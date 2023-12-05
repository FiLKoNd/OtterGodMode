package dev.otter.godmode;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;

import java.util.ArrayList;
import java.util.List;

public class OtterGodMode implements ModInitializer {
    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static List<TeleportConfirmC2SPacket> packets = new ArrayList<>();
    public static boolean isGodModEnabled = false;

    @Override
    public void onInitialize() {
    }
}
