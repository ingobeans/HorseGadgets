package org.ingobeans.horsegadgets.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.item.ItemStack;
import org.ingobeans.horsegadgets.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityAntiDismounter {
    @Inject(method = "shouldDismountUnderwater", at= @At("TAIL"), cancellable = true)
    protected void shouldDismountUnderwater(CallbackInfoReturnable<Boolean> cir) {
        Entity thisObject = (Entity)(Object)this;
        if (thisObject instanceof AbstractHorseEntity horseEntity) {
            ItemStack armor = horseEntity.getBodyArmor();
            if (armor != null && armor.getItem() == ModItems.HORSE_SCUBA_GEAR) {
                cir.setReturnValue(false);
            }
        }

    }

}
