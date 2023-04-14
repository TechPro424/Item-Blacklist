package in.techpro424.itemblacklist.util;

import net.minecraft.item.ItemStack;

public class DeleteStack {
    public static void deleteStack(ItemStack stack) {
        stack.decrement(stack.getCount());
    }
    
    public static void deleteStackOfId(ItemStack stack, String idToDelete) {
        String id = Id.getIdFromItemStack(stack);

        if(id.equals(idToDelete)) {
            deleteStack(stack);
        }
    }
}
