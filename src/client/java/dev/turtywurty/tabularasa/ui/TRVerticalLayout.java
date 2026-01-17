package dev.turtywurty.tabularasa.ui;

import java.util.concurrent.atomic.AtomicInteger;

public class TRVerticalLayout extends TRLinearLayout {
    public TRVerticalLayout(TRAbstractWidget... children) {
        super(children);
    }

    @Override
    public void arrangeChildren() {
        if (children.isEmpty())
            return;

        int leftX = this.padding.left() + getX();
        int currentY = (inverted ? getHeight() - this.padding.bottom() : this.padding.top()) + getY();
        for (int i = 0; i < children.size(); i++) {
            TRAbstractWidget child = children.get(i);
            if (i == 0 && inverted) {
                currentY -= child.getHeight();
            }

            child.setPosition(leftX, currentY);
            currentY += (inverted ? -1 : 1) * (child.getHeight() + this.spacing);
        }
    }

    @Override
    public void calculateBounds(AtomicInteger width, AtomicInteger height) {
        int totalHeight = this.padding.top() + this.padding.bottom();
        int maxWidth = 0;

        for (TRAbstractWidget child : children) {
            totalHeight += child.getHeight();
            maxWidth = Math.max(maxWidth, child.getWidth());
        }

        if (!children.isEmpty()) {
            totalHeight += this.spacing * (children.size() - 1);
        }

        width.set(maxWidth + this.padding.left() + this.padding.right());
        height.set(totalHeight);
    }
}
