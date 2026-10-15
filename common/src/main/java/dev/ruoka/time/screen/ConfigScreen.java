package dev.ruoka.time.screen;

import dev.ruoka.time.config.Configuration;
import dev.ruoka.time.screen.components.IntSlider;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ConfigScreen extends Screen {

    private static final Component TITLE = Component.literal("Time Changer");
    private static final Identifier PANEL = Identifier.withDefaultNamespace("toast/now_playing");
    private static final int PADDING = 8;
    private static final int TITLE_HEIGHT = 14;
    private static final int TITLE_COLOR = 0xFFFFFFFF;

    private final LinearLayout layout = LinearLayout.vertical().spacing(4);
    private final Screen parent;

    public ConfigScreen(Screen parent) {
        super(TITLE);
        this.parent = parent;
    }

    @Override
    protected void init() {
        layout.addChild(CycleButton.onOffBuilder(Configuration.isEnabled())
                .create(Component.literal("Enabled"), ((_, aBoolean) -> Configuration.setEnabled(aBoolean))));

        layout.addChild(new IntSlider(Component.literal("Time"), 0, 23999, 1,
                Configuration.getTimeTicks(), Configuration::setTimeTicks));

        layout.addChild(Button.builder(CommonComponents.GUI_DONE, b -> onClose()).width(150).build(),
                settings -> settings.paddingTop(4));

        layout.visitWidgets(this::addRenderableWidget);
        repositionElements();
    }

    @Override
    protected void repositionElements() {
        layout.arrangeElements();
        FrameLayout.centerInRectangle(layout, 0, TITLE_HEIGHT, width, height - TITLE_HEIGHT);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PANEL,
                layout.getX() - PADDING, layout.getY() - TITLE_HEIGHT - PADDING,
                layout.getWidth() + PADDING * 2, layout.getHeight() + TITLE_HEIGHT + PADDING * 2);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        graphics.text(font, TITLE, (width - font.width(TITLE)) / 2, layout.getY() - TITLE_HEIGHT, TITLE_COLOR, false);
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
