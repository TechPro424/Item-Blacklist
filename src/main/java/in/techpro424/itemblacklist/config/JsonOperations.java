package in.techpro424.itemblacklist.config;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import in.techpro424.itemblacklist.ItemBlacklist;
import net.fabricmc.loader.api.FabricLoader;

//MAKE SURE THAT THE JANKSON DEPENDENCY IN BUILD.GRADLE IS ON THE LATEST VERSION
//CHECK https://central.sonatype.com/artifact/blue.endless/jankson/1.2.2/versions to see which version is the latest
//All other sources are probably outdated
public class JsonOperations {
    private static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve("item-blacklist.json5");
    private static final File configFile = configPath.toFile();
    private static Jankson jankson = Jankson.builder().build();

    public static Config loadConfigFromFile() {
        Config config;
        if (Files.notExists(configPath)) writeConfig(false);

        try {
            JsonObject configJson = jankson.load(configFile);
            config = jankson.fromJson(configJson, Config.class);

            return config;

        } catch (Exception e) {
            ItemBlacklist.LOGGER.info("Error while loading config, maybe you should check the mod's config file to see if it has any syntax errors.");
            e.printStackTrace();
            throw new RuntimeException("(item-blacklist) Error while loading config, maybe you should check the mod's config file to see if it has any syntax errors.");
        }   

        
    }

    public static void writeConfig(boolean toSave) {
        Config configToWrite = new Config();
        if(toSave) configToWrite = Config.instance;

        String result = jankson.toJson(configToWrite)   //The first call makes a JsonObject
        .toJson(true, true); //The second turns the JsonObject into a String -
                                                         //in this case, preserving comments and pretty-printing with newlines
        try {
            if(!configFile.exists()) configFile.createNewFile();
            FileOutputStream out = new FileOutputStream(configFile, false);

            out.write(result.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            ItemBlacklist.LOGGER.info("Error while writing config, create an issue on https://github.com/TechPro424/Item-Blacklist/issues/"); //TODO ask users to report on discord
            e.printStackTrace();
        }
    }

}
