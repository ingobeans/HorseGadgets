package org.ingobeans.horsegadgets.mixin;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HorseEntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.HorseEntityRenderState;
import org.ingobeans.horsegadgets.ElytraSaddleFeatureRenderer;
import org.ingobeans.horsegadgets.HorseGadgets;
import org.ingobeans.horsegadgets.models.ElytraSaddleModel;
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
        ((LivingEntityRendererAccessor)(LivingEntityRenderer)thisObject).getFeatures().add(new ElytraSaddleFeatureRenderer(thisObject, (horseEntityRenderState) -> ((HorseEntityRenderState)horseEntityRenderState).saddleStack, new ElytraSaddleModel(context.getPart(HorseGadgets.ELYTRA_SADDLE_MODEL))));
    }
}
