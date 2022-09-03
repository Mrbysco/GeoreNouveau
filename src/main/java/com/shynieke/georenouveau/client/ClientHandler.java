package com.shynieke.georenouveau.client;

import com.shynieke.georenouveau.client.renderer.GeOreGolemRenderer;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientHandler {
	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(CompatRegistry.GEORE_GOLEM.get(), GeOreGolemRenderer::new);
	}
}
