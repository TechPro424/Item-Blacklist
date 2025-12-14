package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Mixin(ItemEntity.class)
//Prevents players from picking up blacklisted items
public abstract class PlayerPickupMixin {
    @Shadow
    public abstract void makeFakeItem();

    @Shadow
    public abstract ItemStack getItem();

    @Inject(at = @At("HEAD"), method = "playerTouch(Lnet/minecraft/world/entity/player/Player;)V", cancellable = true)
    private void deleteItem(Player player, CallbackInfo ci) {

        ItemStack stack = this.getItem();
        String id = Id.getIdFromItemStack(stack);
       
        if(Config.configIncludesId(id)) {
            this.makeFakeItem();
            ci.cancel();
        }
    }
}
