package com.example.wariumintergrations;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.patchouli.api.PatchouliAPI;
import vazkii.patchouli.api.PatchouliAPI.IPatchouliAPI;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;

public final class PatchouliCompat {
    public static final ResourceLocation BOOK_ID = new ResourceLocation(WariumIntegrations.MODID, "integration_manual");
    private static final String PATCHOULI_ID = "patchouli";
    private static final ResourceLocation PATCHOULI_BOOK_ITEM = new ResourceLocation(PATCHOULI_ID, "guide_book");
    private static final List<String> BOOK_FILES = List.of(
            "book.json",
            "categories/overview.json",
            "categories/assembly.json",
            "categories/processing.json",
            "categories/nuclear.json",
            "categories/addons.json",
            "entries/overview/welcome.json",
            "entries/overview/contents.json",
            "entries/assembly/foundry.json",
            "entries/assembly/assembly_machine.json",
            "entries/assembly/assembly_depot.json",
            "entries/assembly/assembly_fabricator.json",
            "entries/processing/centrifuge.json",
            "entries/processing/digester.json",
            "entries/processing/circuit_fabricator.json",
            "entries/processing/crusher.json",
            "entries/nuclear/reactor_overview.json",
            "entries/nuclear/standard_reactor.json",
            "entries/nuclear/advanced_reactor.json",
            "entries/nuclear/fuel_rods.json",
            "entries/nuclear/breeder_reactor.json",
            "entries/nuclear/cooling_systems.json",
            "entries/nuclear/safety_containment.json",
            "entries/nuclear/uranium_enrichment.json",
            "entries/addons/addon_index.json"
    );

    private PatchouliCompat() { }

    public static boolean isInstalled() {
        return ModList.get().isLoaded(PATCHOULI_ID);
    }

    public static boolean openBook() {
        if (!isInstalled()) {
            WariumIntegrations.LOGGER.warn("Patchouli not detected; cannot open book {}", BOOK_ID);
            return false;
        }
        Boolean opened = DistExecutor.safeCallWhenOn(Dist.CLIENT, ClientOpen::new);
        if (!Boolean.TRUE.equals(opened)) {
            WariumIntegrations.LOGGER.warn("Patchouli detected but openBook failed for {}", BOOK_ID);
        }
        return Boolean.TRUE.equals(opened);
    }

    public static ItemStack createManualStack() {
        if (!isInstalled()) {
            return ItemStack.EMPTY;
        }
        Item item = ForgeRegistries.ITEMS.getValue(PATCHOULI_BOOK_ITEM);
        if (item == null) {
            WariumIntegrations.LOGGER.warn("Patchouli book item {} is missing; cannot create manual stack", PATCHOULI_BOOK_ITEM);
            return ItemStack.EMPTY;
        }
        ItemStack stack = new ItemStack(item);
        stack.getOrCreateTag().putString("patchouli:book", BOOK_ID.toString());
        return stack;
    }

    @OnlyIn(Dist.CLIENT)
    private static final class ClientOpen implements DistExecutor.SafeCallable<Boolean> {
        @Override
        public Boolean call() {
            try {
                IPatchouliAPI api = PatchouliAPI.get();
                if (api.isStub()) {
                    WariumIntegrations.LOGGER.warn("Patchouli API is stubbed; client mod may be missing/disabled. Cannot open {}", BOOK_ID);
                    return false;
                }

                var stack = api.getBookStack(BOOK_ID);
                if (stack.isEmpty()) {
                    WariumIntegrations.LOGGER.warn("Patchouli book data not found for {}. Is the data pack loaded on the client?", BOOK_ID);
                    return false;
                }

                api.openBookGUI(BOOK_ID);
                return true;
            } catch (Throwable t) {
                WariumIntegrations.LOGGER.warn("Failed to open Patchouli manual {}", BOOK_ID, t);
                return false;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void extractBookToGameDir() {
        try {
            Path base = FMLPaths.GAMEDIR.get().resolve("patchouli_books")
                    .resolve(BOOK_ID.getNamespace())
                    .resolve(BOOK_ID.getPath());
            if (Files.exists(base)) {
                try (java.util.stream.Stream<Path> paths = Files.walk(base)) {
                    paths.sorted(Comparator.reverseOrder())
                            .forEach(path -> {
                                try {
                                    Files.delete(path);
                                } catch (Exception e) {
                                    WariumIntegrations.LOGGER.warn("Failed to delete old Patchouli file {}", path, e);
                                }
                            });
                }
            }
            for (String file : BOOK_FILES) {
                String resourcePath = "data/" + BOOK_ID.getNamespace() + "/patchouli_books/" + BOOK_ID.getPath() + "/" + file;
                try (InputStream in = PatchouliCompat.class.getClassLoader().getResourceAsStream(resourcePath)) {
                    if (in == null) {
                        WariumIntegrations.LOGGER.warn("Missing embedded Patchouli resource: {}", resourcePath);
                        continue;
                    }
                    Path target = base.resolve(file);
                    Files.createDirectories(target.getParent());
                    Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (Exception e) {
            WariumIntegrations.LOGGER.warn("Failed to extract Patchouli book to disk", e);
        }
    }

}
