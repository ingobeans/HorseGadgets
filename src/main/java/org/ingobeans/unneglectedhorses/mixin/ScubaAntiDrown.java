package org.ingobeans.unneglectedhorses.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AbstractHorseEntity;
import org.ingobeans.unneglectedhorses.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class ScubaAntiDrown {

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(method = "tick", at=@At("HEAD"))
    void tick(CallbackInfo ci)
    {
        LivingEntity thisObject = (LivingEntity)(Object)this;
        if (thisObject.isSubmergedInWater()) {
            // either give air if you are a horse with scuba gear,
            // or if youre riding a horse with scuba gear
            boolean shouldGetAir = (thisObject instanceof AbstractHorseEntity horseSelf && horseSelf.getBodyArmor().getItem() == ModItems.HORSE_SCUBA_GEAR) ||
                    (thisObject.getVehicle() instanceof AbstractHorseEntity horseEntity && horseEntity.getBodyArmor().getItem() == ModItems.HORSE_SCUBA_GEAR);

            if (shouldGetAir) {
                addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 30, 0, false, false, false));
                addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 30, 0, false, false, true));
            }
        }
     }
}
