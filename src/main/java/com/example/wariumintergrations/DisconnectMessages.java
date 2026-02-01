package com.example.wariumintergrations;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.network.chat.Component;

public final class DisconnectMessages {
    private static final List<String> MESSAGES = List.of(
            "Nope. You need permission to use this mod. Connection blocked. Bazinga!",
            "Access denied: You need permission to use this mod. Connection blocked.",
            "Hold up! You need permission to use this mod. Connection blocked.",
            "Call your local Plumber",
            "getsockop",
            "getsockop - @MC for details",
            "TTrain says no.",
            "Check your inbox!",
            "Don't tell lies. Don't steal work. Don't cheat. Don't be late. Be kind. - Mr. MC",
            "British humour is the best humour!"
    );

    private DisconnectMessages() { }

    public static Component random() {
        if (MESSAGES.isEmpty()) {
            return Component.literal("Connection blocked.");
        }
        int index = ThreadLocalRandom.current().nextInt(MESSAGES.size());
        return Component.literal(MESSAGES.get(index));
    }
}
