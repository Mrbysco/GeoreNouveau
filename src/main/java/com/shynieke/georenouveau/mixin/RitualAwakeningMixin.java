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

import java.util.Arrays;
import java.util.List;

@Mixin(RitualAwakening.class)
public class RitualAwakeningMixin {

	@Shadow(remap = false)
	private BlockPos foundPos;

	@Shadow(remap = false)
	private EntityType<? extends LivingEntity> entity;
	private LinkedGeOre linkedGeOre;

	@Inject(method = "Lcom/hollingsworth/arsnouveau/common/ritual/RitualAwakening;findTargets(Lnet/minecraft/world/level/Level;)V",
			locals = LocalCapture.NO_CAPTURE, at = @At(
			value = "HEAD"), cancellable = true, remap = false)
	private void georenouveau_findTargets(Level world, CallbackInfo ci) {
		RitualAwakening ritual = (RitualAwakening) (Object) this;

		linkedGeOre = LinkedGeOre.DEFAULT;
		for (BlockPos p : BlockPos.withinManhattan(ritual.getPos(), 3, 1, 3)) {
			List<LinkedGeOre> linkedGeOres = Arrays.stream(LinkedGeOre.values()).filter(geore -> world.getBlockState(p).is(geore.getBudding())).toList();
			if (!linkedGeOres.isEmpty()) {
				LinkedGeOre linked = linkedGeOres.get(0);
				if (world.getBlockState(p).is(linked.getBudding())) {
					world.setBlock(p, Blocks.AIR.defaultBlockState(), 3);
					entity = CompatRegistry.GEORE_GOLEM.get();
					foundPos = p;
					linkedGeOre = linked;
					ci.cancel();
					break;
				}
			}
		}
	}

	@Inject(method = "Lcom/hollingsworth/arsnouveau/common/ritual/RitualAwakening;tick()V",
			at = @At(
					value = "INVOKE",
					target = "Lcom/hollingsworth/arsnouveau/client/particle/ParticleUtil;spawnPoof(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)V",
					shift = Shift.AFTER,
					ordinal = 0),
			remap = false, cancellable = true)
	private void georenouveau_tick(CallbackInfo ci) {
		if (linkedGeOre != LinkedGeOre.DEFAULT) {
			RitualAwakening ritual = (RitualAwakening) (Object) this;
			Level level = ritual.getWorld();
			LivingEntity walker = entity.create(level);
			if (walker instanceof GeOreGolem golem) {
				golem.setLinkedGeOre(linkedGeOre);
				golem.setPos(foundPos.getX() + 0.5, foundPos.getY(), foundPos.getZ() + 0.5);
				level.addFreshEntity(golem);
			}
			((AbstractRitualAccessor) ritual).georenouveau_setFinished();
			ci.cancel();
		}
	}
}
