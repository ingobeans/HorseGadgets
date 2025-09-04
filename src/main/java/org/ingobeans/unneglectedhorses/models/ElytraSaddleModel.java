package org.ingobeans.unneglectedhorses.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.state.LivingHorseEntityRenderState;
import org.ingobeans.unneglectedhorses.UnneglectedHorses;

@Environment(EnvType.CLIENT)
public class ElytraSaddleModel extends AbstractHorseEntityModel<LivingHorseEntityRenderState> {
    private static final String SADDLE = "saddle";
    private static final String LEFT_SADDLE_MOUTH = "left_saddle_mouth";
    private static final String LEFT_SADDLE_LINE = "left_saddle_line";
    private static final String RIGHT_SADDLE_MOUTH = "right_saddle_mouth";
    private static final String RIGHT_SADDLE_LINE = "right_saddle_line";
    private static final String HEAD_SADDLE = "head_saddle";
    private static final String MOUTH_SADDLE_WRAP = "mouth_saddle_wrap";
    private final ModelPart[] saddleLines;

    public ElytraSaddleModel(ModelPart modelPart) {
        super(modelPart);
        ModelPart modelPart2 = this.head.getChild("left_saddle_line");
        ModelPart modelPart3 = this.head.getChild("right_saddle_line");
        this.saddleLines = new ModelPart[]{modelPart2, modelPart3};
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = getModelData(Dilation.NONE);
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.getChild(EntityModelPartNames.BODY);
        ModelPartData modelPartData3 = modelPartData.getChild("head_parts");
        modelPartData2.addChild("saddle", ModelPartBuilder.create().uv(26, 0).cuboid(-5.0F, -8.0F, -9.0F, 10.0F, 9.0F, 9.0F, new Dilation(0.5F)), ModelTransform.NONE);
        modelPartData3.addChild("left_saddle_mouth", ModelPartBuilder.create().uv(29, 5).cuboid(2.0F, -9.0F, -6.0F, 1.0F, 2.0F, 2.0F), ModelTransform.NONE);
        modelPartData3.addChild("right_saddle_mouth", ModelPartBuilder.create().uv(29, 5).cuboid(-3.0F, -9.0F, -6.0F, 1.0F, 2.0F, 2.0F), ModelTransform.NONE);
        modelPartData3.addChild("left_saddle_line", ModelPartBuilder.create().uv(32, 2).cuboid(3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F), ModelTransform.rotation(-0.5235988F, 0.0F, 0.0F));
        modelPartData3.addChild("right_saddle_line", ModelPartBuilder.create().uv(32, 2).cuboid(-3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F), ModelTransform.rotation(-0.5235988F, 0.0F, 0.0F));
        modelPartData3.addChild("head_saddle", ModelPartBuilder.create().uv(1, 1).cuboid(-3.0F, -11.0F, -1.9F, 6.0F, 5.0F, 6.0F, new Dilation(0.22F)), ModelTransform.NONE);
        modelPartData3.addChild("mouth_saddle_wrap", ModelPartBuilder.create().uv(19, 0).cuboid(-2.0F, -11.0F, -4.0F, 4.0F, 5.0F, 2.0F, new Dilation(0.2F)), ModelTransform.NONE);

        ModelPartData bb_main = modelPartData2.addChild("bb_main", ModelPartBuilder.create().uv(-32, 0).cuboid(-32.0F, -18.0F, -15.0F, 64.0F, 32.0F, 32.0F, new Dilation(0.0F))
                ,ModelTransform.origin(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    public void setAngles(LivingHorseEntityRenderState livingHorseEntityRenderState) {
        super.setAngles(livingHorseEntityRenderState);
        ModelPart[] var2 = this.saddleLines;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ModelPart modelPart = var2[var4];
            modelPart.visible = livingHorseEntityRenderState.hasPassengers;
        }

    }
}
