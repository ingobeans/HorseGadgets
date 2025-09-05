package org.ingobeans.horsegadgets;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.ingobeans.horsegadgets.items.ElytraSaddle;
import org.ingobeans.horsegadgets.items.EmptyHorsePocket;
import org.ingobeans.horsegadgets.items.FilledHorsePocket;
import org.ingobeans.horsegadgets.items.ModArmorMaterials;

import java.util.function.Function;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HorseGadgets.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup)->itemGroup.add(ModItems.EMPTY_HORSE_POCKET));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup)->itemGroup.add(ModItems.ELYTRA_SADDLE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup)->itemGroup.add(ModItems.HORSE_SCUBA_GEAR));


        DispenserBlock.registerBehavior(ModItems.FILLED_HORSE_POCKET, new ItemDispenserBehavior() {
            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                BlockPos blockPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING));
                World world = pointer.world();
                ((FilledHorsePocket)stack.getItem()).emptyPocket(world,stack,blockPos);
                return new ItemStack(ModItems.EMPTY_HORSE_POCKET);
        }});

    }
    public static final RegistryKey<EquipmentAsset> SCUBA_ASSET_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(HorseGadgets.MOD_ID, "scuba"));

    public static final Item ELYTRA_SADDLE = register("elytra_saddle", ElytraSaddle::new, new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.ofSaddle()));
    public static final Item EMPTY_HORSE_POCKET = register("horse_pocket", EmptyHorsePocket::new, new Item.Settings().maxCount(1));
    public static final Item FILLED_HORSE_POCKET = register("filled_horse_pocket", FilledHorsePocket::new, new Item.Settings().maxCount(1));
    public static final Item HORSE_SCUBA_GEAR = register("horse_scuba_gear", Item::new, new Item.Settings().maxCount(1).horseArmor(ModArmorMaterials.SCUBA));

}