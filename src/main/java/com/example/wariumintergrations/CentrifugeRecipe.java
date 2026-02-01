package com.example.wariumintergrations;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

// One input to one output.
public record CentrifugeRecipe(Ingredient input, ItemStack output) { }
