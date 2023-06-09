package in.techpro424.itemblacklist.altconfig;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import in.techpro424.itemblacklist.ItemBlacklist;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import net.fabricmc.loader.api.FabricLoader;

import com.google.gson.Gson;

public class JsonOperations {
    public static Config config;
    private final static Path configPath = FabricLoader.getInstance().getConfigDir().resolve("item-blacklist.jsonc");
    private final static Gson gson = new GsonBuilder().registerTypeAdapter(Config.class, new ConfigTypeAdapter()).setPrettyPrinting().create();

    public static Config loadConfigFromFile() {
        if (Files.notExists(configPath)) writeConfig(false);

        try (JsonReader reader = new JsonReader(Files.newBufferedReader(configPath))) {
            config = gson.fromJson(reader, Config.class);

        } catch (Exception e) {
            ItemBlacklist.LOGGER.info("Error while loading config, maybe you should check the mod's config file to see if it has any syntax errors.");
            e.printStackTrace();
            throw new RuntimeException("(timberjack-refabricated) Error while loading config, maybe you should check the mod's config file to see if it has any syntax errors.");
        }

        ItemBlacklist.LOGGER.info("Config loaded successfully");
        return config;
    }

    public static void writeConfig(boolean toSave) {
        Config configToWrite = new Config();

        if(toSave) configToWrite = Config.instance;

        try {
            Files.deleteIfExists(configPath);
            Files.write(configPath, gson.toJson(configToWrite).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            ItemBlacklist.LOGGER.info("Error while writing config"); //TODO ask users to report on gh or discord
            e.printStackTrace();
        }
        
    }
}
