package org.ingobeans.horsegadgets.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import org.ingobeans.horsegadgets.HorseGadgets;
import org.ingobeans.horsegadgets.models.ElytraSaddleModel;

public class HorseGadgetsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(HorseGadgets.ELYTRA_SADDLE_MODEL, ElytraSaddleModel::getTexturedModelData);
    }
}
