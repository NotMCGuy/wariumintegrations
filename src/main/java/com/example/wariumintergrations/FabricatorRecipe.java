package com.example.wariumintergrations;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

// Assembly fabricator style: one input to one output.
public record FabricatorRecipe(Ingredient input, ItemStack output) { }
