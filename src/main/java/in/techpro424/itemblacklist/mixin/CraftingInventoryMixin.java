package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.Id;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;

@Mixin(CraftingInventory.class)
public abstract class CraftingInventoryMixin {
    /*inject into setStack method
      prevents you from crafting blacklisted items  */
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/inventory/CraftingInventory;setStack(ILnet/minecraft/item/ItemStack;)V", cancellable = true)
    private void dontSetStack(int slot, ItemStack stack, CallbackInfo ci) {

        String id = Id.getIdFromItemStack(stack);
        if(Config.configIncludesId(id)) ci.cancel();
    }
}
