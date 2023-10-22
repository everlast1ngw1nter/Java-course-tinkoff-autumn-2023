package edu.hw2;

public class Task2Mutable {
    public static class Rectangle {
        private int width;
        private int height;

        public Rectangle(int width, int height) {
            this.height = height;
            this.width = width;
        }

        void setWidth(int width) throws NoSuchMethodException {
            this.width = width;
        }

        void setHeight(int height) throws NoSuchMethodException {
            this.height = height;
        }

        double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {

        private int side;

        public Square(int side) {
            super(side, side);
        }

        @Override
        void setWidth(int width) {
            setSide(width);
        }

        @Override
        void setHeight(int height) {
            setSide(height);
        }

        void setSide(int side) {
            this.side = side;
        }

        @Override
        double area() {
            return side * side;
        }
    }
}
