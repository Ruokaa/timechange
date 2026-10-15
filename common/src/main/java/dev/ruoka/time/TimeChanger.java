package dev.ruoka.time;

import dev.ruoka.time.config.Configuration;
import dev.ruoka.time.keybind.Keybinds;
import dev.ruoka.time.screen.ConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.nio.file.Path;

public class TimeChanger {
    private static final int SCROLL_STEP = 200;

    public static void init(Path configDir) {
        Configuration.init(configDir.toFile());
    }

    public static void tick(Minecraft minecraft) {
        while (Keybinds.getToggleKey().consumeClick()){
            toggleTime(minecraft);
        }

        while (Keybinds.getConfigKey().consumeClick()){
            openConfigScreen(minecraft);
        }
    }

    public static void openConfigScreen(Minecraft minecraft){
        minecraft.gui.setScreen(new ConfigScreen(minecraft.gui.screen()));
    }

    public static void toggleTime(Minecraft minecraft){
        boolean enabled = !Configuration.isEnabled();
        Configuration.setEnabled(enabled);
        Configuration.save();
        minecraft.gui.hud.setOverlayMessage(Component.literal("Time Changer: " + (enabled ? "ON" : "OFF")), false);
    }

    public static void setTime(Minecraft minecraft, int ticks){
        Configuration.setTimeTicks(ticks);
        Configuration.setEnabled(true);
        Configuration.save();
        minecraft.gui.hud.setOverlayMessage(Component.literal("Time: " + ticks), false);
    }

    public static void scrollTime(Minecraft minecraft, double amount){
        int direction = (int) Math.signum(amount);
        setTime(minecraft, Math.floorMod(Configuration.getTimeTicks() + direction * SCROLL_STEP, 24000));
    }
}