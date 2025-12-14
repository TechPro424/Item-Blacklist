package in.techpro424.itemblacklist.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.Id;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;



@Mixin(LivingEntity.class)
//Prevents players from dropping blacklisted items
public abstract class EntityDropItemDeleteMixin {

    @Inject(at = @At("HEAD"), method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", cancellable = true)
    private void deleteItem(ItemStack stack, boolean dropAtSelf, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> ci) {
        
        String id = Id.getIdFromItemStack(stack);
        if(Config.configIncludesId(id)) ci.cancel();

    }
    
}
