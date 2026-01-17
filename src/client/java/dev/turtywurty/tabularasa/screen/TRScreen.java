package dev.turtywurty.tabularasa.screen;

import dev.turtywurty.tabularasa.ui.TRLayout;
import dev.turtywurty.tabularasa.ui.TRPanel;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class TRScreen extends Screen {
    protected final List<TRPanel> panels = new ArrayList<>();

    protected int centerX() {
        return this.width / 2;
    }

    protected int centerY() {
        return this.height / 2;
    }

    protected TRScreen() {
        super(Component.empty());
    }

    protected abstract void constructPanels();


    @Override
    protected void init() {
        super.init();
        this.width = this.minecraft.getWindow().getGuiScaledWidth();
        this.height = this.minecraft.getWindow().getGuiScaledHeight();

        this.panels.clear();
        constructPanels();

        for (TRPanel panel : panels) {
            panel.init(this.width, this.height);
        }
    }

    @Override
    public void render(@NonNull GuiGraphics graphics, int mouseX, int mouseY, float tickDelta) {
        super.render(graphics, mouseX, mouseY, tickDelta);
        for (TRPanel panel : panels) {
            panel.render(graphics, mouseX, mouseY, tickDelta);
        }
    }

    @Override
    public void tick() {
        super.tick();
        for (TRPanel panel : panels) {
            panel.tick();
        }
    }

    @Override
    public void onClose() {
        ScreenStack stack = ScreenStack.getCurrentInstance();
        if (stack != null && ScreenStack.isScreenStackOpen() && !stack.isEmpty() && stack.peek() == this) {
            stack.pop();
            return;
        }

        super.onClose();
    }

    protected final void addPanel(TRPanel panel) {
        this.panels.add(panel);
    }

    protected final TRPanel addPanel(TRLayout layout, int x, int y, int width, int height, Component title, Consumer<TRPanel> onInit) {
        var panel = new TRPanel(layout, x, y, width, height, title, onInit);
        this.panels.add(panel);
        return panel;
    }

    protected final TRPanel addPanel(TRLayout layout, int x, int y, int width, int height, Consumer<TRPanel> onInit) {
        return addPanel(layout, x, y, width, height, Component.empty(), onInit);
    }

    protected final TRPanel addPanel(TRLayout layout, int x, int y, int width, int height, Component title) {
        return addPanel(layout, x, y, width, height, title, _ -> {
        });
    }

    protected final TRPanel addPanel(TRLayout layout, int x, int y, int width, int height) {
        return addPanel(layout, x, y, width, height, Component.empty(), _ -> {
        });
    }

    protected final TRPanel addPanelCentre(TRLayout layout, int centerX, int centerY, int width, int height, Component title, Consumer<TRPanel> onInit) {
        int x = centerX - (width / 2);
        int y = centerY - (height / 2);
        return addPanel(layout, x, y, width, height, title, onInit);
    }

    protected final TRPanel addPanelCentre(TRLayout layout, int centerX, int centerY, int width, int height, Consumer<TRPanel> onInit) {
        return addPanelCentre(layout, centerX, centerY, width, height, Component.empty(), onInit);
    }

    protected final TRPanel addPanelCentre(TRLayout layout, int centerX, int centerY, int width, int height, Component title) {
        return addPanelCentre(layout, centerX, centerY, width, height, title, _ -> {
        });
    }

    protected final TRPanel addPanelCentre(TRLayout layout, int centerX, int centerY, int width, int height) {
        return addPanelCentre(layout, centerX, centerY, width, height, Component.empty(), _ -> {
        });
    }
}
