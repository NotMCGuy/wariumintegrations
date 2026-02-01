package com.example.wariumintergrations.recipes;

import com.example.wariumintergrations.WariumIntegrations;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModRecipeTypes {
    private ModRecipeTypes() {
    }

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, WariumIntegrations.MODID);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, WariumIntegrations.MODID);

    public static final RegistryObject<RecipeType<MechanicalFabricatorRecipe>> MECHANICAL_FABRICATOR_TYPE =
            RECIPE_TYPES.register("mechanical_fabricator", () -> RecipeType.simple(id("mechanical_fabricator")));

    public static final RegistryObject<RecipeSerializer<MechanicalFabricatorRecipe>> MECHANICAL_FABRICATOR_SERIALIZER =
            RECIPE_SERIALIZERS.register("mechanical_fabricator", MechanicalFabricatorRecipeSerializer::new);

    public static void register(IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
        RECIPE_TYPES.register(modEventBus);
    }

    private static ResourceLocation id(String path) {
        return new ResourceLocation(WariumIntegrations.MODID, path);
    }
}
