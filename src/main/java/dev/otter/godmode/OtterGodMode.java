package dev.otter.godmode;

import dev.otter.godmode.event.packet.PacketEvent;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class OtterGodMode implements ModInitializer {
    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static List<TeleportConfirmC2SPacket> packets = new ArrayList<>();
    public static boolean isGodModEnabled = false;
    public static final Logger LOGGER = LogManager.getLogger("GodMode");
    public static IEventBus EVENT_BUS = new EventBus();

    @Override
    public void onInitialize() {
        EVENT_BUS.registerLambdaFactory("dev.otter.godmode", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        EVENT_BUS.subscribe(this);
    }

    @EventHandler
    private void onPacketSend(PacketEvent.Send event) {
        if (isGodModEnabled && event.getPacket() instanceof TeleportConfirmC2SPacket packet) {
            packets.add(packet);
            event.cancel();
        }
    }
}
