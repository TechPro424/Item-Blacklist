package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.Id;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

@Mixin(Inventory.class)
//Removes blacklisted items if transferred to player's inventory through means other than picking up
public abstract class InventoryMixin {
    //inject into addStack method
    @Inject(at = @At("HEAD"), method = "addResource(ILnet/minecraft/world/item/ItemStack;)I", cancellable = true)
    private void dontAddStack(int slot, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        String id = Id.getIdFromItemStack(stack);
        if(Config.configIncludesId(id)) cir.cancel();

    }

    //inject into insertStack method
    @Inject(at = @At("HEAD"), method = "add(ILnet/minecraft/world/item/ItemStack;)Z", cancellable = true)
    private void dontInsertStack(int slot, ItemStack stack, CallbackInfoReturnable<Integer> cir) {

        String id = Id.getIdFromItemStack(stack);


        if(Config.configIncludesId(id)) cir.cancel();
    }

    //inject into setStack method
    @Inject(at = @At("HEAD"), method = "setItem(ILnet/minecraft/world/item/ItemStack;)V", cancellable = true)
    private void dontSetStack(int slot, ItemStack stack, CallbackInfo ci) {

        String id = Id.getIdFromItemStack(stack);
        

        if(Config.configIncludesId(id)) ci.cancel();
    }
}
