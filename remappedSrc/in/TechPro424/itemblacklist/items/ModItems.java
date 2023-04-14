package in.TechPro424.itemblacklist.items;

import in.TechPro424.itemblacklist.ItemBlacklist;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {

    public static final Item RAW_TANZANITE = registerItem("raw_tanzanite", 
    new Item(new FabricItemSettings().maxCount(0).rarity(Rarity.EPIC)));

    /*v
     * how maxCount: 0 behaves
     * you can pickup the item from the ground
     * you can throw the item on to the ground
     * you cannot put it in chests or any other containers
     * you cannot move it to any other inventory slot from the one in which it appeared when it was picked up
     */

    public static void addItemToGroup(Item item) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(item));
    }

    private static Item registerItem(String name, Item item) {
        Item item2;
        item2 = Registry.register(Registries.ITEM, new Identifier(ItemBlacklist.MOD_ID, name), item);
        addItemToGroup(item2);
        return item2;
    }
    

    public static void registerModItems() {
        ItemBlacklist.LOGGER.debug("Registering mod items for " + ItemBlacklist.MOD_ID);
    }
}
