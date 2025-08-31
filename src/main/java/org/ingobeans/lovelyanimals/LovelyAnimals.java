package org.ingobeans.lovelyanimals;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LovelyAnimals implements ModInitializer {
    public static final String MOD_ID = "lovelyanimals";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("loading lovelyanimals!");
        ModItems.initialize();
    }
}
