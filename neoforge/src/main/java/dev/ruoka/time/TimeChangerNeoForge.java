package dev.ruoka.time;

import dev.ruoka.time.command.TimeCommand;
import dev.ruoka.time.keybind.Keybinds;
import dev.ruoka.time.screen.ConfigScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
public class TimeChangerNeoForge {

    public TimeChangerNeoForge(IEventBus eventBus, ModContainer container) {
        TimeChanger.init(FMLPaths.CONFIGDIR.get());

        container.registerExtensionPoint(IConfigScreenFactory.class, (_, parent) -> new ConfigScreen(parent));

        eventBus.addListener(RegisterKeyMappingsEvent.class, event -> {
            event.registerCategory(Keybinds.getKeyCategory());
            event.register(Keybinds.getToggleKey());
            event.register(Keybinds.getScrollKey());
            event.register(Keybinds.getConfigKey());
        });

        NeoForge.EVENT_BUS.addListener(ClientTickEvent.Post.class, _ -> {
            TimeChanger.tick(Minecraft.getInstance());
        });

        NeoForge.EVENT_BUS.addListener(RegisterClientCommandsEvent.class, event -> {
            TimeCommand.register(event.getDispatcher());
        });
    }
}