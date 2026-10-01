package in.techpro424.itemblacklist.mixin;

import net.minecraft.util.Prediction;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import in.techpro424.itemblacklist.config.Config;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;



@Mixin(LivingEntity.class)
//Prevents players from dropping blacklisted items
public abstract class EntityDropItemDeleteMixin {

    @Inject(at = @At("HEAD"), method = "drop(Lnet/minecraft/world/item/ItemStack;ZLnet/minecraft/util/Prediction;)Lnet/minecraft/world/entity/item/ItemEntity;", cancellable = true)
    private void deleteItem(ItemStack itemStack, boolean thrownFromHand, Prediction prediction, CallbackInfoReturnable<ItemEntity> cir) {
        
        if(Config.configIncludesItem(itemStack)) cir.cancel();

    }
    
}
