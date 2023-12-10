package edu.project4.transformations;

import edu.project4.Point;

public class Spherical implements Transformation {
    @Override
    public Point apply(Point point) {
        var c = 1 / (point.x() * point.x() + point.y() * point.y());
        return new Point(c * point.x(), c * point.y());
    }
}
