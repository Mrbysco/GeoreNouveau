package com.shynieke.georenouveau.entity.goal;

import com.hollingsworth.arsnouveau.api.util.BlockUtil;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.entity.AmethystGolem;
import com.shynieke.georenouveau.entity.GeOreGolem;
import com.shynieke.georenouveau.entity.LinkedGeOre;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.function.Supplier;

public class GeOreConvertBuddingGoal extends Goal {
	public GeOreGolem golem;
	public Supplier<Boolean> canUse;
	BlockPos targetCluster;
	int usingTicks;
	boolean isDone;

	public GeOreConvertBuddingGoal(GeOreGolem golem, Supplier<Boolean> canUse) {
		this.golem = golem;
		this.canUse = canUse;
	}

	@Override
	public boolean canContinueToUse() {
		return targetCluster != null && !isDone;
	}

	@Override
	public void tick() {
		super.tick();
		usingTicks--;
		golem.getNavigation().tryMoveToBlockPos(targetCluster, 1.0f);
		if (usingTicks <= 0) {
			isDone = true;
			convert();
			return;
		}
		if (BlockUtil.distanceFrom(golem.blockPosition(), targetCluster) <= 2) {
			golem.setImbuePos(targetCluster);
			golem.setImbueing(true);
			usingTicks = Math.min(usingTicks, 40);
		}
	}

	public void convert() {
		LinkedGeOre linked = golem.getLinkedGeOre();
		if (targetCluster != null && golem.level.getBlockState(targetCluster).is(linked.getBlock())) {
			golem.level.setBlock(targetCluster, linked.getBudding().defaultBlockState(), 3);
			ParticleUtil.spawnTouchPacket(golem.level, targetCluster, ParticleUtil.defaultParticleColorWrapper());
		}
		golem.convertCooldown = 20 * 60 * 5;
		golem.setImbueing(false);
		golem.setImbuePos(BlockPos.ZERO);
	}

	@Override
	public void start() {
		this.isDone = false;
		this.usingTicks = 120;
		LinkedGeOre linked = golem.getLinkedGeOre();
		for (BlockPos pos : golem.amethystBlocks) {
			if (golem.level.getBlockState(pos).is(linked.getBlock())) {
				targetCluster = pos;
				break;
			}
		}
		golem.goalState = AmethystGolem.AmethystGolemGoalState.CONVERT;
	}

	@Override
	public void stop() {
		golem.setImbueing(false);
		golem.goalState = AmethystGolem.AmethystGolemGoalState.NONE;
	}

	@Override
	public boolean isInterruptable() {
		return false;
	}

	@Override
	public boolean canUse() {
		return canUse.get() && golem.convertCooldown <= 0 && !golem.amethystBlocks.isEmpty();
	}
}
