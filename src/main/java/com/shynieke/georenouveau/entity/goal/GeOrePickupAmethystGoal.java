package com.shynieke.georenouveau.entity.goal;

import com.hollingsworth.arsnouveau.api.util.BlockUtil;
import com.hollingsworth.arsnouveau.common.entity.AmethystGolem;
import com.hollingsworth.arsnouveau.common.entity.goal.amethyst_golem.PickupAmethystGoal;
import com.shynieke.georenouveau.entity.GeOreGolem;
import com.shynieke.georenouveau.mixin.PickupAmethystGoalAccessor;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.AABB;

import java.util.function.Supplier;

public class GeOrePickupAmethystGoal extends PickupAmethystGoal {
	public GeOreGolem geOreGolem;

	public GeOrePickupAmethystGoal(GeOreGolem golem, Supplier<Boolean> canUse) {
		super(golem, canUse);
		this.geOreGolem = golem;
	}

	@Override
	public void tick() {
		super.tick();

		PickupAmethystGoalAccessor accessor = ((PickupAmethystGoalAccessor) this);
		accessor.georenouveau_setUsingTicks(accessor.georenouveau_getUsingTicks() - 1);
		if (accessor.georenouveau_getUsingTicks() <= 0) {
			accessor.georenouveau_setIsDone(true);
			collectStacks();
			return;
		}

		ItemEntity targetEntity = accessor.georenouveau_getTargetEntity();
		if (targetEntity == null || targetEntity.isRemoved() || !targetEntity.getItem().is(geOreGolem.getLinkedGeOre().getShard())) {
			accessor.georenouveau_setIsDone(true);
			return;
		}
		golem.getNavigation().tryMoveToBlockPos(targetEntity.blockPosition(), 1.0f);

		if (BlockUtil.distanceFrom(golem.blockPosition(), targetEntity.blockPosition()) <= 1.5) {
			collectStacks();
			accessor.georenouveau_setIsDone(true);
			golem.pickupCooldown = 60 + golem.getRandom().nextInt(10);
		}
	}

	public void collectStacks() {
		for (ItemEntity i : golem.level.getEntitiesOfClass(ItemEntity.class, new AABB(golem.getHome()).inflate(10))) {
			if (!i.getItem().is(geOreGolem.getLinkedGeOre().getShard()))
				continue;
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
	public void start() {
		PickupAmethystGoalAccessor accessor = ((PickupAmethystGoalAccessor) this);
		accessor.georenouveau_setIsDone(false);
		accessor.georenouveau_setUsingTicks(80);
		for (ItemEntity entity : golem.level.getEntitiesOfClass(ItemEntity.class, new AABB(golem.getHome()).inflate(10))) {
			if (entity.getItem().is(geOreGolem.getLinkedGeOre().getShard())) {
				golem.getNavigation().tryMoveToBlockPos(entity.blockPosition(), 1f);
				accessor.georenouveau_setTargetEntity(entity);
				break;
			}
		}
		if (accessor.georenouveau_getTargetEntity() == null)
			accessor.georenouveau_setIsDone(true);
		golem.goalState = AmethystGolem.AmethystGolemGoalState.PICKUP;
	}
}
