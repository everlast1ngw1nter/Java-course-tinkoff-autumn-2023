package edu.project4.transformations;

import edu.project4.Point;

public class Exponential implements Transformation {
    @Override
    public Point apply(Point point) {
        var x = Math.exp(point.x() - 1) * Math.cos(Math.PI * point.y());
        var y = Math.exp(point.x() - 1) * Math.sin(Math.PI * point.y());
        return new Point(x, y);
    }
}
