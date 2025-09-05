package org.ingobeans.horsegadgets.items;

import com.google.common.collect.Maps;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import org.ingobeans.horsegadgets.ModItems;

import java.util.Map;

public interface ModArmorMaterials {
    ArmorMaterial SCUBA = new ArmorMaterial (
            25,
            Maps.newEnumMap(Map.of(EquipmentType.BOOTS, 2, EquipmentType.LEGGINGS, 5, EquipmentType.CHESTPLATE, 6, EquipmentType.HELMET, 2, EquipmentType.BODY, 5)),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,
            0.0F,
            0.0F,
            ItemTags.REPAIRS_TURTLE_HELMET,
            ModItems.SCUBA_ASSET_KEY
    );

}
