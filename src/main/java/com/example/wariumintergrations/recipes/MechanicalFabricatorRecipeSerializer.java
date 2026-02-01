package com.example.wariumintergrations.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public final class MechanicalFabricatorRecipeSerializer implements RecipeSerializer<MechanicalFabricatorRecipe> {
    @Override
    public MechanicalFabricatorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
        JsonElement inputJson = GsonHelper.getNonNull(json, "input");
        Ingredient input = Ingredient.fromJson(inputJson);

        JsonObject resultJson = GsonHelper.getAsJsonObject(json, "result");
        String itemId = GsonHelper.getAsString(resultJson, "item");
        int count = GsonHelper.getAsInt(resultJson, "count", 1);

        Item item = BuiltInRegistries.ITEM
                .getOptional(new ResourceLocation(itemId))
                .orElseThrow(() -> new JsonSyntaxException("Unknown item: " + itemId));

        return new MechanicalFabricatorRecipe(recipeId, input, new ItemStack(item, count));
    }

    @Override
    public MechanicalFabricatorRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        Ingredient input = Ingredient.fromNetwork(buffer);
        ItemStack result = buffer.readItem();
        return new MechanicalFabricatorRecipe(recipeId, input, result);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, MechanicalFabricatorRecipe recipe) {
        recipe.input().toNetwork(buffer);
        buffer.writeItem(recipe.result());
    }
}
