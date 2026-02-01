package com.example.wariumintergrations;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WariumIntegrations.MODID)
public final class CommonEvents {
    private static final String TAG_ROOT = WariumIntegrations.MODID + ":player";
    private static final String TAG_GIVEN = "manual_given";

    private CommonEvents() { }

    @SubscribeEvent
    public static void blockListedClients(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        if (ConnectionBlocklist.isBlockedClient(player.connection.connection.getRemoteAddress())) {
            player.connection.disconnect(DisconnectMessages.random());
        }
    }

    @SubscribeEvent
    public static void giveManualOnFirstJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !PatchouliCompat.isInstalled()) {
            return;
        }
        if (ConnectionBlocklist.isBlockedClient(player.connection.connection.getRemoteAddress())) {
            return;
        }

        CompoundTag persistent = player.getPersistentData().getCompound(ServerPlayer.PERSISTED_NBT_TAG);
        CompoundTag tag = persistent.contains(TAG_ROOT) ? persistent.getCompound(TAG_ROOT) : new CompoundTag();
        if (tag.getBoolean(TAG_GIVEN)) {
            return;
        }

        ItemStack manual = PatchouliCompat.createManualStack();
        if (manual.isEmpty()) {
            return;
        }

        boolean added = player.getInventory().add(manual);
        if (!added) {
            player.drop(manual, false);
        }

        tag.putBoolean(TAG_GIVEN, true);
        persistent.put(TAG_ROOT, tag);
        player.getPersistentData().put(ServerPlayer.PERSISTED_NBT_TAG, persistent);
    }
}
