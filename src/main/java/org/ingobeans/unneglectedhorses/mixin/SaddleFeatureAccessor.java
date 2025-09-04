package org.ingobeans.unneglectedhorses.mixin;

import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;

@Mixin(SaddleFeatureRenderer.class)
public interface SaddleFeatureAccessor<S extends LivingEntityRenderState, RM extends EntityModel<? super S>, EM extends EntityModel<? super S>> {
    @Accessor("babyModel")
    EM getBabyModel();
    @Accessor("adultModel")
    EM getAdultModel();
    @Accessor("saddleStackGetter")
    Function<S, ItemStack> invokeSaddleStackGetter();
    @Accessor("equipmentRenderer")
    EquipmentRenderer getEquipmentRenderer();
    @Accessor("layerType")
    EquipmentModel.LayerType getLayerType();

}
