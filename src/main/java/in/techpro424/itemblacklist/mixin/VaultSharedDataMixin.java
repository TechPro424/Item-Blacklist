package in.techpro424.itemblacklist.mixin;

import in.techpro424.itemblacklist.config.Config;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.vault.VaultSharedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VaultSharedData.class)
public abstract class VaultSharedDataMixin {

    @Shadow protected abstract void markDirty();
    @Shadow private ItemStack displayItem;

    @Inject(method = "setDisplayItem", at = @At("HEAD"), cancellable = true)
    public void setDisplayItem(final ItemStack stack, final CallbackInfo ci) {
        if (Config.configIncludesItem(stack)) this.displayItem = new ItemStack(Items.BARRIER);
        this.markDirty();
        ci.cancel();
    }
}
