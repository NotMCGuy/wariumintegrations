package com.example.wariumintergrations;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@JeiPlugin
public class FoundryJeiPlugin implements IModPlugin {
    public static final ResourceLocation UID = new ResourceLocation(WariumIntegrations.MODID, "jei_plugin");
    public static final RecipeType<FoundryRecipe> FOUNDRY_TYPE = RecipeType.create(WariumIntegrations.MODID, "foundry", FoundryRecipe.class);
    public static final RecipeType<AssemblyRecipe> ASSEMBLY_TYPE = RecipeType.create(WariumIntegrations.MODID, "assembly", AssemblyRecipe.class);
    public static final RecipeType<CentrifugeRecipe> CENTRIFUGE_TYPE = RecipeType.create(WariumIntegrations.MODID, "centrifuge", CentrifugeRecipe.class);
    public static final RecipeType<FabricatorRecipe> FABRICATOR_TYPE = RecipeType.create(WariumIntegrations.MODID, "fabricator", FabricatorRecipe.class);
    public static final RecipeType<DigesterRecipe> DIGESTER_TYPE = RecipeType.create(WariumIntegrations.MODID, "digester", DigesterRecipe.class);
    public static final RecipeType<CircuitFabricatorRecipe> CIRCUIT_FABRICATOR_TYPE = RecipeType.create(WariumIntegrations.MODID, "circuit_fabricator", CircuitFabricatorRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new FoundryCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new AssemblyCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CentrifugeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new FabricatorCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new DigesterCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CircuitFabricatorCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        List<FoundryRecipe> recipes = collect(Stream.of(
            foundry("crusty_chunks:component_foundry_template", "immersiveengineering:ingot_steel", 1, "crusty_chunks:cast_component"),
            foundry("crusty_chunks:cylinder_foundry_template", "immersiveengineering:ingot_steel", 1, "crusty_chunks:steel_cylinder"),
            foundry("crusty_chunks:small_projectile_foundry_template", "immersiveengineering:nugget_lead", 3, "crusty_chunks:small_projectile"),
            foundry("crusty_chunks:medium_projectile_foundry_template", "immersiveengineering:nugget_lead", 6, "crusty_chunks:medium_projectile"),
            foundry("crusty_chunks:large_projectile_foundry_template", "immersiveengineering:ingot_lead", 1, "crusty_chunks:large_projectile"),
            foundry("crusty_chunks:extra_large_projectile_template", "immersiveengineering:ingot_lead", 3, "crusty_chunks:extra_large_projectile"),
            foundry("crusty_chunks:huge_projectile_foundry_template", "immersiveengineering:ingot_lead", 6, "crusty_chunks:huge_projectile"),
            foundry("crusty_chunks:small_barrel_template", "immersiveengineering:ingot_steel", 1, "crusty_chunks:small_unbored_barrel"),
            foundry("crusty_chunks:medium_barrel_template", "immersiveengineering:ingot_steel", 3, "crusty_chunks:medium_unbored_barrel"),
            foundry("crusty_chunks:large_barrel_template", "immersiveengineering:ingot_steel", 4, "crusty_chunks:large_unbored_barrel"),
            foundry("crusty_chunks:huge_barrel_foundry_template", "immersiveengineering:ingot_steel", 6, "crusty_chunks:huge_unbored_barrel"),
            foundry("crusty_chunks:small_cannon_foundry_template", "immersiveengineering:ingot_steel", 8, "crusty_chunks:small_unbored_cannon_barrel"),
            foundry("crusty_chunks:medium_cannon_foundry_template", "immersiveengineering:storage_steel", 1, "crusty_chunks:medium_unbored_cannon_barrel"),
            foundry("crusty_chunks:large_cannon_foundry_template", "immersiveengineering:storage_steel", 1, "crusty_chunks:large_unbored_cannon_barrel"),
            foundry("crusty_chunks:huge_cannon_foundry_template", "immersiveengineering:storage_steel", 2, "crusty_chunks:huge_unbored_cannon_barrel")
        ));
        registry.addRecipes(FOUNDRY_TYPE, recipes);

        Optional<Ingredient> bore = ingOpt("crusty_chunks:mechanical_bore");
        Optional<Ingredient> press = ingOpt("crusty_chunks:mechanical_press");
        Optional<Ingredient> extruder = ingOpt("crusty_chunks:mechanical_extruder");
        Optional<Ingredient> shear = ingOpt("crusty_chunks:mechanical_shear");
        Optional<Ingredient> fabricator = ingOpt("crusty_chunks:assembly_mechanical_fabricator");

        List<AssemblyRecipe> assembly = collect(Stream.of(
            assembly("crusty_chunks:cast_component", bore, "crusty_chunks:bored_component"),
            assembly("crusty_chunks:steel_cylinder", bore, "crusty_chunks:steel_tube"),
            assembly("crusty_chunks:brass_ingot", bore, "crusty_chunks:brass_fitting"),
            assembly("crusty_chunks:large_projectile", bore, "crusty_chunks:hollowed_large_projectile"),
            assembly("crusty_chunks:extra_large_projectile", bore, "crusty_chunks:hollowed_extra_large_projectile"),
            assembly("crusty_chunks:huge_projectile", bore, "crusty_chunks:hollowed_huge_projectile"),
            assembly("crusty_chunks:small_unbored_barrel", bore, "crusty_chunks:small_bored_barrel"),
            assembly("crusty_chunks:medium_unbored_barrel", bore, "crusty_chunks:medium_bored_barrel"),
            assembly("crusty_chunks:large_unbored_barrel", bore, "crusty_chunks:large_bored_barrel"),
            assembly("crusty_chunks:huge_unbored_barrel", bore, "crusty_chunks:huge_bored_barrel"),
            assembly("crusty_chunks:large_unbored_cannon_barrel", bore, "crusty_chunks:battle_cannon_barrel"),
            assembly("crusty_chunks:huge_unbored_cannon_barrel", bore, "crusty_chunks:artillery_barrel"),

            assembly("immersiveengineering:ingot_steel", press, "crusty_chunks:steelplate"),
            assembly("crusty_chunks:steelplate", press, "crusty_chunks:bent_component"),
            assembly("crusty_chunks:bent_component", press, "crusty_chunks:steel_tube"),
            assembly("minecraft:copper_ingot", press, "crusty_chunks:copper_plate"),
            assembly("crusty_chunks:brass_ingot", press, "crusty_chunks:brass_plate"),
            assembly("immersiveengineering:ingot_aluminum", press, "crusty_chunks:aluminum_plate"),

            assembly("crusty_chunks:cast_component", extruder, "crusty_chunks:steel_gear"),
            assembly("minecraft:oak_planks", extruder, "crusty_chunks:wood_component"),
            assembly("immersiveengineering:ingot_steel", extruder, "crusty_chunks:steel_wire"),
            assembly("crusty_chunks:steel_wire", extruder, "crusty_chunks:steel_spring"),
            assembly("minecraft:copper_ingot", extruder, "crusty_chunks:copper_wire"),
            assembly("crusty_chunks:copper_wire", extruder, "crusty_chunks:copper_coil"),

            assembly("crusty_chunks:cast_component", shear, "crusty_chunks:steel_gear"),
            assembly("crusty_chunks:bent_component", shear, "crusty_chunks:cut_component"),
            assembly("crusty_chunks:copper_plate", shear, "crusty_chunks:copper_wire"),
            assembly("crusty_chunks:steelplate", shear, "crusty_chunks:steel_wire")
        ));
        registry.addRecipes(ASSEMBLY_TYPE, assembly);

        List<CentrifugeRecipe> centrifuge = collect(Stream.of(
            centrifuge("crusty_chunks:filtered_pyrochlore_dust", "crusty_chunks:niobium_tiny_dust"),
            centrifuge("crusty_chunks:aluminate_dust", "crusty_chunks:aluminum_tiny_dust"),
            centrifuge("crusty_chunks:uranium_neutral_dust", "crusty_chunks:uranium_enriched_tiny_dust"),
            centrifuge("crusty_chunks:lithium_dust", "crusty_chunks:tiny_lithium_deuteride")
        ));
        registry.addRecipes(CENTRIFUGE_TYPE, centrifuge);

        List<FabricatorRecipe> fabricatorRecipes = collect(Stream.of(
            fabricator("crusty_chunks:advanced_alloy_ingot", "crusty_chunks:advanced_alloy_component", fabricator),
            fabricator("crusty_chunks:aluminum_block", "crusty_chunks:precision_component", fabricator)
        ));
        registry.addRecipes(FABRICATOR_TYPE, fabricatorRecipes);

        List<DigesterRecipe> digesterRecipes = collect(Stream.of(
            digester("crusty_chunks:pyrochlore_dust", "crusty_chunks:filtered_pyrochlore_dust"),
            digester("crusty_chunks:bauxite_dust", "crusty_chunks:aluminate_dust")
        ));
        registry.addRecipes(DIGESTER_TYPE, digesterRecipes);

        List<CircuitFabricatorRecipe> circuitRecipes = collect(Stream.of(
            circuit("crusty_chunks:unfabricated_tech_component", "crusty_chunks:tech_component")
        ));
        registry.addRecipes(CIRCUIT_FABRICATOR_TYPE, circuitRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        addCatalyst(registry, "crusty_chunks:foundry", FOUNDRY_TYPE);
        addCatalyst(registry, "crusty_chunks:assembly_machine", ASSEMBLY_TYPE);
        addCatalyst(registry, "crusty_chunks:assembly_centrifuge_middle", CENTRIFUGE_TYPE);
        addCatalyst(registry, "crusty_chunks:assembly_mechanical_fabricator", FABRICATOR_TYPE);
        addCatalyst(registry, "crusty_chunks:bauxite_digester", DIGESTER_TYPE);
        addCatalyst(registry, "crusty_chunks:assembly_circuit_fabricator", CIRCUIT_FABRICATOR_TYPE);
    }

    // Helpers
    private static Optional<FoundryRecipe> foundry(String templateId, String materialId, int materialCount, String resultId) {
        Optional<Ingredient> template = ingOpt(templateId);
        Optional<Ingredient> material = ingOpt(materialId, materialCount);
        Optional<ItemStack> result = stackOpt(resultId);
        if (template.isEmpty() || material.isEmpty() || result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new FoundryRecipe(template.get(), material.get(), result.get()));
    }

    private static Optional<AssemblyRecipe> assembly(String inputId, Optional<Ingredient> head, String resultId) {
        Optional<Ingredient> input = ingOpt(inputId);
        Optional<ItemStack> result = stackOpt(resultId);
        if (input.isEmpty() || head.isEmpty() || result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new AssemblyRecipe(input.get(), head.get(), result.get()));
    }

    private static Optional<CentrifugeRecipe> centrifuge(String inputId, String outputId) {
        Optional<Ingredient> input = ingOpt(inputId);
        Optional<ItemStack> output = stackOpt(outputId);
        if (input.isEmpty() || output.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new CentrifugeRecipe(input.get(), output.get()));
    }

    private static Optional<FabricatorRecipe> fabricator(String inputId, String outputId, Optional<Ingredient> machine) {
        Optional<Ingredient> input = ingOpt(inputId);
        Optional<ItemStack> output = stackOpt(outputId);
        if (input.isEmpty() || output.isEmpty() || machine.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new FabricatorRecipe(input.get(), output.get()));
    }

    private static Optional<DigesterRecipe> digester(String inputId, String outputId) {
        Optional<Ingredient> input = ingOpt(inputId);
        Optional<ItemStack> output = stackOpt(outputId);
        if (input.isEmpty() || output.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new DigesterRecipe(input.get(), output.get()));
    }

    private static Optional<CircuitFabricatorRecipe> circuit(String inputId, String outputId) {
        Optional<Ingredient> input = ingOpt(inputId);
        Optional<ItemStack> output = stackOpt(outputId);
        if (input.isEmpty() || output.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new CircuitFabricatorRecipe(input.get(), output.get()));
    }

    private static Optional<ItemStack> stackOpt(String id) {
        return resolveItem(id).map(ItemStack::new);
    }

    private static Optional<Ingredient> ingOpt(String id) {
        return resolveItem(id).map(Ingredient::of);
    }

    private static Optional<Ingredient> ingOpt(String id, int count) {
        return resolveItem(id).map(item -> Ingredient.of(new ItemStack(item, count)));
    }

    private static Optional<Item> resolveItem(String id) {
        ResourceLocation loc = new ResourceLocation(id);
        Optional<Item> item = BuiltInRegistries.ITEM.getOptional(loc);
        if (item.isEmpty()) {
            WariumIntegrations.LOGGER.warn("Missing item for JEI recipe entry: {}", loc);
        }
        return item;
    }

    private static <T> List<T> collect(Stream<Optional<T>> stream) {
        return stream.flatMap(Optional::stream).toList();
    }

    private static void addCatalyst(IRecipeCatalystRegistration registry, String id, RecipeType<?> type) {
        stackOpt(id).ifPresent(stack -> registry.addRecipeCatalyst(stack, type));
    }
}
