package com.example.wariumintergrations;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

// Circuit fabricator: one input to one output.
public record CircuitFabricatorRecipe(Ingredient input, ItemStack output) { }
