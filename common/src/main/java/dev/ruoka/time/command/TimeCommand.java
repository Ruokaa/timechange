package dev.ruoka.time.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import dev.ruoka.time.TimeChanger;
import net.minecraft.client.Minecraft;

public class TimeCommand {

    public static <S> void register(CommandDispatcher<S> dispatcher){
        dispatcher.register(LiteralArgumentBuilder.<S>literal("timechanger")
                .then(LiteralArgumentBuilder.<S>literal("toggle")
                        .executes(ctx -> toggle()))
                .then(LiteralArgumentBuilder.<S>literal("menu")
                        .executes(ctx -> menu()))
                .then(LiteralArgumentBuilder.<S>literal("set")
                        .then(RequiredArgumentBuilder.<S, Integer>argument("ticks", IntegerArgumentType.integer(0, 23999))
                                .executes(ctx -> set(IntegerArgumentType.getInteger(ctx, "ticks"))))));
    }

    private static int toggle() {
        TimeChanger.toggleTime(Minecraft.getInstance());
        return Command.SINGLE_SUCCESS;
    }

    private static int menu() {
        Minecraft minecraft = Minecraft.getInstance();
        // open on the next frame, otherwise the chat screen closing after the command closes this too
        minecraft.schedule(() -> TimeChanger.openConfigScreen(minecraft));
        return Command.SINGLE_SUCCESS;
    }

    private static int set(int ticks) {
        TimeChanger.setTime(Minecraft.getInstance(), ticks);
        return Command.SINGLE_SUCCESS;
    }
}
