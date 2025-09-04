package org.ingobeans.unneglectedhorses.mixin;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HorseEntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.HorseEntityRenderState;
import org.ingobeans.unneglectedhorses.ElytraSaddleFeatureRenderer;
import org.ingobeans.unneglectedhorses.UnneglectedHorses;
import org.ingobeans.unneglectedhorses.models.ElytraSaddleModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseEntityRenderer.class)
public abstract class HorseEntityRendererMixin {
    @SuppressWarnings({"unchecked", "rawtypes", "DataFlowIssue"})
    @Inject(method = "<init>",at=@At("TAIL"))
    protected void HorseEntityRenderer(EntityRendererFactory.Context context, CallbackInfo ci) {
        HorseEntityRenderer thisObject = (HorseEntityRenderer)(Object)this;
        ((LivingEntityRendererAccessor)(LivingEntityRenderer)thisObject).getFeatures().add(new ElytraSaddleFeatureRenderer(thisObject, (horseEntityRenderState) -> ((HorseEntityRenderState)horseEntityRenderState).saddleStack, new ElytraSaddleModel(context.getPart(UnneglectedHorses.ELYTRA_SADDLE_MODEL))));
    }
}
