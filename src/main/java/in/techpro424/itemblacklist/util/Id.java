package in.techpro424.itemblacklist.util;

import in.techpro424.itemblacklist.ItemBlacklist;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;

public class Id {
    public static String getIdFromItem(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString();
    }

    public static void logIdOfItem(Item item) {
        String id = getIdFromItem(item);
        ItemBlacklist.LOGGER.info("The ID of the item you just dropped is " + id);
    }

    public static void logIdOfStack(ItemStack stack) {
        String id = getIdFromItem(stack.getItem());
        ItemBlacklist.LOGGER.info("The ID of the item you just dropped is " + id);
    }
}
