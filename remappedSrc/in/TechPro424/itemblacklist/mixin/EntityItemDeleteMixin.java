package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;

@Mixin(Entity.class)
//prevents entities from dropping blacklisted items upon death
public abstract class EntityItemDeleteMixin {
    
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/Entity;dropStack(Lnet/minecraft/item/ItemStack;F)Lnet/minecraft/entity/ItemEntity;", cancellable = true)
    private void dropStackCancel(ItemStack stack, float yOffset, CallbackInfoReturnable<ItemEntity> cir) {
        String id = Id.getIdFromItemStack(stack);
        String dimensionName = ((Entity)(Object)this).method_48926().getRegistryKey().getValue().toString();

        if(Config.configIncludesId(id, Formatting.formatDimension(dimensionName))) cir.cancel();

    }
}
