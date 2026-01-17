package dev.turtywurty.tabularasa.ui;

import java.util.concurrent.atomic.AtomicInteger;

public class TRHorizontalLayout extends TRLinearLayout {
    public TRHorizontalLayout(TRAbstractWidget... children) {
        super(children);
    }

    @Override
    public void arrangeChildren() {
        if (children.isEmpty())
            return;

        int currentX = (inverted ? getWidth() - this.padding.right() : this.padding.left()) + getX();
        int topY = this.padding.top() + getY();
        for (int i = 0; i < children.size(); i++) {
            TRAbstractWidget child = children.get(i);
            if (i == 0 && inverted) {
                currentX -= child.getWidth();
            }

            child.setPosition(currentX, topY);
            currentX += (inverted ? -1 : 1) * (child.getWidth() + this.spacing);
        }
    }

    @Override
    public void calculateBounds(AtomicInteger width, AtomicInteger height) {
        int totalWidth = this.padding.left() + this.padding.right();
        int maxHeight = 0;

        for (TRAbstractWidget child : children) {
            totalWidth += child.getWidth();
            maxHeight = Math.max(maxHeight, child.getHeight());
        }

        if (!children.isEmpty()) {
            totalWidth += this.spacing * (children.size() - 1);
        }

        width.set(totalWidth);
        height.set(maxHeight + this.padding.top() + this.padding.bottom());
    }
}
