package com.shynieke.georenouveau.client.model;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.entity.GeOreGolem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GeOreGolemModel extends GeoModel<GeOreGolem> {

	public static final ResourceLocation NORMAL_MODEL = ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "geo/amethyst_golem.geo.json");
	public static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "animations/amethyst_golem_animations.json");

	@Override
	public ResourceLocation getModelResource(GeOreGolem golem) {
		return NORMAL_MODEL;
	}

	@Override
	public ResourceLocation getTextureResource(GeOreGolem golem) {
		return golem.getLinkedGeOre().getTextureLocation();
	}

	@Override
	public ResourceLocation getAnimationResource(GeOreGolem golem) {
		return ANIMATIONS;
	}

}
