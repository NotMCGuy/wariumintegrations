package com.example.wariumintergrations;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

// Chemical digester: one input to one output.
public record DigesterRecipe(Ingredient input, ItemStack output) { }
