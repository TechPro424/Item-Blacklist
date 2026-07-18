package in.techpro424.itemblacklist.mixin;

import in.techpro424.itemblacklist.config.Config;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultDispenseItemBehavior.class)
public abstract class DefaultDispenseItemBehaviorMixin {
    @Inject(method = "spawnItem", at = @At("HEAD"), cancellable = true)
    private static void spawnItem(final Level level, final ItemStack itemStack, final int accuracy, final Direction direction, final Position position, CallbackInfo ci) {

        if (Config.configIncludesItem(itemStack)) ci.cancel();
    }
}
