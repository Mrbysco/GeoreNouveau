package com.shynieke.georenouveau.client.model;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.entity.GeOreGolem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.ars_nouveau.geckolib.model.GeoModel;

public class GeOreGolemModel extends GeoModel<GeOreGolem> {

	public static final ResourceLocation NORMAL_MODEL = new ResourceLocation(ArsNouveau.MODID, "geo/amethyst_golem.geo.json");
	public static final ResourceLocation ANIMATIONS = new ResourceLocation(ArsNouveau.MODID, "animations/amethyst_golem_animations.json");
	public static final ResourceLocation[] TEXTURES = new ResourceLocation[]{
			new ResourceLocation(ArsNouveau.MODID, "textures/entity/amethyst_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/coal_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/copper_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/diamond_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/emerald_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/gold_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/iron_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/lapis_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/quartz_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/redstone_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/redstone_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/lapis_golem.png"),
			new ResourceLocation(GeOreNouveau.MOD_ID, "textures/entity/gold_golem.png"),
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
	public ResourceLocation getAnimationResource(GeOreGolem drygmy) {
		return ANIMATIONS;
	}

}
