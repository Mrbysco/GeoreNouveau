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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Arrays;
import java.util.List;

@Mixin(RitualAwakening.class)
public class RitualAwakeningMixin {

	@Shadow(remap = false)
	BlockPos foundPos;

	@Shadow(remap = false)
	EntityType<? extends LivingEntity> entity;
	
	@Unique
	private LinkedGeOre georenouveau_linkedGeOre;

	@Inject(method = "findTargets(Lnet/minecraft/world/level/Level;)V",
			locals = LocalCapture.NO_CAPTURE, at = @At(
			value = "HEAD"), cancellable = true, remap = false)
	private void georenouveau_findTargets(Level level, CallbackInfo ci) {
		RitualAwakening ritual = (RitualAwakening) (Object) this;

		georenouveau_linkedGeOre = LinkedGeOre.DEFAULT;
		for (BlockPos p : BlockPos.withinManhattan(ritual.getPos(), 3, 1, 3)) {
			List<LinkedGeOre> linkedGeOres = Arrays.stream(LinkedGeOre.values()).filter(geore -> level.getBlockState(p).is(geore.getBudding())).toList();
			if (!linkedGeOres.isEmpty()) {
				LinkedGeOre linked = linkedGeOres.getFirst();
				if (level.getBlockState(p).is(linked.getBudding())) {
					level.setBlock(p, Blocks.AIR.defaultBlockState(), 3);
					entity = CompatRegistry.GEORE_GOLEM.get();
					foundPos = p;
					georenouveau_linkedGeOre = linked;
					ci.cancel();
					break;
				}
			}
		}
	}

	@Inject(method = "tick()V",
			at = @At(
					value = "INVOKE",
					target = "Lcom/hollingsworth/arsnouveau/client/particle/ParticleUtil;spawnPoof(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)V",
					shift = Shift.AFTER,
					ordinal = 0),
			remap = false, cancellable = true)
	private void georenouveau_tick(CallbackInfo ci) {
		if (georenouveau_linkedGeOre != LinkedGeOre.DEFAULT) {
			RitualAwakening ritual = (RitualAwakening) (Object) this;
			Level level = ritual.getWorld();
			LivingEntity walker = entity.create(level);
			if (walker instanceof GeOreGolem golem) {
				golem.setLinkedGeOre(georenouveau_linkedGeOre);
				golem.setPos(foundPos.getX() + 0.5, foundPos.getY(), foundPos.getZ() + 0.5);
				level.addFreshEntity(golem);
			}
			ritual.setFinished();
			ci.cancel();
		}
	}
}
