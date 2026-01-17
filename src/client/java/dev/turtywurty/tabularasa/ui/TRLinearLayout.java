package dev.turtywurty.tabularasa.ui;

public abstract class TRLinearLayout extends TRLayout {
    protected Padding padding = Padding.none();
    protected boolean inverted = false;
    protected int spacing = 0;

    public TRLinearLayout(TRAbstractWidget... children) {
        for (TRAbstractWidget child : children) {
            addChild(child);
        }
    }

    public TRLinearLayout setPadding(Padding padding) {
        if (padding == null)
            padding = Padding.none();

        Padding original = this.padding;
        this.padding = padding;
        if (!original.equals(padding)) {
            updateContents();
        }

        return this;
    }

    public TRLinearLayout setInverted(boolean inverted) {
        boolean original = this.inverted;
        this.inverted = inverted;
        if (original != inverted) {
            updateContents();
        }

        return this;
    }

    public TRLinearLayout setSpacing(int spacing) {
        int original = this.spacing;
        this.spacing = spacing;
        if (original != spacing) {
            updateContents();
        }

        return this;
    }

    public Padding getPadding() {
        return padding;
    }

    public boolean isInverted() {
        return inverted;
    }

    public int getSpacing() {
        return spacing;
    }
}
