package org.ingobeans.unneglectedhorses;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.ingobeans.unneglectedhorses.models.ElytraSaddleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UnneglectedHorses implements ModInitializer {
    public static final String MOD_ID = "unneglectedhorses";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("loading UnneglectedHorses!");
        ModItems.initialize();
        EntityModelLayerRegistry.registerModelLayer(UnneglectedHorses.ELYTRA_SADDLE_MODEL, (EntityModelLayerRegistry.TexturedModelDataProvider) ElytraSaddleModel::getTexturedModelData);

    }
    public static final EntityModelLayer ELYTRA_SADDLE_MODEL = new EntityModelLayer(Identifier.of(UnneglectedHorses.MOD_ID), "elytra_saddle");
}
