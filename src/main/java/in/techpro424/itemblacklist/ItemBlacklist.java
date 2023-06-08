package in.techpro424.itemblacklist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.techpro424.itemblacklist.altconfig.JsonOperations;
import in.techpro424.itemblacklist.config.MyConfig;
import in.techpro424.itemblacklist.util.ModCommandRegister;
import net.fabricmc.api.ModInitializer;

public class ItemBlacklist implements ModInitializer {
    public static final String MOD_ID = "item-blacklist";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static MyConfig CONFIG = new MyConfig();

    @Override
    public void onInitialize() {
        
        JsonOperations.loadConfigFromFile();
        CONFIG.load();
        ModCommandRegister.registerCommands();
    }
    
}
