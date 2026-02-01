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

public class AssemblyCategory implements IRecipeCategory<AssemblyRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(WariumIntegrations.MODID, "assembly");
    private final IDrawable background;
    private final IDrawable icon;

    public AssemblyCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(128, 64); // neutral gray drawn in draw()
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation("crusty_chunks:assembly_machine"))));
    }

    @Override
    public mezz.jei.api.recipe.RecipeType<AssemblyRecipe> getRecipeType() {
        return FoundryJeiPlugin.ASSEMBLY_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Assembly Machine");
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
    public void setRecipe(IRecipeLayoutBuilder builder, AssemblyRecipe recipe, IFocusGroup focuses) {
        // Input, tool (catalyst), output spaced across panel
        builder.addSlot(RecipeIngredientRole.INPUT, 16, 26).addIngredients(recipe.input());
        builder.addSlot(RecipeIngredientRole.CATALYST, 58, 26).addIngredients(recipe.tool());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 100, 26).addItemStack(recipe.output());
    }

    @Override
    public void draw(AssemblyRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        graphics.fill(0, 0, 128, 64, 0xFFE0E0E0); // light gray
        // simple border
        graphics.fill(0, 0, 128, 1, 0xFFB0B0B0);
        graphics.fill(0, 63, 128, 64, 0xFFB0B0B0);
        graphics.fill(0, 0, 1, 64, 0xFFB0B0B0);
        graphics.fill(127, 0, 128, 64, 0xFFB0B0B0);

        // Show the tool name above the catalyst slot (press/shear/extruder/bore)
        String toolName = recipe.tool().getItems().length > 0
                ? recipe.tool().getItems()[0].getHoverName().getString()
                : "Tool";
        var font = Minecraft.getInstance().font;
        graphics.drawString(font, toolName, 58 - font.width(toolName) / 2, 10, 0x404040, false);
    }
}
