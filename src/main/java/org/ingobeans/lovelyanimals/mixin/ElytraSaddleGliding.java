package org.ingobeans.lovelyanimals.mixin;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.ItemStack;
import org.ingobeans.lovelyanimals.items.ElytraSaddle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ElytraSaddleGliding {
    @Inject(method = "isGliding", at= @At("HEAD"), cancellable = true)
    protected void isGliding(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        if (livingEntity instanceof HorseEntity horseEntity) {
            ItemStack saddle = horseEntity.getEquippedStack(EquipmentSlot.SADDLE);

            if (saddle != null && saddle.getItem() instanceof ElytraSaddle && !livingEntity.isOnGround()) {
                cir.setReturnValue(true);
            }
        }
    }
}