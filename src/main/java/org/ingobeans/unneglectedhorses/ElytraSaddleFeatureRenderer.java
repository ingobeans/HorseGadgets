package org.ingobeans.unneglectedhorses;

import java.util.function.Function;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.ingobeans.unneglectedhorses.items.ElytraSaddle;

@Environment(EnvType.CLIENT)
public class ElytraSaddleFeatureRenderer<S extends LivingEntityRenderState, RM extends EntityModel<? super S>, EM extends EntityModel<? super S>> extends FeatureRenderer<S, RM> {
    private final EquipmentRenderer equipmentRenderer;
    private final EquipmentModel.LayerType layerType;
    private final Function<S, ItemStack> saddleStackGetter;
    private final EM model;

    public ElytraSaddleFeatureRenderer(FeatureRendererContext<S, RM> context, EquipmentRenderer equipmentRenderer, EquipmentModel.LayerType layerType, Function<S, ItemStack> saddleStackGetter, EM model) {
        super(context);
        this.equipmentRenderer = equipmentRenderer;
        this.layerType = layerType;
        this.saddleStackGetter = saddleStackGetter;
        this.model = model;
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, S livingEntityRenderState, float f, float g) {
        ItemStack itemStack = (ItemStack)this.saddleStackGetter.apply(livingEntityRenderState);
        if (itemStack != null && itemStack.getItem() instanceof ElytraSaddle) {
            Identifier identifier = Identifier.ofVanilla("textures/entity/equipment/wings/elytra.png");
            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, RenderLayer.getArmorCutoutNoCull(identifier), itemStack.hasGlint());
            this.model.setAngles(livingEntityRenderState);
            model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
        }
    }
}
