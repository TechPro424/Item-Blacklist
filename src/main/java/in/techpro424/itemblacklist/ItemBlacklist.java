package in.techpro424.itemblacklist;

import in.techpro424.itemblacklist.config.MyConfig;
import in.techpro424.itemblacklist.util.ModCommandRegister;
import net.fabricmc.api.ModInitializer;

public class ItemBlacklist implements ModInitializer {
    public static MyConfig CONFIG = new MyConfig();

    @Override
    public void onInitialize() {
        
        CONFIG.load();
        ModCommandRegister.registerCommands();
    }
    
}
