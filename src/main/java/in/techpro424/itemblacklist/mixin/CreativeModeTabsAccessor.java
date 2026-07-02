package in.techpro424.itemblacklist.mixin;

import net.minecraft.world.item.CreativeModeTabs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;

@Mixin(CreativeModeTabs.class)
public interface CreativeModeTabsAccessor {
    @Accessor("CACHED_PARAMETERS")
    static void setCachedParameters(ItemDisplayParameters value) {}
}
