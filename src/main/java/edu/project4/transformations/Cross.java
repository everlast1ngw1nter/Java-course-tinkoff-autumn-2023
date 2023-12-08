package edu.project4.transformations;

import edu.project4.Point;

public class Cross implements Transformation {
    @Override
    public Point apply(Point point) {
        var c = Math.sqrt(1 / Math.pow(point.x() * point.x() - point.y() * point.y(), 2));
        return new Point(c * point.x(), c * point.y());
    }
}
