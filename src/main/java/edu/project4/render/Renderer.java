package edu.project4.render;

import edu.project4.AffineTransformation;
import edu.project4.FractalImage;
import edu.project4.Point;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(FractalImage canvas, List<Transformation> variations, int samples,
                        int iterPerSample, int symmetry);
    default List<AffineTransformation> getAffineTransformation(int count) {
        var affines = new ArrayList<AffineTransformation>();
        for (var k = 0; k < count; k++) {
            affines.add(new AffineTransformation());
        }
        return affines;
    }

    default void updatePixel(FractalImage canvas, Point pwr, AffineTransformation affineTransformation) {
        var pixel = canvas.pixel(pwr);
        synchronized (pixel) {
            if (pixel.getHitCount() == 0) {
                pixel.setColor(affineTransformation.getColor());
            } else {
                var affineColor = affineTransformation.getColor();
                var pixelColor = pixel.getColor();
                pixel.setColor(new Color(
                        (pixelColor.getRed() + affineColor.getRed()) / 2,
                        (pixelColor.getGreen() + affineColor.getGreen()) / 2,
                        (pixelColor.getBlue() + affineColor.getBlue()) / 2));
            }
            pixel.incrementHitCount();
        }
    }
}
