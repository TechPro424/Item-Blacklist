package in.techpro424.itemblacklist.altconfig;

import java.util.ArrayList;
import java.util.Arrays;

import in.techpro424.itemblacklist.util.FormatArrayList;

public class Config {
    
    ArrayList<String> itemBlacklist = new ArrayList<String>(Arrays.asList("mod_id:item_id","mod_id:item2_id"));

    static Config instance = JsonOperations.loadConfigFromFile();

    public void addIdToConfig(String id) {
        if(configIncludesId(id)) return;
        itemBlacklist.add(id);
        JsonOperations.writeConfig(true);
    }

    public void removeIdFromConfig(String id) {
        if(!configIncludesId(id)) return;
        itemBlacklist.remove(id);
        JsonOperations.writeConfig(true);
    }

    public String listConfig() {
        return FormatArrayList.formatArrayList(itemBlacklist);
    }

    public static boolean configIncludesId(String id) {
        return instance.itemBlacklist.contains(id);
    }

}
