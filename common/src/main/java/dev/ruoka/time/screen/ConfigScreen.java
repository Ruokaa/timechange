package dev.ruoka.time.screen;

import dev.ruoka.time.config.Configuration;
import dev.ruoka.time.screen.components.IntSlider;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {

    private static final Component TITLE = Component.literal("Time Changer");

    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    private final Screen parent;

    public ConfigScreen(Screen parent) {
        super(TITLE);
        this.parent = parent;
    }

    @Override
    protected void init() {
        layout.addTitleHeader(TITLE, font);

        LinearLayout contents = layout.addToContents(LinearLayout.vertical().spacing(8));

        contents.addChild(CycleButton.onOffBuilder(Configuration.isEnabled())
                .create(Component.literal("Enabled"), ((_, aBoolean) -> Configuration.setEnabled(aBoolean))));

        contents.addChild(new IntSlider(Component.literal("Time"), 0, 23999, 1,
                Configuration.getTimeTicks(), Configuration::setTimeTicks));

        layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, b -> onClose()).width(200).build());

        layout.visitWidgets(this::addRenderableWidget);
        repositionElements();
    }

    @Override
    protected void repositionElements() {
        layout.arrangeElements();
    }

    @Override
    public void onClose() {
        minecraft.gui.setScreen(parent);
    }

    @Override
    public void removed() {
        Configuration.save();
    }
}
