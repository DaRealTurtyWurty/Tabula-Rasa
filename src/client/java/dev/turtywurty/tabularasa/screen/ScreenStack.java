package dev.turtywurty.tabularasa.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Stack;

public class ScreenStack extends Screen {
    private static ScreenStack currentInstance = null;
    private final Stack<TRScreen> screenStack = new Stack<>();

    private ScreenStack() {
        super(Component.empty());
    }

    public static boolean isCurrentScreenOfType(Class<? extends TRScreen> screenClass) {
        if (!isScreenStackOpen())
            return false;

        return !currentInstance.isEmpty() && screenClass.isInstance(currentInstance.peek());
    }

    public static void pushScreen(TRScreen screen) {
        if (currentInstance == null) {
            currentInstance = new ScreenStack();
            Minecraft.getInstance().setScreen(currentInstance);
        }

        currentInstance.push(screen);
    }

    public static boolean isScreenStackOpen() {
        return Minecraft.getInstance().screen instanceof ScreenStack;
    }

    public static ScreenStack getCurrentInstance() {
        return currentInstance;
    }

    public void push(TRScreen screen) {
        this.screenStack.push(screen);
        Minecraft.getInstance().setScreen(this);
    }

    public TRScreen pop() {
        TRScreen popped = this.screenStack.pop();
        if (isEmpty()) {
            currentInstance = null;
            Minecraft.getInstance().setScreen(null);
            return popped;
        }

        Minecraft.getInstance().setScreen(this);
        return popped;
    }

    public TRScreen peek() {
        return this.screenStack.peek();
    }

    public boolean isEmpty() {
        return this.screenStack.isEmpty();
    }

    public int size() {
        return this.screenStack.size();
    }

    @Override
    public boolean mouseClicked(@NonNull MouseButtonEvent event, boolean doubleClick) {
        if (!isEmpty())
            return peek().mouseClicked(event, doubleClick);

        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean mouseDragged(@NonNull MouseButtonEvent event, double dx, double dy) {
        if (!isEmpty())
            return peek().mouseDragged(event, dx, dy);

        return super.mouseDragged(event, dx, dy);
    }

    @Override
    public boolean mouseReleased(@NonNull MouseButtonEvent event) {
        if (!isEmpty())
            return peek().mouseReleased(event);

        return super.mouseReleased(event);
    }

    @Override
    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        if (!isEmpty())
            return peek().mouseScrolled(x, y, scrollX, scrollY);

        return super.mouseScrolled(x, y, scrollX, scrollY);
    }

    @Override
    public void mouseMoved(double x, double y) {
        if (!isEmpty()) {
            peek().mouseMoved(x, y);
        }
    }

    @Override
    public boolean keyPressed(@NonNull KeyEvent event) {
        if (!isEmpty())
            return peek().keyPressed(event);

        return super.keyPressed(event);
    }

    @Override
    public boolean keyReleased(@NonNull KeyEvent event) {
        if (!isEmpty())
            return peek().keyReleased(event);

        return super.keyReleased(event);
    }

    @Override
    public void tick() {
        if (!isEmpty()) {
            peek().tick();
        }
    }

    @Override
    public boolean isPauseScreen() {
        return screenStack.stream().anyMatch(Screen::isPauseScreen);
    }

    @Override
    public void render(@NonNull GuiGraphics graphics, int mouseX, int mouseY, float tickDelta) {
        for (TRScreen trScreen : screenStack) {
            trScreen.render(graphics, mouseX, mouseY, tickDelta);
        }
    }

    @Override
    public void setInitialFocus() {
        if (!isEmpty()) {
            peek().setInitialFocus();
        }
    }

    @Override
    public void setInitialFocus(@NonNull GuiEventListener target) {
        if (!isEmpty()) {
            peek().setInitialFocus(target);
        }
    }

    @Override
    public void clearFocus() {
        if (!isEmpty()) {
            peek().clearFocus();
        }
    }

    @Override
    public void changeFocus(@NonNull ComponentPath componentPath) {
        if (!isEmpty()) {
            peek().changeFocus(componentPath);
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        if (!isEmpty())
            return peek().shouldCloseOnEsc();

        return super.shouldCloseOnEsc();
    }

    @Override
    public void onClose() {
        if (!isEmpty()) {
            pop().onClose();
            if(isEmpty()) {
                currentInstance = null;
                Minecraft.getInstance().setScreen(null);
            }
        } else {
            super.onClose();
        }
    }

    @Override
    public void clearWidgets() {
        for (TRScreen trScreen : screenStack) {
            trScreen.clearWidgets();
        }
    }

    @Override
    public void rebuildWidgets() {
        for (TRScreen trScreen : screenStack) {
            trScreen.rebuildWidgets();
        }
    }

    @Override
    public void fadeWidgets(float widgetFade) {
        if (!isEmpty()) {
            peek().fadeWidgets(widgetFade);
        }
    }

    @Override
    public boolean isInGameUi() {
        if (!isEmpty())
            return peek().isInGameUi();

        return super.isInGameUi();
    }

    @Override
    public boolean isAllowedInPortal() {
        if (!isEmpty())
            return peek().isAllowedInPortal();

        return super.isAllowedInPortal();
    }

    @Override
    public boolean panoramaShouldSpin() {
        if (!isEmpty())
            return peek().panoramaShouldSpin();

        return super.panoramaShouldSpin();
    }

    @Override
    public void onFilesDrop(@NonNull List<Path> files) {
        if (!isEmpty()) {
            peek().onFilesDrop(files);
        }
    }

    @Override
    public void updateNarratorStatus(boolean wasDisabled) {
        if (!isEmpty()) {
            peek().updateNarratorStatus(wasDisabled);
        }
    }

    @Override
    public @NonNull Component getUsageNarration() {
        if (!isEmpty())
            return peek().getUsageNarration();

        return super.getUsageNarration();
    }

    @Override
    public void updateNarratedWidget(@NonNull NarrationElementOutput output) {
        if (!isEmpty()) {
            peek().updateNarratedWidget(output);
        }
    }

    @Override
    public void updateNarrationState(@NonNull NarrationElementOutput output) {
        if (!isEmpty()) {
            peek().updateNarrationState(output);
        }
    }

    @Override
    public boolean shouldNarrateNavigation() {
        if (!isEmpty())
            return peek().shouldNarrateNavigation();

        return super.shouldNarrateNavigation();
    }

    @Override
    public void triggerImmediateNarration(boolean onlyChanged) {
        if (!isEmpty()) {
            peek().triggerImmediateNarration(onlyChanged);
        }
    }

    @Override
    public void handleDelayedNarration() {
        if (!isEmpty()) {
            peek().handleDelayedNarration();
        }
    }

    @Override
    public void afterKeyboardAction() {
        if (!isEmpty()) {
            peek().afterKeyboardAction();
        }
    }

    @Override
    public void afterMouseAction() {
        if (!isEmpty()) {
            peek().afterMouseAction();
        }
    }

    @Override
    public void afterMouseMove() {
        if (!isEmpty()) {
            peek().afterMouseMove();
        }
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        if (!isEmpty())
            return peek().isMouseOver(mouseX, mouseY);

        return super.isMouseOver(mouseX, mouseY);
    }
}
