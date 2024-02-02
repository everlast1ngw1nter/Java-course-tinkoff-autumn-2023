package edu.project4.processors;

import edu.project4.FractalImage;
import java.awt.Color;

public class GammaCorrection implements ImageProcessor {

    private final double gamma;

    public GammaCorrection(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        var normals = new double[image.width()][image.height()];
        var max = 0.0;
        for (int row = 0; row < image.width(); row++) {
            for (int col = 0; col < image.height(); col++) {
                if (image.getPixel(row, col).getHitCount() != 0) {
                    normals[row][col] = Math.log10(image.getPixel(row, col).getHitCount());
                    if (normals[row][col] > max) {
                        max = normals[row][col];
                    }
                }
            }
        }
        for (int row = 0; row < image.width(); row++) {
            for (int col = 0; col < image.height(); col++) {
                var color = image.getPixel(row, col).getColor();
                if (color == null) {
                    continue;
                }
                normals[row][col] /= max;
                var red = color.getRed() * Math.pow(normals[row][col], (1.0 / gamma));
                var green = color.getGreen() * Math.pow(normals[row][col], (1.0 / gamma));
                var blue = color.getBlue() * Math.pow(normals[row][col], (1.0 / gamma));
                image.getPixel(row, col).setColor(new Color((int) red, (int) green, (int) blue));
            }
        }
    }
}
