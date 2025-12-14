package in.techpro424.itemblacklist.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import in.techpro424.itemblacklist.config.Config;
import in.techpro424.itemblacklist.util.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

@Mixin(Entity.class)
//prevents entities from dropping blacklisted items upon death
public abstract class EntityItemDeleteMixin {
    
    @Inject(at = @At("HEAD"), method = "spawnAtLocation(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/entity/item/ItemEntity;", cancellable = true)
    private void dropStackCancel(ServerLevel world, ItemStack stack, Vec3 yOffset, CallbackInfoReturnable<ItemEntity> cir) {
        String id = Id.getIdFromItemStack(stack);

        if(Config.configIncludesId(id)) cir.cancel();

    }
}
