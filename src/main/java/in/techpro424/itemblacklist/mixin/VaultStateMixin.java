package in.techpro424.itemblacklist.mixin;

import in.techpro424.itemblacklist.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.vault.VaultState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VaultState.class)
public abstract class VaultStateMixin {
    @Inject(method = "ejectResultItem", at = @At("HEAD"), cancellable = true)
    private static void ejectResultItem(final ServerLevel serverLevel, final BlockPos pos, final ItemStack itemToEject, final float ejectionSoundProgress, CallbackInfo ci) {
        if (Config.configIncludesItem(itemToEject)) {
            serverLevel.levelEvent(3017, pos, 0);
            serverLevel.playSound(null, pos, SoundEvents.VAULT_REJECT_REWARDED_PLAYER, SoundSource.BLOCKS);
            ci.cancel();
        }
    }
}
