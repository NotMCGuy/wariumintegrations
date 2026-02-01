package com.example.wariumintergrations;

import com.example.wariumintergrations.recipes.ModRecipeTypes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(WariumIntegrations.MODID)
public class WariumIntegrations {
    public static final String MODID = "warium_integrations";
    public static final Logger LOGGER = LoggerFactory.getLogger(WariumIntegrations.class);

    public WariumIntegrations() {
        // Patchouli now loads book assets directly from the mod's resource pack.
        ModRecipeTypes.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
