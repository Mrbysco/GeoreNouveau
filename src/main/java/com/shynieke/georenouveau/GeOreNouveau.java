package com.shynieke.georenouveau;

import com.mojang.logging.LogUtils;
import com.shynieke.geore.registry.GeOreRegistry;
import com.shynieke.georenouveau.client.ClientHandler;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.List;

@Mod(GeOreNouveau.MOD_ID)
public class GeOreNouveau {
	public static final String MOD_ID = "georenouveau";
	public static final Logger LOGGER = LogUtils.getLogger();

	public GeOreNouveau() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		CompatRegistry.ENTITY_TYPES.register(eventBus);
		CompatRegistry.ENTITY_DATA_SERIALIZER.register(eventBus);
		CompatRegistry.ITEMS.register(eventBus);

		eventBus.addListener(CompatRegistry::registerEntityAttributes);
		eventBus.addListener(this::fillCreativeTab);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::registerRenderers);
		});
	}

	private void fillCreativeTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == GeOreRegistry.GEORE_TAB.getKey()) {
			List<ItemStack> stacks = CompatRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
			event.acceptAll(stacks);
		}
	}
}
