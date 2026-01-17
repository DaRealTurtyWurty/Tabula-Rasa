package dev.turtywurty.tabularasa.ui;

public record Padding(int top, int right, int bottom, int left) {
    private static final Padding NONE = new Padding(0, 0, 0, 0);

    public Padding(int vertical, int horizontal) {
        this(vertical, horizontal, vertical, horizontal);
    }

    public Padding(int all) {
        this(all, all, all, all);
    }

    public static Padding of(int top, int right, int bottom, int left) {
        return new Padding(top, right, bottom, left);
    }

    public static Padding of(int vertical, int horizontal) {
        return new Padding(vertical, horizontal);
    }

    public static Padding all(int all) {
        return new Padding(all);
    }

    public static Padding none() {
        return NONE;
    }
}
