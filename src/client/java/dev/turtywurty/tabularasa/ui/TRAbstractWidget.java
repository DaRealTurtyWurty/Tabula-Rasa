package dev.turtywurty.tabularasa.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public abstract class TRAbstractWidget extends AbstractWidget {
    protected final Minecraft minecraft = Minecraft.getInstance();
    protected final Font font = minecraft.font;
    
    protected TRParent parent;

    protected TRAbstractWidget(int width, int height, Component message) {
        super(0, 0, width, height, message);
    }

    public TRParent getParent() {
        return this.parent;
    }

    public void init(int screenWidth, int screenHeight) {
        // NO-OP
    }

    public void tick() {
        // NO-OP
    }

    @Override
    protected abstract void updateWidgetNarration(@NonNull NarrationElementOutput output);
}
