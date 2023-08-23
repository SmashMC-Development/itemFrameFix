package me.paradis.fixitemframesmixin.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.world.TrackedEntity;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TrackedEntity.class, priority = 0)
public abstract class MyTrackedEntityMixin {

    // @formatter:off
    @Shadow private int updateCounter;
    //@Shadow private int tickCount;
    // @formatter:on

    /**
     * @author paradis
     * @reason check if tickign entitity is of type ItemFrameEntity and if so check
     *         if this.updateCounter % 50 == 0
     */
    //@Inject(method = "sendChanges", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/ItemFrameEntity;getItem()Lnet/minecraft/item/ItemStack;"), cancellable = true)
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/ItemFrameEntity;getDisplayedItem()Lnet/minecraft/item/ItemStack;"), cancellable = true)
    private void tick(CallbackInfo ci) {
        if (this.updateCounter % 50 == 0) {
            System.out.println("DELAYED");
            ci.cancel();
        }
    }
}
