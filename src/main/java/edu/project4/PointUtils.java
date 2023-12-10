package edu.project4;

public class PointUtils {

    public static Point rotate(Point point, double rotation) {
        var x = point.x() * Math.cos(rotation) - point.y() * Math.sin(rotation);
        var y = point.x() * Math.sin(rotation) + point.y() * Math.cos(rotation);
        return new Point(x, y);
    }
}
