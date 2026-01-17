package dev.turtywurty.tabularasa.ui;

import dev.turtywurty.tabularasa.util.GuiGraphicsUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class TRPanel extends TRParent {
    protected final Minecraft minecraft = Minecraft.getInstance();
    protected final Font font = minecraft.font;
    protected int titleX, titleY;
    protected int titleColor = 0xFF404040;
    protected boolean titleDropShadow = false;
    protected final Consumer<TRPanel> onInit;
    protected TRLayout layout;

    public TRPanel(TRLayout layout, int x, int y, int width, int height, Component title, Consumer<TRPanel> onInit) {
        super(width, height, title);
        setPosition(x, y);

        this.layout = layout;
        this.titleX = x + 4;
        this.titleY = y + 4;
        this.onInit = onInit;

        if (this.layout != null) {
            this.layout.setPosition(x, y);
            this.layout.setSize(width, height);
            addChild(this.layout);
        }
    }

    @Override
    public void init(int screenWidth, int screenHeight) {
        super.init(screenWidth, screenHeight);
        if (this.onInit != null) {
            this.onInit.accept(this);
        }
    }

    @Override
    protected final void renderWidget(@NonNull GuiGraphics graphics, int mouseX, int mouseY, float tickDelta) {
        GuiGraphicsUtils.drawBackground(graphics, getX(), getY(), getWidth(), getHeight());
        graphics.drawString(this.font, getMessage(), this.titleX, this.titleY, this.titleColor, this.titleDropShadow);
        super.renderWidget(graphics, mouseX, mouseY, tickDelta);
    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput output) {
        output.add(NarratedElementType.TITLE, createNarrationMessage());
    }

    public void setTitleX(int titleX) {
        this.titleX = titleX;
    }

    public void setTitleY(int titleY) {
        this.titleY = titleY;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public void setTitleDropShadow(boolean titleDropShadow) {
        this.titleDropShadow = titleDropShadow;
    }
}
