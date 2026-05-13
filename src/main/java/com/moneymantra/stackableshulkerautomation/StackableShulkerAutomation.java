package com.moneymantra.stackableshulkerautomation;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(StackableShulkerAutomation.MOD_ID)
public final class StackableShulkerAutomation {
    public static final String MOD_ID = "stackable_shulker_automation";
    private static final Logger LOGGER = LogUtils.getLogger();

    public StackableShulkerAutomation() {
        LOGGER.info("Stackable Shulker Automation loaded: identical shulker boxes can stack up to {}.", ShulkerBoxStacking.STACK_LIMIT);
    }
}
