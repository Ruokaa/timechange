package dev.ruoka.time.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import dev.ruoka.time.Constants;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    private static boolean enabled;
    private static int timeTicks;

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static File configFile;

    public static void init(File path){
        configFile = new File(path, "timechanger.json");
        load();
    }

    public static void load(){
        if (!configFile.exists()){
            save();
            return;
        }

        try (FileReader reader = new FileReader(configFile)){
            ConfigData data = gson.fromJson(reader, ConfigData.class);

            if (data == null){
                return;
            }

            enabled = data.isEnabled;
            timeTicks = data.timeTicks;

        } catch (IOException | JsonParseException exception){
            Constants.LOG.error("Failed to load config: {}", exception.getMessage());
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(configFile)) {
            gson.toJson(new ConfigData(enabled, timeTicks), writer);
        } catch (IOException exception) {
            Constants.LOG.error("Failed to save config: {}", exception.getMessage());
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static int getTimeTicks() {
        return timeTicks;
    }

    public static void setEnabled(boolean value){
        enabled = value;
    }

    public static void setTimeTicks(int ticks){
        timeTicks = ticks;
    }

    public record ConfigData(boolean isEnabled, int timeTicks){}
}
