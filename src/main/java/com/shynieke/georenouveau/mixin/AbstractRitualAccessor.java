package com.shynieke.georenouveau.mixin;

import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractRitual.class)
public interface AbstractRitualAccessor {
	@Invoker("setFinished")
	void georenouveau_setFinished();
}
