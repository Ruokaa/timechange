package dev.ruoka.time.mixin;

import dev.ruoka.time.TimeChanger;
import dev.ruoka.time.keybind.Keybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MixinMouseHandler {

    @Inject(
            method = "onScroll",
            at = @At("HEAD"),
            cancellable = true
    )
    private void time$scrollTime(long handle, double xoffset, double yoffset, CallbackInfo ci) {
        if (Keybinds.getScrollKey().isDown()){
            TimeChanger.scrollTime(Minecraft.getInstance(), yoffset);
            ci.cancel();
        }
    }
}
