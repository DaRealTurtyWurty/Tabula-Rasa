package dev.turtywurty.tabularasa.ui;

import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class TRLayout extends TRParent {
    protected TRLayout() {
        super(0, 0, Component.empty());
    }

    public abstract void arrangeChildren();

    public abstract void calculateBounds(AtomicInteger width, AtomicInteger height);

    protected final void calculateAndSetSize() {
        var width = new AtomicInteger();
        var height = new AtomicInteger();
        calculateBounds(width, height);
        setSize(width.get(), height.get());
    }

    @Override
    public void init(int screenWidth, int screenHeight) {
        updateContents();
        super.init(screenWidth, screenHeight);
    }

    @Override
    public <T extends TRAbstractWidget> T addChild(T child) {
        T added = super.addChild(child);
        updateContents();
        return added;
    }

    @Override
    public boolean removeChild(TRAbstractWidget child) {
        boolean removed = super.removeChild(child);
        if (removed) {
            updateContents();
        }

        return removed;
    }

    protected void updateContents() {
        calculateAndSetSize();
        arrangeChildren();
    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput output) {
    }
}
