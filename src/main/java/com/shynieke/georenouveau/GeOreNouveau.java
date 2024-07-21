package com.shynieke.georenouveau;

import com.mojang.logging.LogUtils;
import com.shynieke.geore.registry.GeOreRegistry;
import com.shynieke.georenouveau.client.ClientHandler;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

import java.util.List;

@Mod(GeOreNouveau.MOD_ID)
public class GeOreNouveau {
	public static final String MOD_ID = "georenouveau";
	public static final Logger LOGGER = LogUtils.getLogger();

	public GeOreNouveau(IEventBus eventBus, Dist dist) {
		CompatRegistry.ENTITY_TYPES.register(eventBus);
		CompatRegistry.ENTITY_DATA_SERIALIZER.register(eventBus);
		CompatRegistry.ITEMS.register(eventBus);

		eventBus.addListener(CompatRegistry::registerEntityAttributes);
		eventBus.addListener(this::fillCreativeTab);

		if (dist.isClient()) {
			eventBus.addListener(ClientHandler::clientSetup);
			eventBus.addListener(ClientHandler::registerRenderers);
		}
	}

	private void fillCreativeTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().equals(GeOreRegistry.GEORE_TAB.getKey())) {
			List<ItemStack> stacks = CompatRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
			event.acceptAll(stacks);
		}
	}
}
