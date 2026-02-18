package com.shynieke.georenouveau.compat.jei.category;

import com.shynieke.georenouveau.compat.jei.JEIPlugin;
import com.shynieke.georenouveau.compat.jei.wrapper.ConversionWrapper;
import com.shynieke.georenouveau.registry.CompatRegistry;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConversionCategory implements IRecipeCategory<ConversionWrapper> {
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableAnimated arrow;
	private final Component localizedName;

	public ConversionCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createBlankDrawable(120, 24);
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, CompatRegistry.COAL_GEORE_GOLEM_CHARM.asItem().getDefaultInstance());
		this.arrow = guiHelper.createAnimatedRecipeArrow(40);
		this.localizedName = Component.translatable("georenouveau.gui.jei.category.conversion");
	}

	@Override
	public RecipeType<ConversionWrapper> getRecipeType() {
		return JEIPlugin.CONVERSION_TYPE;
	}

	@Nullable
	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@NotNull
	@Override
	public Component getTitle() {
		return this.localizedName;
	}

	@Nullable
	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ConversionWrapper wrapper, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.CATALYST, 32, 4).addItemLike(wrapper.linked().getCharm());
		builder.addSlot(RecipeIngredientRole.OUTPUT, 120 - 16 - 6, 4).addItemLike(wrapper.linked().getBudding());
		builder.addSlot(RecipeIngredientRole.INPUT, 6, 4).addItemLike(wrapper.linked().getBlock());
	}

	@Override
	public void draw(ConversionWrapper wrapper, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		arrow.draw(guiGraphics, 48, 5);
		// Check if arrow is hovered (mouseX mouseY) and display tooltip
		if (mouseX >= 48 && mouseX <= 48 + arrow.getWidth() && mouseY >= 5 && mouseY <= 5 + arrow.getHeight()) {
			String formattedName = wrapper.linked().name;
			// Replace underscores with spaces
			formattedName = formattedName.replace("_", " ");
			// Capitalize first letter of each word
			formattedName = Character.toUpperCase(formattedName.charAt(0)) + formattedName.substring(1);
			guiGraphics.renderTooltip(Minecraft.getInstance().font,
					Component.translatable("georenouveau.gui.jei.category.conversion.required", formattedName).withStyle(ChatFormatting.YELLOW),
					(int) mouseX, (int) mouseY);
		}
	}
}
