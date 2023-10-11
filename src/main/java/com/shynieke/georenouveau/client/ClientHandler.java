package com.shynieke.georenouveau.client;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.shynieke.georenouveau.client.renderer.GeOreGolemRenderer;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClientHandler {
	public static void clientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			List<Item> rods = new ArrayList<>();
			rods.add(CompatRegistry.COAL_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.COPPER_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.DIAMOND_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.EMERALD_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.GOLD_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.IRON_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.LAPIS_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.QUARTZ_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.REDSTONE_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.RUBY_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.SAPPHIRE_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.TOPAZ_GEORE_DOWSING_ROD.get());
			rods.add(CompatRegistry.ZINC_GEORE_DOWSING_ROD.get());

			for (Item rod : rods) {
				ItemProperties.register(rod, new ResourceLocation(ArsNouveau.MODID, "uses"), new ClampedItemPropertyFunction() {
					@Override
					public float unclampedCall(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity livingEntity, int index) {
						return switch (stack.getDamageValue()) {
							case 1 -> 0.75f;
							case 2 -> 0.50f;
							case 3 -> 0.25f;
							default -> 1.0f;
						};
					}
				});
			}
		});
	}

	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(CompatRegistry.GEORE_GOLEM.get(), GeOreGolemRenderer::new);
	}
}
