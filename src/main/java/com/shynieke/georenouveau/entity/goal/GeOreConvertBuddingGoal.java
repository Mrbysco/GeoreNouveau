package com.shynieke.georenouveau.entity.goal;

import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.entity.AmethystGolem;
import com.hollingsworth.arsnouveau.common.entity.goal.amethyst_golem.ConvertBuddingGoal;
import com.shynieke.georenouveau.entity.GeOreGolem;
import com.shynieke.georenouveau.mixin.ConvertBuddingGoalAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class GeOreConvertBuddingGoal extends ConvertBuddingGoal {
	public GeOreGolem geOreGolem;

	public GeOreConvertBuddingGoal(GeOreGolem golem, Supplier<Boolean> canUse) {
		super(golem, canUse);
		this.geOreGolem = golem;
	}

	@Override
	public void convert() {
		BlockPos targetCluster = ((ConvertBuddingGoalAccessor) this).georenouveau_getTargetCluster();
		if (targetCluster != null && golem.level.getBlockState(targetCluster).is(geOreGolem.getLinkedGeOre().getBlock())) {
			golem.level.setBlock(targetCluster, Blocks.BUDDING_AMETHYST.defaultBlockState(), 3);
			ParticleUtil.spawnTouchPacket(golem.level, targetCluster, ParticleUtil.defaultParticleColorWrapper());
		}
		golem.convertCooldown = 20 * 60 * 5;
		golem.setImbueing(false);
		golem.setImbuePos(BlockPos.ZERO);
	}

	@Override
	public void start() {
		((ConvertBuddingGoalAccessor) this).georenouveau_setIsDone(false);
		((ConvertBuddingGoalAccessor) this).georenouveau_setUsingTicks(120);
		for (BlockPos pos : golem.amethystBlocks) {
			if (geOreGolem.level.getBlockState(pos).is(geOreGolem.getLinkedGeOre().getBlock())) {
				((ConvertBuddingGoalAccessor) this).georenouveau_setTargetCluster(pos);
				break;
			}
		}
		golem.goalState = AmethystGolem.AmethystGolemGoalState.CONVERT;
	}
}
