package edu.project4;

public class Pixel {

    private int r;

    private int g;

    private int b;

    private int hitCount;

    public Pixel() {
        r = 0;
        g = 0;
        b = 0;
        hitCount = 0;
    }

    public void incrementHitCount() {
        hitCount++;
    }

    public int getHitCount() {
        return hitCount;
    }
}
