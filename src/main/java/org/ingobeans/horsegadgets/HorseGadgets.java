package org.ingobeans.horsegadgets;

import net.fabricmc.api.ModInitializer;
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
}
