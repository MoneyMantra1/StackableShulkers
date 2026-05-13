package com.moneymantra.stackableshulkerautomation.mixin;

import com.moneymantra.stackableshulkerautomation.ShulkerBoxStacking;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getMaxStackSize", at = @At("HEAD"), cancellable = true)
    private void stackable_shulker_automation$getMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;
        if (ShulkerBoxStacking.isShulkerBox(self)) {
            cir.setReturnValue(ShulkerBoxStacking.STACK_LIMIT);
        }
    }

    @Inject(method = "isSameItemSameComponents", at = @At("HEAD"), cancellable = true)
    private static void stackable_shulker_automation$isSameItemSameComponents(ItemStack left, ItemStack right, CallbackInfoReturnable<Boolean> cir) {
        if (ShulkerBoxStacking.eitherStackIsShulkerBox(left, right)) {
            cir.setReturnValue(ShulkerBoxStacking.areSameStackableShulker(left, right));
        }
    }

    @Inject(method = "hashItemAndComponents", at = @At("HEAD"), cancellable = true)
    private static void stackable_shulker_automation$hashItemAndComponents(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (ShulkerBoxStacking.isShulkerBox(stack)) {
            cir.setReturnValue(ShulkerBoxStacking.hashStackableShulker(stack));
        }
    }
}
