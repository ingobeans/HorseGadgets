package org.ingobeans.horsegadgets.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.ingobeans.horsegadgets.HorseGadgets;
import org.ingobeans.horsegadgets.models.ElytraSaddleModel;

public class HorseGadgetsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(HorseGadgetsClient.ELYTRA_SADDLE_MODEL, ElytraSaddleModel::getTexturedModelData);
    }
    public static final EntityModelLayer ELYTRA_SADDLE_MODEL = new EntityModelLayer(Identifier.of(HorseGadgets.MOD_ID), "elytra_saddle");
}
