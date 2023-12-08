package edu.project4;

public record Rect(double width, double height) {
    boolean contains(Point p) {
        return p.x() >= 0
                && p.y() >= 0
                && p.x() < width
                && p.y() < height;
    }
}
