package edu.project4;

import java.awt.Color;

public class Pixel {

    private Color color;

    private int hitCount;

    public void incrementHitCount() {
        hitCount++;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
