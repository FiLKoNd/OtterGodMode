package dev.otter.godmode.mixin;

import dev.otter.godmode.OtterGodMode;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "send(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"),cancellable = true)
    private void onSendPacketPre(Packet<?> packet, CallbackInfo info) {
        if (OtterGodMode.isGodModEnabled && packet instanceof TeleportConfirmC2SPacket teleportConfirmC2SPacket) {
            OtterGodMode.packets.add(teleportConfirmC2SPacket);
            info.cancel();
        }
    }
}
