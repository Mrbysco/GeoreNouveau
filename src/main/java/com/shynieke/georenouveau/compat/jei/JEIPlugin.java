package com.shynieke.georenouveau.compat.jei;

import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.compat.jei.category.ConversionCategory;
import com.shynieke.georenouveau.compat.jei.wrapper.ConversionWrapper;
import com.shynieke.georenouveau.entity.LinkedGeOre;
import com.shynieke.georenouveau.registry.CompatRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

	public static final ResourceLocation PLUGIN_UID = GeOreNouveau.modLoc("main");

	public static final RecipeType<ConversionWrapper> CONVERSION_TYPE = RecipeType.create(GeOreNouveau.MOD_ID, "conversion", ConversionWrapper.class);

	@Nullable
	private IRecipeCategory<ConversionWrapper> conversionCategory;

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_UID;
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalysts(CONVERSION_TYPE,
				CompatRegistry.COAL_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.COPPER_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.DIAMOND_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.EMERALD_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.GOLD_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.IRON_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.LAPIS_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.QUARTZ_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.REDSTONE_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.ANCIENT_DEBRIS_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.RUBY_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.SAPPHIRE_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.TOPAZ_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.ZINC_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.URANINITE_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.BLACK_QUARTZ_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.MONAZITE_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.ALUMINUM_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.LEAD_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.NICKEL_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.OSMIUM_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.PLATINUM_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.SILVER_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.TIN_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.TUNGSTEN_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.URANIUM_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.ALLTHEMODIUM_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.VIBRANIUM_GEORE_GOLEM_CHARM.asItem(),
				CompatRegistry.UNOBTAINIUM_GEORE_GOLEM_CHARM.asItem()
		);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IJeiHelpers jeiHelpers = registration.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registration.addRecipeCategories(
				conversionCategory = new ConversionCategory(guiHelper)
		);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(CONVERSION_TYPE, Arrays.stream(LinkedGeOre.values())
				.filter(ore -> ore != LinkedGeOre.DEFAULT)
				.map(ConversionWrapper::new).toList());
	}
}
