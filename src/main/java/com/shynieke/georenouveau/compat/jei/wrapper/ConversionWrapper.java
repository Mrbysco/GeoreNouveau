package com.shynieke.georenouveau.compat.jei.wrapper;

import com.shynieke.georenouveau.entity.LinkedGeOre;
import mezz.jei.api.recipe.category.extensions.IRecipeCategoryExtension;

public record ConversionWrapper(LinkedGeOre linked) implements IRecipeCategoryExtension<ConversionWrapper> {
}
