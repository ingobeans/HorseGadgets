package org.ingobeans.horsegadgets;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HorseGadgets implements ModInitializer {
    public static final String MOD_ID = "horsegadgets";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("loading HorseGadgets!");
        ModItems.initialize();

    }
    public static final EntityModelLayer ELYTRA_SADDLE_MODEL = new EntityModelLayer(Identifier.of(HorseGadgets.MOD_ID), "elytra_saddle");
}
