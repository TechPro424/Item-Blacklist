package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import in.techpro424.itemblacklist.ItemBlacklist;
import in.techpro424.itemblacklist.util.Id;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

@Mixin(Inventory.class)
public abstract interface InventoryMixin {
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/inventory/Inventory;setStack(ILnet/minecraft/item/ItemStack;)V", cancellable = true)
    private void dontSetStack(int slot, ItemStack stack, CallbackInfo ci) {

        String id = Id.getIdFromItemStack(stack);
        if(ItemBlacklist.CONFIG.configIncludesId(id)) ci.cancel();
    }
}
