package org.ingobeans.unneglectedhorses;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UnneglectedHorses implements ModInitializer {
    public static final String MOD_ID = "unneglectedhorses";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("loading UnneglectedHorses!");
        ModItems.initialize();
    }
}
