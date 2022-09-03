package com.shynieke.georenouveau.entity.goal;

import com.hollingsworth.arsnouveau.api.util.BlockUtil;
import com.hollingsworth.arsnouveau.common.entity.AmethystGolem;
import com.shynieke.georenouveau.entity.GeOreGolem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.function.Supplier;

public class GeOrePickupAmethystGoal extends Goal {
	public GeOreGolem golem;
	public Supplier<Boolean> canUse;
	ItemEntity targetEntity;
	int usingTicks;
	boolean isDone;


	public GeOrePickupAmethystGoal(GeOreGolem golem, Supplier<Boolean> canUse) {
		this.golem = golem;
		this.canUse = canUse;
	}

	@Override
	public boolean canContinueToUse() {
		return targetEntity != null && !isDone;
	}

	@Override
	public void tick() {
		super.tick();

		usingTicks--;
		if (usingTicks <= 0) {
			isDone = true;
			collectStacks();
			return;
		}

		if (targetEntity == null || targetEntity.isRemoved() || !targetEntity.getItem().is(golem.getLinkedGeOre().getShard())) {
			isDone = true;
			return;
		}
		golem.getNavigation().tryMoveToBlockPos(targetEntity.blockPosition(), 1.0f);

		if (BlockUtil.distanceFrom(golem.blockPosition(), targetEntity.blockPosition()) <= 1.5) {
			collectStacks();
			isDone = true;
			golem.pickupCooldown = 60 + golem.getRandom().nextInt(10);
		}
	}

	public void collectStacks() {
		for (ItemEntity i : golem.level.getEntitiesOfClass(ItemEntity.class, new AABB(golem.getHome()).inflate(10))) {
			if (!i.getItem().is(golem.getLinkedGeOre().getShard())) continue;
			int maxTake = golem.getHeldStack().getMaxStackSize() - golem.getHeldStack().getCount();
			if (golem.getHeldStack().isEmpty()) {
				golem.setHeldStack(i.getItem().copy());
				i.getItem().setCount(0);
				continue;
			}

			int toTake = Math.min(i.getItem().getCount(), maxTake);
			i.getItem().shrink(toTake);
			golem.getHeldStack().grow(toTake);
		}
	}

	@Override
	public void stop() {
		isDone = false;
		usingTicks = 80;
		golem.goalState = AmethystGolem.AmethystGolemGoalState.NONE;
		golem.pickupCooldown = 60 + golem.getRandom().nextInt(10);
	}

	@Override
	public void start() {
		this.isDone = false;
		this.usingTicks = 80;
		for (ItemEntity entity : golem.level.getEntitiesOfClass(ItemEntity.class, new AABB(golem.getHome()).inflate(10))) {
			if (entity.getItem().is(golem.getLinkedGeOre().getShard())) {
				golem.getNavigation().tryMoveToBlockPos(entity.blockPosition(), 1f);
				targetEntity = entity;
				break;
			}
		}
		if (targetEntity == null) isDone = true;
		golem.goalState = AmethystGolem.AmethystGolemGoalState.PICKUP;
	}

	@Override
	public boolean isInterruptable() {
		return false;
	}

	@Override
	public boolean canUse() {
		if (golem.getHome() == null) return false;
		BlockEntity entity = golem.getLevel().getBlockEntity(golem.getHome());
		return canUse.get() && golem.pickupCooldown <= 0 && entity != null && entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent();
	}
}
