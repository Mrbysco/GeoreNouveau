package com.shynieke.georenouveau.mixin;

import com.hollingsworth.arsnouveau.common.ritual.RitualAwakening;
import com.shynieke.georenouveau.entity.GeOreGolem;
import com.shynieke.georenouveau.entity.LinkedGeOre;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(RitualAwakening.class)
public class RitualAwakeningMixin {

	@Shadow
	private BlockPos foundPos;

	@Shadow
	private EntityType<? extends LivingEntity> entity;
	private LinkedGeOre linkedGeOre;

	@Inject(method = "findTargets(Lnet/minecraft/world/level/Level;)V",
			at = @At(value = "TAIL"), cancellable = true)
	private void georenouveau_findTargets(Level world, CallbackInfo ci) {
		RitualAwakening ritual = (RitualAwakening) (Object) this;

		linkedGeOre = LinkedGeOre.DEFAULT;
		for (BlockPos p : BlockPos.withinManhattan(ritual.getPos(), 3, 1, 3)) {
			for (LinkedGeOre linked : LinkedGeOre.values()) {
				if (world.getBlockState(p).is(linked.getBudding())) {
					world.setBlock(p, Blocks.AIR.defaultBlockState(), 3);
					entity = CompatRegistry.GEORE_GOLEM.get();
					foundPos = p;
					linkedGeOre = linked;
					ci.cancel();
				}
			}
		}
	}

	@Inject(method = "tick()V",
			locals = LocalCapture.CAPTURE_FAILEXCEPTION, at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/LivingEntity;setPos(DDD)V",
			shift = Shift.AFTER,
			ordinal = 0
	))
	private void georenouveau_tick(CallbackInfo ci, Level world, LivingEntity walker) {
		if (walker instanceof GeOreGolem geOreGolem) {
			geOreGolem.setLinkedGeOre(linkedGeOre);
		}
	}
}
