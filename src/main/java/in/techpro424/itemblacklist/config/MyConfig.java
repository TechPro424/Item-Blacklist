package in.techpro424.itemblacklist.config;

import java.util.ArrayList;
import java.util.Arrays;

import in.techpro424.itemblacklist.util.FormatArrayList;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.data.Config;
import me.lortseam.completeconfig.data.ConfigOptions;

public class MyConfig extends Config {

    @ConfigEntry(comment = "The list of items to block.\nEach item must be separated by a comma, and must be in the following format.\n\"mod_id:item_id\"\nFor example, warped planks would be\"minecraft:warped_planks\"")
    public ArrayList<String> itemsToBlock = new ArrayList<String>(Arrays.asList("mod_id:item_id","mod_id:item2_id"));

    public void addIdToConfig(String id) {
        if(configIncludesId(id)) return;
        itemsToBlock.add(id);
        this.save();
    }

    public void removeIdFromConfig(String id) {
        if(!configIncludesId(id)) return;
        itemsToBlock.remove(id);
        this.save();
    }

    public String listConfig() {
        return FormatArrayList.formatArrayList(itemsToBlock);
    }

    public boolean configIncludesId(String id) {
        return itemsToBlock.contains(id);
    }

    public MyConfig() {
        super(ConfigOptions.mod("item-blacklist"));
    }
    
}

    