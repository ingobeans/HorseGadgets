package org.ingobeans.horsegadgets.mixin;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.ingobeans.horsegadgets.ModItems;
import org.ingobeans.horsegadgets.items.ElytraSaddle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHorseEntity.class)
public abstract class HorseJumping {
    @Inject(method = "playJumpSound", at=@At("HEAD"), cancellable = true)
    protected void playJumpSound(CallbackInfo ci){
        AbstractHorseEntity horseEntity = (AbstractHorseEntity)(Object)this;
        if (!horseEntity.isOnGround()) {
            horseEntity.playSound(SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL, 0.2F, 1.0F);
            ci.cancel();
        }
    }
    @Inject(method = "tickControlled", at= @At("TAIL"))
    protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput, CallbackInfo cir) {
        AbstractHorseEntity horseEntity = (AbstractHorseEntity)(Object)this;
        if (horseEntity.isLogicalSideForUpdatingMovement()) {
            if (!horseEntity.isOnGround()) {
                ItemStack saddle = horseEntity.getEquippedStack(EquipmentSlot.SADDLE);
                ItemStack armor = horseEntity.getBodyArmor();
                boolean canJumpOverride = (saddle != null && saddle.getItem() instanceof ElytraSaddle) || (armor != null && armor.getItem() == ModItems.HORSE_SCUBA_GEAR && horseEntity.isTouchingWater());

                if (canJumpOverride) {
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