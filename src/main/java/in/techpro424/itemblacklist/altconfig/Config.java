package in.techpro424.itemblacklist.altconfig;

import java.util.ArrayList;
import java.util.Arrays;

import in.techpro424.itemblacklist.util.FormatArrayList;

public class Config {
    
    ArrayList<String> itemBlacklist = new ArrayList<String>(Arrays.asList("mod_id:item_id","mod_id:item2_id"));

    static Config instance = JsonOperations.loadConfigFromFile();

    public static void addIdToConfig(String id) {
        if(configIncludesId(id)) return;
        instance.itemBlacklist.add(id);
        JsonOperations.writeConfig(true);
    }

    public static void removeIdFromConfig(String id) {
        if(!configIncludesId(id)) return;
        instance.itemBlacklist.remove(id);
        JsonOperations.writeConfig(true);
    }

    public static String listConfig() {
        return FormatArrayList.formatArrayList(instance.itemBlacklist);
    }

    public static boolean configIncludesId(String id) {
        return instance.itemBlacklist.contains(id);
    }

}
