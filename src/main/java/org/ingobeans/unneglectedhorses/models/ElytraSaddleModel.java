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

@Environment(EnvType.CLIENT)
public class ElytraSaddleModel extends EntityModel<LivingHorseEntityRenderState> {
    public ElytraSaddleModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData RIGHT = modelPartData.addChild("RIGHT", ModelPartBuilder.create(), ModelTransform.of(-5.0F, 24.0F, 2.0F, 0.0F, -0.2182F, 0.0F));

        ModelPartData bottom_r1 = RIGHT.addChild("bottom_r1", ModelPartBuilder.create().uv(39, 21).cuboid(4.0F, -24.0F, -8.0F, 7.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(15, 2).cuboid(0.0F, -24.0F, -8.0F, 11.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData right_r1 = RIGHT.addChild("right_r1", ModelPartBuilder.create().uv(22, 10).cuboid(0.0F, -29.0F, 1.0F, 0.0F, 11.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-11.0F, -22.0F, 26.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData left_r1 = RIGHT.addChild("left_r1", ModelPartBuilder.create().uv(32, 1).cuboid(0.0F, -29.0F, 0.0F, 0.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -22.0F, 17.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData LEFT = modelPartData.addChild("LEFT", ModelPartBuilder.create(), ModelTransform.of(4.0F, -24.0F, 2.0F, 0.0F, -0.2182F, -3.1416F));

        ModelPartData bottom_r2 = LEFT.addChild("bottom_r2", ModelPartBuilder.create().uv(39, 21).cuboid(4.0F, -24.0F, -8.0F, 7.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData right_r2 = LEFT.addChild("right_r2", ModelPartBuilder.create().uv(22, 10).cuboid(0.0F, -29.0F, 1.0F, 0.0F, 11.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-11.0F, -23.0F, 26.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData left_r2 = LEFT.addChild("left_r2", ModelPartBuilder.create().uv(32, 1).cuboid(0.0F, -29.0F, 0.0F, 0.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -26.0F, 17.0F, 1.5708F, 0.0F, -3.1416F));

        ModelPartData face_r1 = LEFT.addChild("face_r1", ModelPartBuilder.create().uv(37, 2).cuboid(22.0F, -24.0F, -8.0F, -11.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(11.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }
}
