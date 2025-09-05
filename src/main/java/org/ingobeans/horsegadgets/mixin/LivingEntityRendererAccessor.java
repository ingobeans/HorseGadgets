package org.ingobeans.horsegadgets.mixin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(LivingEntityRenderer.class)
public interface LivingEntityRendererAccessor<S extends LivingEntityRenderState, M extends EntityModel<? super S>>  {
    @Accessor("features")
    List<FeatureRenderer<S, M>> getFeatures();

}