package com.example.wariumintergrations;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public record FoundryRecipe(Ingredient template, Ingredient material, ItemStack result) { }
