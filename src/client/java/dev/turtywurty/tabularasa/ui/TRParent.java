package dev.turtywurty.tabularasa.ui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class TRParent extends TRAbstractWidget {
    protected final List<TRAbstractWidget> children = new ArrayList<>();

    protected TRParent(int width, int height, Component message) {
        super(width, height, message);
    }

    public List<TRAbstractWidget> getChildrenUnmodifiable() {
        return Collections.unmodifiableList(this.children);
    }

    public <T extends TRAbstractWidget> T addChild(T child) {
        if (child.getParent() != null && child.getParent() != this) {
            child.getParent().children.remove(child);
        }

        child.parent = this;
        this.children.add(child);
        return child;
    }

    public boolean removeChild(TRAbstractWidget child) {
        if (this.children.remove(child)) {
            if (child.getParent() == this) {
                child.parent = null;
            }

            return true;
        }

        return false;
    }

    public void removeAllChildren() {
        children.forEach(this::removeChild);
    }

    @Override
    public void init(int screenWidth, int screenHeight) {
        for (TRAbstractWidget child : this.children) {
            child.init(screenWidth, screenHeight);
        }
    }

    @Override
    public void tick() {
        for (TRAbstractWidget child : this.children) {
            child.tick();
        }
    }

    @Override
    protected void renderWidget(@NonNull GuiGraphics graphics, int mouseX, int mouseY, float tickDelta) {
        renderChildren(graphics, mouseX, mouseY, tickDelta);
    }

    protected void renderChildren(@NonNull GuiGraphics graphics, int mouseX, int mouseY, float tickDelta) {
        for (TRAbstractWidget child : this.children) {
            child.render(graphics, mouseX, mouseY, tickDelta);
        }
    }

    @Override
    public void mouseMoved(double x, double y) {
        for (TRAbstractWidget child : this.children) {
            child.mouseMoved(x, y);
        }
    }

    @Override
    public boolean mouseClicked(@NonNull MouseButtonEvent event, boolean doubleClick) {
        for (int i = this.children.size() - 1; i >= 0; i--) {
            if (this.children.get(i).mouseClicked(event, doubleClick))
                return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean mouseDragged(@NonNull MouseButtonEvent event, double dx, double dy) {
        for (int i = this.children.size() - 1; i >= 0; i--) {
            if (this.children.get(i).mouseDragged(event, dx, dy))
                return true;
        }

        return super.mouseDragged(event, dx, dy);
    }

    @Override
    public boolean mouseReleased(@NonNull MouseButtonEvent event) {
        for (int i = this.children.size() - 1; i >= 0; i--) {
            if (this.children.get(i).mouseReleased(event))
                return true;
        }

        return super.mouseReleased(event);
    }

    @Override
    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        for (int i = this.children.size() - 1; i >= 0; i--) {
            if (this.children.get(i).mouseScrolled(x, y, scrollX, scrollY))
                return true;
        }

        return super.mouseScrolled(x, y, scrollX, scrollY);
    }

    @Override
    public boolean keyPressed(@NonNull KeyEvent event) {
        for (int i = this.children.size() - 1; i >= 0; i--) {
            if (this.children.get(i).keyPressed(event))
                return true;
        }

        return super.keyPressed(event);
    }

    @Override
    public boolean keyReleased(@NonNull KeyEvent event) {
        for (int i = this.children.size() - 1; i >= 0; i--) {
            if (this.children.get(i).keyReleased(event))
                return true;
        }

        return super.keyReleased(event);
    }

    @Override
    public boolean charTyped(@NonNull CharacterEvent event) {
        for (int i = this.children.size() - 1; i >= 0; i--) {
            if (this.children.get(i).charTyped(event))
                return true;
        }

        return super.charTyped(event);
    }
}
