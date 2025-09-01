package org.ingobeans.unneglectedhorses.items;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.NbtReadView;
import net.minecraft.storage.NbtWriteView;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ErrorReporter;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.ingobeans.unneglectedhorses.ModItems;

public class FilledHorsePocket extends Item {
    public FilledHorsePocket(Settings settings) {
        super(settings);
    }


    public static void writeEntityDataToItem(HorseEntity entity, ItemStack stack) {
        NbtWriteView view = NbtWriteView.create(ErrorReporter.EMPTY);
        entity.writeData(view);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, view.getNbt());
        Text name = entity.getCustomName();
        if (name != null) {
            stack.set(DataComponentTypes.CUSTOM_NAME, name);
        }
    }

    public static void writeEntityDataFromItem(HorseEntity entity, ItemStack item) {
        NbtComponent nbtComponent = item.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = nbtComponent.copyNbt();

        // remove uuid, otherwise you can't reuse the same horse pocket instance in creative mode
        nbt.remove("UUID");
        // pos should be the new pos
        nbt.put("Pos", Vec3d.CODEC, entity.getPos());
        // make name be derived from item stack's name rather than NBT data
        Text name = item.getCustomName();
        if (name != null) {
            nbt.putNullable("CustomName", TextCodecs.CODEC, name);
        }
        NbtReadView view = (NbtReadView)NbtReadView.create(ErrorReporter.EMPTY, entity.getRegistryManager(), nbt);
        entity.readData(view);
    }

    public static void playFillSound(World world, Entity entity) {
        world.playSound(null, entity.getBlockPos(), SoundEvents.ITEM_BUNDLE_INSERT, SoundCategory.PLAYERS, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    public static void playEmptySound(World world, Entity entity) {
        world.playSound(null, entity.getBlockPos(), SoundEvents.ITEM_BUNDLE_DROP_CONTENTS, SoundCategory.PLAYERS, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    public void emptyPocket(World world, ItemStack itemStack, BlockPos pos) {
        HorseEntity mobEntity = EntityType.HORSE.create((ServerWorld)world, EntityType.copier(world, itemStack, null), pos, SpawnReason.BUCKET, true, false);

        if (mobEntity != null) {
            FilledHorsePocket.writeEntityDataFromItem(mobEntity,itemStack);
            world.spawnEntity(mobEntity);
            mobEntity.playAmbientSound();
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        playEmptySound(world,user);
        if (world instanceof ServerWorld serverWorld) {
            BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);
            BlockPos blockPos = blockHitResult.getBlockPos();
            this.emptyPocket(world,itemStack,blockPos);

        }
        ItemStack itemStack2 = new ItemStack(ModItems.EMPTY_HORSE_POCKET);
        return ActionResult.SUCCESS.withNewHandStack(itemStack2);
    }
}