package edu.project4;

import java.util.Random;

public class PointUtils {

    public static final Random rnd = new Random();

    public static Point getRandomPoint(double x1, double y1, double x2, double y2) {
        return new Point(rnd.nextDouble(x1, x2), rnd.nextDouble(y1, y2));
    }

    public static Point rotate(Point point, double rotation) {
        var x = point.x() * Math.cos(rotation) - point.y() * Math.sin(rotation);
        var y = point.x() * Math.sin(rotation) + point.y() * Math.cos(rotation);
        return new Point(x, y);
    }
}
