package org.ingobeans.lovelyanimals.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.ingobeans.lovelyanimals.EmptyHorsePocket;
import org.ingobeans.lovelyanimals.FilledHorsePocket;
import org.ingobeans.lovelyanimals.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(HorseEntity.class)
public abstract class HorseInteract {

    @Inject(method = "interactMob", at= @At("HEAD"), cancellable = true)
    protected void injectInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);


        AnimalEntity entity = ((AnimalEntity)(Object)this);
        if (itemStack.isOf(ModItems.EMPTY_HORSE_POCKET)) {
            World world = entity.getWorld();
            if (entity.isAlive()) {

                ItemStack filledStack = new ItemStack(ModItems.FILLED_HORSE_POCKET);
                FilledHorsePocket.copyDataToStack((HorseEntity)entity ,filledStack);

                player.setStackInHand(hand, filledStack);

                entity.discard();
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
