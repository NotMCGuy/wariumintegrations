package com.example.wariumintergrations;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WariumIntegrations.MODID, value = Dist.CLIENT)
public final class ClientNetworkEvents {
    private ClientNetworkEvents() { }

    @SubscribeEvent
    public static void blockListedServers(ClientPlayerNetworkEvent.LoggingIn event) {
        if (event.getConnection() == null) {
            return;
        }
        if (ConnectionBlocklist.isBlockedServerEndpoint(event.getConnection().getRemoteAddress())) {
            event.getConnection().disconnect(DisconnectMessages.random());
        }
    }
}
