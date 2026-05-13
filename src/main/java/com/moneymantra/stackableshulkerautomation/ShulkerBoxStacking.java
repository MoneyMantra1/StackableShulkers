package com.moneymantra.stackableshulkerautomation;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ShulkerBoxBlock;

import java.util.Objects;

public final class ShulkerBoxStacking {
    public static final int STACK_LIMIT = 64;

    private ShulkerBoxStacking() {
    }

    public static boolean isShulkerBox(ItemStack stack) {
        return !stack.isEmpty() && isShulkerBoxItem(stack.getItem());
    }

    public static boolean isShulkerBoxItem(Item item) {
        return item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock;
    }

    public static boolean eitherStackIsShulkerBox(ItemStack left, ItemStack right) {
        return isShulkerBox(left) || isShulkerBox(right);
    }

    /**
     * Vanilla already requires same item + same components to stack.
     * We keep that rule intact, except we ignore only minecraft:max_stack_size so old datapack-modified
     * shulkers and new mod-controlled shulkers can merge when every meaningful property matches.
     */
    public static boolean areSameStackableShulker(ItemStack left, ItemStack right) {
        if (left.isEmpty() || right.isEmpty()) {
            return left.isEmpty() && right.isEmpty();
        }

        if (!isShulkerBox(left) || !isShulkerBox(right)) {
            return false;
        }

        if (!left.is(right.getItem())) {
            return false;
        }

        return comparableComponents(left).equals(comparableComponents(right));
    }

    public static int hashStackableShulker(ItemStack stack) {
        return Objects.hash(stack.getItem(), comparableComponents(stack));
    }

    private static DataComponentMap comparableComponents(ItemStack stack) {
        return stack.getComponents().filter(componentType -> !componentType.equals(DataComponents.MAX_STACK_SIZE));
    }
}
