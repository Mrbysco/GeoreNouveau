package com.shynieke.georenouveau;

import com.mojang.logging.LogUtils;
import com.shynieke.georenouveau.client.ClientHandler;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

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

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::registerRenderers);
		});
	}
}
