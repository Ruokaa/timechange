package dev.ruoka.time.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.ruoka.time.config.Configuration;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Level.class)
public class MixinLevel {

    @ModifyReturnValue(method = "getDayTime", at = @At("RETURN"))
    private long time$overrideTime(long original){
        // Level is shared with the integrated server, only the client's view should change
        if (!Configuration.isEnabled() || !((Object) this instanceof ClientLevel)){
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
