package org.ingobeans.unneglectedhorses.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import org.ingobeans.unneglectedhorses.UnneglectedHorses;
import org.ingobeans.unneglectedhorses.models.ElytraSaddleModel;

public class UnneglectedHorsesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(UnneglectedHorses.ELYTRA_SADDLE_MODEL, ElytraSaddleModel::getTexturedModelData);
    }
}
