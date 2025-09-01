package org.ingobeans.unneglectedhorses.mixin;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.ingobeans.unneglectedhorses.items.ElytraSaddle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHorseEntity.class)
public abstract class ElytraSaddleJumping {
    @Inject(method = "tickControlled", at= @At("TAIL"))
    protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput, CallbackInfo cir) {
        AbstractHorseEntity horseEntity = (AbstractHorseEntity)(Object)this;
        if (horseEntity.isLogicalSideForUpdatingMovement()) {
            if (!horseEntity.isOnGround()) {
                ItemStack saddle = horseEntity.getEquippedStack(EquipmentSlot.SADDLE);

                if (saddle != null && saddle.getItem() instanceof ElytraSaddle) {
                    float jumpStrength = ((AbstractHorseEntityAccessor)horseEntity).getJumpStrength();
                    if (jumpStrength > 0.0F && !horseEntity.isJumping()) {
                        ((AbstractHorseEntityAccessor)horseEntity).invokeJump(jumpStrength, movementInput);
                    }

                    ((AbstractHorseEntityAccessor)horseEntity).setJumpStrength(0.0F);
                }
        }
        }
    }
}