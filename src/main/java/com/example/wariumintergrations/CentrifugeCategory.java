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

public class CentrifugeCategory implements IRecipeCategory<CentrifugeRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(WariumIntegrations.MODID, "centrifuge");
    private final IDrawable background;
    private final IDrawable icon;

    public CentrifugeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(128, 64);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation("crusty_chunks:assembly_centrifuge_middle"))));
    }

    @Override
    public mezz.jei.api.recipe.RecipeType<CentrifugeRecipe> getRecipeType() {
        return FoundryJeiPlugin.CENTRIFUGE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Centrifuge");
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
    public void setRecipe(IRecipeLayoutBuilder builder, CentrifugeRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 32, 26).addIngredients(recipe.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 96, 26).addItemStack(recipe.output());
    }

    @Override
    public void draw(CentrifugeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        graphics.fill(0, 0, 128, 64, 0xFFE0E0E0);
        graphics.fill(0, 0, 128, 1, 0xFFB0B0B0);
        graphics.fill(0, 63, 128, 64, 0xFFB0B0B0);
        graphics.fill(0, 0, 1, 64, 0xFFB0B0B0);
        graphics.fill(127, 0, 128, 64, 0xFFB0B0B0);
    }
}
