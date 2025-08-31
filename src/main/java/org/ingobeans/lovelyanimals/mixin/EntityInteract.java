package org.ingobeans.lovelyanimals.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.ingobeans.lovelyanimals.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HorseEntity.class)
public abstract class EntityInteract {
    @Inject(method = "interactMob", at= @At("HEAD"), cancellable = true)
    protected void injectInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);

        AnimalEntity thisObject = ((AnimalEntity)(Object)this);
        if (itemStack.isOf(ModItems.HORSE_POCKET)) {
            World var5 = thisObject.getWorld();
            if (var5 instanceof ServerWorld serverWorld) {
                player.sendMessage(Text.empty().append("horse pocket activated!"), false);
                thisObject.remove(Entity.RemovalReason.DISCARDED);
                cir.setReturnValue(ActionResult.SUCCESS_SERVER);
                return;
            }

            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
