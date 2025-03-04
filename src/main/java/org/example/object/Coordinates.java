package org.example.object;

import static java.lang.Math.sqrt;

public class Coordinates {
    private final int x;
    private final double y;

    public Coordinates(int x, double y) {
        this.x = x;
        this.y = y;
    }

    public static int compare(Coordinates x, Coordinates y) {
        return Double.compare(x.countLength(), y.countLength());
    }

    private double countLength() {
        return sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "Coordinates {" +
                "x=" + x +
                ", y=" + y +
                "}";
    }
}
