package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

@Mixin(ItemEntity.class)
//Prevents players from picking up blacklisted items
public abstract class PlayerPickupMixin {
    @Shadow
    public abstract void setDespawnImmediately();

    @Shadow
    public abstract ItemStack getStack();

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/ItemEntity;onPlayerCollision(Lnet/minecraft/entity/player/PlayerEntity;)V", cancellable = true)
    private void deleteItem(PlayerEntity player, CallbackInfo ci) {

        ItemStack stack = this.getStack();
        String id = Id.getIdFromItemStack(stack);
       
        if(Config.configIncludesId(id)) {
            this.setDespawnImmediately();
            ci.cancel();
        }
    }
}
