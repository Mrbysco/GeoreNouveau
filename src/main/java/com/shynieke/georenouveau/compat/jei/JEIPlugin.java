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
import net.minecraft.world.item.ItemStack;
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
		registration.addRecipeCatalyst(new ItemStack(CompatRegistry.COAL_GEORE_GOLEM_CHARM.get()), CONVERSION_TYPE);
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
