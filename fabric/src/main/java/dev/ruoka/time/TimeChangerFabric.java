package dev.ruoka.time;

import dev.ruoka.time.command.TimeCommand;
import dev.ruoka.time.keybind.Keybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;

public class TimeChangerFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        TimeChanger.init(FabricLoader.getInstance().getConfigDir());
        ClientTickEvents.END_CLIENT_TICK.register(TimeChanger::tick);
        KeyMapping.Category.register(Keybinds.getKeyCategory().id());
        KeyMappingHelper.registerKeyMapping(Keybinds.getToggleKey());
        KeyMappingHelper.registerKeyMapping(Keybinds.getScrollKey());
        KeyMappingHelper.registerKeyMapping(Keybinds.getConfigKey());
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, _) -> TimeCommand.register(dispatcher));
    }
}
