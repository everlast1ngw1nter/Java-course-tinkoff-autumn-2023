package edu.project4;

public record FractalImage(Pixel[][] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        var pixels = new Pixel[width][height];
        for (var i = 0; i < width; i++) {
            for (var j = 0; j < height; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        return new FractalImage(pixels, width, height);
    }
    public boolean contains(int x, int y) {
        return x >= 0
                && y >= 0
                && x < width
                && y < height;
    }

    public boolean contains(Point point) {
        return contains((int) point.x(),(int) point.y());
    }

    public Pixel pixel(int x, int y) {
        return data[x][y];
    }

    public Pixel pixel(Point point) {
        return data[(int) point.x()][(int) point.y()];
    }
}
