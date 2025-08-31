package org.ingobeans.lovelyanimals;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.NbtReadView;
import net.minecraft.storage.NbtWriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ErrorReporter;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class FilledHorsePocket extends Item {
    public FilledHorsePocket(Settings settings) {
        super(settings);
    }


    public static void copyDataToStack(HorseEntity entity, ItemStack stack) {
        NbtWriteView view = NbtWriteView.create(ErrorReporter.EMPTY);
        entity.writeData(view);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, view.getNbt());
    }

    public static void copyDataFromNbt(HorseEntity entity, NbtCompound nbt) {
        // remove uuid, otherwise you can't reuse the same horse pocket instance in creative mode
        nbt.remove("UUID");
        // pos should be the new pos
        nbt.put("Pos", Vec3d.CODEC, entity.getPos());
        NbtReadView view = (NbtReadView)NbtReadView.create(ErrorReporter.EMPTY, entity.getRegistryManager(), nbt);
        entity.readData(view);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (world instanceof ServerWorld serverWorld) {
            BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);
            BlockPos blockPos = blockHitResult.getBlockPos();

            HorseEntity mobEntity = EntityType.HORSE.create(serverWorld, EntityType.copier(world, itemStack, (LivingEntity)null), blockPos, SpawnReason.BUCKET, true, false);

            NbtComponent nbtComponent = (NbtComponent)itemStack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);

            if (mobEntity != null) {
                FilledHorsePocket.copyDataFromNbt(mobEntity,nbtComponent.copyNbt());

                world.spawnEntity(mobEntity);
                mobEntity.playAmbientSound();
            }

        }
        ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, user, new ItemStack(ModItems.EMPTY_HORSE_POCKET));
        return ActionResult.SUCCESS.withNewHandStack(itemStack2);
    }
}