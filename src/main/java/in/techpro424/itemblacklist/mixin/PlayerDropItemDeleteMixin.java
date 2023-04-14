package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import in.techpro424.itemblacklist.ItemBlacklist;
import in.techpro424.itemblacklist.util.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;



@Mixin(PlayerEntity.class)
//Prevents players from dropping blacklisted items
public abstract class PlayerDropItemDeleteMixin {

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/player/PlayerEntity;dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", cancellable = true)
    private void deleteItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> ci) {
        
        String id = Id.getIdFromItemStack(stack);
        if(ItemBlacklist.CONFIG.configIncludesId(id)) ci.cancel();

    }
    
}
