package edu.hw2;

public class Task2 {
    private Task2() {
    }

    public static class Rectangle {
        private final double width;
        private final double height;

        Rectangle setWidth(double width) {
            return new Rectangle(width, this.height);
        }

        double getWidth() {
            return width;
        }

        Rectangle setHeight(double height) {
            return new Rectangle(this.width, height);
        }

        double getHeight() {
            return height;
        }

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        private final double side;

        @Override
        Rectangle setWidth(double width) {
            return new Rectangle(width, side);
        }

        @Override
        Rectangle setHeight(double height) {
            return new Rectangle(side, height);
        }

        public Square(double side) {
            super(side, side);
            this.side = side;
        }
    }
}
