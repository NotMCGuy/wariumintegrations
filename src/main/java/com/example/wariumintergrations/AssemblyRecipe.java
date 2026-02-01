package com.example.wariumintergrations;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

// One input, one tool (catalyst), one output.
public record AssemblyRecipe(Ingredient input, Ingredient tool, ItemStack output) { }
