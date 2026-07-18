package in.techpro424.itemblacklist.config;

import in.techpro424.itemblacklist.util.Id;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class Config {
    
    ArrayList<String> blacklist = new ArrayList<>(Arrays.asList("mod_id:item_id","mod_id:item2_id"));

    static Config instance = JsonOperations.loadConfigFromFile();

    public static void addIdToConfig(String id) {
        if(configIncludesId(id)) return;
        instance.blacklist.add(id);
        JsonOperations.writeConfig(true);
    }

    public static void removeIdFromConfig(String id) {
        if(!configIncludesId(id)) return;
        instance.blacklist.remove(id);
        JsonOperations.writeConfig(true);
    }

    public static void clearBlacklist() {
        instance.blacklist.clear();
        JsonOperations.writeConfig(true);
    }

    public static ArrayList<String> getBlacklist() {
        return (ArrayList<String>) instance.blacklist.clone();
    }

    public static boolean configIncludesId(String id) {
        return instance.blacklist.contains(id);
    }

    public static boolean configIncludesItem(Item item) {
        return configIncludesId(Id.getIdFromItem(item));
    }

    public static boolean configIncludesItem(ItemStack stack) {
        return configIncludesItem(stack.getItem());
    }

}
