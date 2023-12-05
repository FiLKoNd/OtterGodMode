package dev.otter.godmode.mixin;

import dev.otter.godmode.OtterGodMode;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static dev.otter.godmode.OtterGodMode.mc;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin extends Screen {

    protected InventoryScreenMixin(Text title){
        super(title);
    }

    @Inject(method = "init", at = @At("HEAD"))
    public void onInit(CallbackInfo ci){
        ButtonWidget toggle = new ButtonWidget(1, 1, 100, 20, Text.of("GodMod: " + (OtterGodMode.isGodModEnabled ? "On" : "Off")), b -> {
            OtterGodMode.isGodModEnabled = !OtterGodMode.isGodModEnabled;
            if (mc.player != null) {
                mc.openScreen(new InventoryScreen(mc.player));
            }
            if (!OtterGodMode.isGodModEnabled && !OtterGodMode.packets.isEmpty()) {
                Objects.requireNonNull(mc.getNetworkHandler()).sendPacket(OtterGodMode.packets.get(OtterGodMode.packets.size() - 1));
            }
        });
        this.addButton(toggle);
    }
}