package org.ingobeans.unneglectedhorses.mixin;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HorseEntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.HorseSaddleEntityModel;
import net.minecraft.client.render.entity.state.HorseEntityRenderState;
import org.ingobeans.unneglectedhorses.ElytraSaddleFeatureRenderer;
import org.ingobeans.unneglectedhorses.UnneglectedHorses;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseEntityRenderer.class)
public abstract class HorseEntityRendererMixin {
    @Inject(method = "<init>",at=@At("TAIL"))
    protected void HorseEntityRenderer(EntityRendererFactory.Context context, CallbackInfo ci) {
        UnneglectedHorses.LOGGER.info("we mixa de in");
        HorseEntityRenderer thisObject = (HorseEntityRenderer)(Object)this;
        ((LivingEntityRendererAccessor)(LivingEntityRenderer)thisObject).getFeatures().add(new ElytraSaddleFeatureRenderer(thisObject, context.getEquipmentRenderer(), EquipmentModel.LayerType.HORSE_SADDLE, (horseEntityRenderState) -> {
            return ((HorseEntityRenderState)horseEntityRenderState).saddleStack;
        }, new HorseSaddleEntityModel(context.getPart(UnneglectedHorses.ELYTRA_SADDLE_MODEL))));
    }
}
