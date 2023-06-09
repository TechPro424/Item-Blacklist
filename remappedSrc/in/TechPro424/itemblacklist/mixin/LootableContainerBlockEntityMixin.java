package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.Id;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.item.ItemStack;

@Mixin(LootableContainerBlockEntity.class)
public abstract class LootableContainerBlockEntityMixin {
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/block/entity/LootableContainerBlockEntity;setStack(ILnet/minecraft/item/ItemStack;)V", cancellable = true)
    public void dontSetStack(int slot, ItemStack stack, CallbackInfo ci) {
        String id = Id.getIdFromItemStack(stack);
        if(Config.configIncludesId(id)) ci.cancel();
    }
}
