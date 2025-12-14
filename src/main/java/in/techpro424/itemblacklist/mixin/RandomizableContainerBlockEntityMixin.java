package in.techpro424.itemblacklist.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.Id;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;

@Mixin(RandomizableContainerBlockEntity.class)
public abstract class RandomizableContainerBlockEntityMixin {
    /*
    * Prevents players from placing blacklisted items in containers
    */
    @Inject(at = @At("HEAD"), method = "setItem(ILnet/minecraft/world/item/ItemStack;)V", cancellable = true)
    public void dontSetStack(int slot, ItemStack stack, CallbackInfo ci) {
        String id = Id.getIdFromItemStack(stack);
        if(Config.configIncludesId(id)) ci.cancel();
    }
}
