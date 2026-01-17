package dev.turtywurty.tabularasa.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class TRTextWidget extends TRAbstractWidget {
    private int color;
    private boolean dropShadow;

    public TRTextWidget(Component message, int color, boolean dropShadow) {
        Font font = Minecraft.getInstance().font;
        super(font.width(message), font.lineHeight, message);
        this.color = color;
        this.dropShadow = dropShadow;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setDropShadow(boolean dropShadow) {
        this.dropShadow = dropShadow;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float tickDelta) {
        graphics.drawString(this.font, getMessage(), getX(), getY(), this.color, this.dropShadow);
    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput output) {
        output.add(NarratedElementType.TITLE, createNarrationMessage());
    }
}
