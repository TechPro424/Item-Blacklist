package in.techpro424.itemblacklist.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.Id;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;
import java.util.stream.Collectors;

@Mixin(CreativeModeTab.class)
public abstract class CreativeModeTabMixin {
    @WrapOperation(method = "buildContents", at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/CreativeModeTab;displayItems:Ljava/util/Collection;", opcode = Opcodes.PUTFIELD))
    private void removeBlacklistedItems(CreativeModeTab instance, Collection<ItemStack> newValue, Operation<Void> original, @Local(name = "displayList") CreativeModeTab.ItemDisplayBuilder displayList) {
        ((CreativeModeTabAccessor) instance).setDisplayItems(displayList.tabContents.stream().filter(itemStack -> !Config.configIncludesItem(itemStack)).collect(Collectors.toSet()));
        ((CreativeModeTabAccessor) instance).setDisplayItemsSearchTab(displayList.searchTabContents.stream().filter(itemStack -> !Config.configIncludesItem(itemStack)).collect(Collectors.toSet()));
    }
}
