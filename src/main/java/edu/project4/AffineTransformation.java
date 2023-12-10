package edu.project4;

import java.awt.Color;
import java.util.Random;

public class AffineTransformation {

    private static final int MIN = -1;

    private static final int MAX = 1;

    private static final int MAX_RGB_COLOR = 255;

    private double a;
    private double b;
    private final double c;
    private double d;
    private double e;
    private final double f;
    private final int red;
    private final int green;
    private final int blue;

    public AffineTransformation() {
        var rnd = new Random();
        do {
            a = rnd.nextDouble(MIN, MAX);
            b = rnd.nextDouble(MIN,  MAX);
            d = rnd.nextDouble(MIN,  MAX);
            e = rnd.nextDouble(MIN,  MAX);
        } while (a * a + b * b + d * d + e * e >= 1 + (a * e - b * d) *  (a * e - b * d)
                    || a * a + d * d >= 1
                    || b * b + e * e >= 1);
        c = rnd.nextDouble(MIN,  MAX);
        f = rnd.nextDouble(MIN,  MAX);
        red = rnd.nextInt(0, MAX_RGB_COLOR);
        green = rnd.nextInt(0, MAX_RGB_COLOR);
        blue = rnd.nextInt(0, MAX_RGB_COLOR);
    }

    public Point transform(Point point) {
        return new Point(a * point.x() + b * point.y() + c, d * point.x() + e * point.y() + f);
    }

    public Color getColor() {
        return new Color(red, green, blue);
    }
}
