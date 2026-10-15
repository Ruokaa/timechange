package dev.ruoka.time;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    public static final String MOD_ID = "timechanger";
    public static final String MOD_NAME = "TimeChanger";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static Identifier identifier(String path){
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}