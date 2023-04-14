package in.techpro424.itemblacklist.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

public class Id {
    public static String getIdFromItem(Item item) {
        String id = Registries.ITEM.getId(item).toString();
        return id;
    }
    
    public static String getIdFromItemStack(ItemStack stack) {
        Item item = stack.getItem();
        String id = getIdFromItem(item);
        return id;
    }

    public static void logIdOfItem(Item item) {
        String id = getIdFromItem(item);
        Logging.LOGGER.info("The ID of the item you just dropped is " + id);
    }

    public static void logIdOfStack(ItemStack stack) {
        String id = getIdFromItemStack(stack);
        Logging.LOGGER.info("The ID of the item you just dropped is " + id);
    }
}
