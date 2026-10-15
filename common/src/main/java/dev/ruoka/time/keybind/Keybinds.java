package dev.ruoka.time.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import dev.ruoka.time.Constants;
import net.minecraft.client.KeyMapping;

public class Keybinds {
    private static final KeyMapping.Category KEY_CATEGORY = new KeyMapping.Category(Constants.identifier("main"));
    private static final KeyMapping TOGGLE_KEY = new KeyMapping("key.timechanger.toggle", InputConstants.KEY_N, KEY_CATEGORY);
    private static final KeyMapping SCROLL_KEY = new KeyMapping("key.timechanger.scroll", InputConstants.KEY_M, KEY_CATEGORY);
    private static final KeyMapping CONFIG_KEY = new KeyMapping("key.timechanger.config", InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);

    public static KeyMapping.Category getKeyCategory(){
        return KEY_CATEGORY;
    }

    public static KeyMapping getToggleKey(){
        return TOGGLE_KEY;
    }

    public static KeyMapping getScrollKey(){
        return SCROLL_KEY;
    }

    public static KeyMapping getConfigKey(){
        return CONFIG_KEY;
    }
}
