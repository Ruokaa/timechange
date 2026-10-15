package dev.ruoka.time.screen.components;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

import java.util.function.IntConsumer;

public class IntSlider extends AbstractSliderButton {

    private final Component name;
    private final int min;
    private final int max;
    private final int increment;
    private final IntConsumer onChange;
    private int current;

    public IntSlider(Component name, int min, int max, int increment, int initial, IntConsumer onChange) {
        super(0, 0, 150, 20, CommonComponents.EMPTY, Mth.clamp((initial - min) / (double) (max - min), 0.0, 1.0));
        this.name = name;
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.onChange = onChange;
        this.current = initial;
        updateMessage();
    }

    @Override
    protected void updateMessage() {
        setMessage(CommonComponents.optionNameValue(name, Component.literal(String.valueOf(current))));
    }

    @Override
    protected void applyValue() {
        int steps = (int) Math.round(value * (max - min) / increment);
        current = Math.min(max, min + steps * increment);
        onChange.accept(current);
    }
}
