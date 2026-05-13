package com.moneymantra.stackableshulkerautomation.mixin;

import com.moneymantra.stackableshulkerautomation.ShulkerBoxStacking;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {
    /**
     * Vanilla clients can know that the server put more than one shulker in a slot,
     * but the normal slot decoration path may still suppress the count because the
     * unmodified client treats shulker boxes as single-stack items.
     *
     * This is intentionally client-only and visual-only. The server-side stacking
     * rule still comes from ModifyDefaultComponentsEvent.
     */
    @Inject(
            method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void stackable_shulker_automation$renderShulkerStackCount(
            Font font,
            ItemStack stack,
            int x,
            int y,
            String text,
            CallbackInfo ci
    ) {
        if (!ShulkerBoxStacking.isShulkerBox(stack)) {
            return;
        }

        if (text == null && stack.getCount() == 1) {
            return;
        }

        GuiGraphics self = (GuiGraphics) (Object) this;
        String displayText = text == null ? String.valueOf(stack.getCount()) : text;

        self.pose().pushPose();
        self.pose().translate(0.0F, 0.0F, 200.0F);
        self.drawString(font, displayText, x + 19 - 2 - font.width(displayText), y + 6 + 3, 0xFFFFFF, true);
        self.pose().popPose();

        ci.cancel();
    }
}
