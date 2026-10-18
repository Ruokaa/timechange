package dev.ruoka.time.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.ruoka.time.config.Configuration;
import net.minecraft.client.ClientClockManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientClockManager.class)
public class MixinClientClockManager {

    @ModifyReturnValue(method = "getTotalTicks", at = @At("RETURN"))
    private long time$overrideTime(long original){
        if (!Configuration.isEnabled()){
            return original;
        }
        return time$modifyTime(original);
    }

    @Unique
    public long time$modifyTime(long realTicks) {
        if (!Configuration.isEnabled()) {
            return realTicks;
        }
        return Math.floorDiv(realTicks, 24000L) * 24000L + Configuration.getTimeTicks();
    }
}
