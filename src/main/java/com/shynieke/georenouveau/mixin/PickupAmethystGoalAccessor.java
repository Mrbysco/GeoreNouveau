package com.shynieke.georenouveau.mixin;

import com.hollingsworth.arsnouveau.common.entity.goal.amethyst_golem.PickupAmethystGoal;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PickupAmethystGoal.class)
public interface PickupAmethystGoalAccessor {
	@Accessor("targetEntity")
	ItemEntity georenouveau_getTargetEntity();

	@Accessor("targetEntity")
	void georenouveau_setTargetEntity(ItemEntity targetEntity);

	@Accessor("isDone")
	void georenouveau_setIsDone(boolean isDone);

	@Accessor("usingTicks")
	int georenouveau_getUsingTicks();

	@Accessor("usingTicks")
	void georenouveau_setUsingTicks(int usingTicks);
}
