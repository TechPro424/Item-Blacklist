package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.Id;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

@Mixin(PlayerInventory.class)
//Removes blacklisted items if transferred to player's inventory through means other than picking up
public abstract class PlayerInventoryMixin {
    //inject into addStack method
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/player/PlayerInventory;addStack(ILnet/minecraft/item/ItemStack;)I", cancellable = true)
    private void dontAddStack(int slot, ItemStack stack, CallbackInfoReturnable<Integer> callbackInfo) {

        String id = Id.getIdFromItemStack(stack);
        if(Config.configIncludesId(id)) callbackInfo.cancel();
    }

    //inject into insertStack method
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/player/PlayerInventory;insertStack(ILnet/minecraft/item/ItemStack;)Z", cancellable = true)
    private void dontInsertStack(int slot, ItemStack stack, CallbackInfoReturnable<Integer> callbackInfo) {

        String id = Id.getIdFromItemStack(stack);
        if(Config.configIncludesId(id)) callbackInfo.cancel();
    }

    //inject into setStack method
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/player/PlayerInventory;setStack(ILnet/minecraft/item/ItemStack;)V", cancellable = true)
    private void dontSetStack(int slot, ItemStack stack, CallbackInfo callbackInfo) {

        String id = Id.getIdFromItemStack(stack);
        if(Config.configIncludesId(id)) callbackInfo.cancel();
    }
}
