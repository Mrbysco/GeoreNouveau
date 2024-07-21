package com.shynieke.georenouveau.client.model;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.entity.GeOreGolem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GeOreGolemModel extends GeoModel<GeOreGolem> {

	public static final ResourceLocation NORMAL_MODEL = ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "geo/amethyst_golem.geo.json");
	public static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "animations/amethyst_golem_animations.json");
	public static final ResourceLocation[] TEXTURES = new ResourceLocation[]{
			ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "textures/entity/amethyst_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/coal_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/copper_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/diamond_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/emerald_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/gold_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/iron_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/lapis_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/quartz_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/redstone_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/redstone_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/lapis_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/gold_golem.png"),
			ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/zinc_golem.png"),
	};

	@Override
	public ResourceLocation getModelResource(GeOreGolem golem) {
		return NORMAL_MODEL;
	}

	@Override
	public ResourceLocation getTextureResource(GeOreGolem golem) {
		return TEXTURES[golem.getLinkedGeOre().ordinal()];
	}

	@Override
	public ResourceLocation getAnimationResource(GeOreGolem golem) {
		return ANIMATIONS;
	}

}
