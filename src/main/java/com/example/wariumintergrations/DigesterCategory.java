package com.example.wariumintergrations;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.Minecraft;

public class DigesterCategory implements IRecipeCategory<DigesterRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(WariumIntegrations.MODID, "digester");
    private final IDrawable background;
    private final IDrawable icon;

    public DigesterCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(128, 64);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation("crusty_chunks:bauxite_digester"))));
    }

    @Override
    public mezz.jei.api.recipe.RecipeType<DigesterRecipe> getRecipeType() {
        return FoundryJeiPlugin.DIGESTER_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Chemical Digester");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DigesterRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 32, 26).addIngredients(recipe.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 96, 26).addItemStack(recipe.output());
    }

    @Override
    public void draw(DigesterRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        graphics.fill(0, 0, 128, 64, 0xFFE0E0E0);
        graphics.fill(0, 0, 128, 1, 0xFFB0B0B0);
        graphics.fill(0, 63, 128, 64, 0xFFB0B0B0);
        graphics.fill(0, 0, 1, 64, 0xFFB0B0B0);
        graphics.fill(127, 0, 128, 64, 0xFFB0B0B0);
    }
}
