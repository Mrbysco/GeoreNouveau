package com.shynieke.georenouveau.mixin;

import com.hollingsworth.arsnouveau.common.entity.goal.amethyst_golem.ConvertBuddingGoal;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ConvertBuddingGoal.class)
public interface ConvertBuddingGoalAccessor {
	@Accessor("targetCluster")
	BlockPos georenouveau_getTargetCluster();

	@Accessor("targetCluster")
	void georenouveau_setTargetCluster(BlockPos targetCluster);

	@Accessor("isDone")
	void georenouveau_setIsDone(boolean isDone);

	@Accessor("usingTicks")
	void georenouveau_setUsingTicks(int usingTicks);
}
