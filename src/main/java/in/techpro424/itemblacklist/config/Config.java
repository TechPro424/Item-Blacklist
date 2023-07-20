package in.techpro424.itemblacklist.config;

import java.util.ArrayList;
import java.util.Arrays;

import in.techpro424.itemblacklist.util.Formatting;

public class Config {
    
    ArrayList<String> globalBlacklist = new ArrayList<String>(Arrays.asList("mod_id:item_id","mod_id:item2_id"));
    ArrayList<String> overworldBlacklist =  new ArrayList<String>();
    ArrayList<String> netherBlacklist =  new ArrayList<String>();
    ArrayList<String> endBlacklist =  new ArrayList<String>();

    static Config instance = JsonOperations.loadConfigFromFile();

    public static void addIdToConfig(String id, String blacklist) {
        if(configIncludesId(id, blacklist)) return;

        switch(blacklist) {
            case "global" -> instance.globalBlacklist.add(id);
            case "overworld" -> instance.overworldBlacklist.add(id);
            case "nether" -> instance.netherBlacklist.add(id);
            case "end" -> instance.endBlacklist.add(id);

        };

        JsonOperations.writeConfig(true);
    }

    public static void removeIdFromConfig(String id, String blacklist) {
        if(!configIncludesId(id, blacklist)) return;
        
        switch(blacklist) {
            case "global" -> instance.globalBlacklist.remove(id);
            case "overworld" -> instance.overworldBlacklist.remove(id);
            case "nether" -> instance.netherBlacklist.remove(id);
            case "end" -> instance.endBlacklist.remove(id);

        };
        
        JsonOperations.writeConfig(true);
    }

    public static String listConfig(String blacklist) {
        return switch(blacklist) {
            case "global" -> Formatting.formatArrayList(instance.globalBlacklist);
            case "overworld" ->Formatting.formatArrayList(instance.overworldBlacklist);
            case "nether" -> Formatting.formatArrayList(instance.netherBlacklist);
            case "end" -> Formatting.formatArrayList(instance.endBlacklist);
            default -> "The given blacklist does not exist.";
        };
    }

    public static boolean configIncludesId(String id, String blacklist) {
        if(instance.globalBlacklist.contains(id)) return true;
        return switch(blacklist) {
            case "global" -> instance.globalBlacklist.contains(id);
            case "overworld" -> instance.overworldBlacklist.contains(id);
            case "nether" -> instance.netherBlacklist.contains(id);
            case "end" -> instance.endBlacklist.contains(id);
            default -> false;
        };

    }

}
