package com.moneymantra.stackableshulkerautomation;

import com.mojang.logging.LogUtils;
import net.minecraft.core.component.DataComponents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

@Mod(StackableShulkerAutomation.MOD_ID)
public final class StackableShulkerAutomation {
    public static final String MOD_ID = "stackable_shulker_automation";
    private static final Logger LOGGER = LogUtils.getLogger();

    public StackableShulkerAutomation(IEventBus modEventBus) {
        modEventBus.addListener(this::modifyDefaultComponents);
        LOGGER.info("Stackable Shulker Automation loaded. Waiting to patch vanilla shulker box default item components.");
    }

    private void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        AtomicInteger patchedItems = new AtomicInteger();

        event.getAllItems()
                .filter(ShulkerBoxStacking::isShulkerBoxItem)
                .forEach(item -> {
                    event.modify(item, builder -> builder.set(DataComponents.MAX_STACK_SIZE, ShulkerBoxStacking.STACK_LIMIT));
                    patchedItems.incrementAndGet();
                });

        LOGGER.info(
                "Stackable Shulker Automation patched {} shulker box item(s) to max stack size {}.",
                patchedItems.get(),
                ShulkerBoxStacking.STACK_LIMIT
        );
    }
}
