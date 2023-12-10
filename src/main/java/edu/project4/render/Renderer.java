package edu.project4.render;

import edu.project4.AffineTransformation;
import edu.project4.FractalImage;
import edu.project4.ImageConfig;
import edu.project4.Point;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static edu.project4.PointUtils.rotate;

@FunctionalInterface
public interface Renderer {
    FractalImage render(FractalImage canvas, List<Transformation> variations, int samples,
                        int iterPerSample, int symmetry);

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

    default List<AffineTransformation> getAffines(int count) {
        var affines = new ArrayList<AffineTransformation>();
        for (var k = 0; k < count; k++) {
            affines.add(new AffineTransformation());
        }
        return affines;
    }

    default void drawSymmetry(FractalImage canvas, Point pw, AffineTransformation affineTransformation,
                              int symmetry, ImageConfig cfg) {
        double theta2 = 0.0;
        for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
            var pwr = rotate(pw, theta2);
            var x1 = canvas.width() - (int) (((cfg.xMax() - pwr.x()) / (cfg.xMax() - cfg.xMin())) * canvas.width());
            var y1 = canvas.height() - (int) (((cfg.yMax() - pwr.y()) / (cfg.yMax() - cfg.yMin())) * canvas.height());
            pwr = new Point(x1, y1);
            if (!canvas.contains(pwr)) {
                continue;
            }
            updatePixel(canvas, pwr, affineTransformation);
        }
    }

    default void drawPerSample(Random rnd, FractalImage canvas, ImageConfig cfg, int iterPerSample, int symmetry,
                               List<AffineTransformation> affines, List<Transformation> variations) {
        var newX = rnd.nextDouble(cfg.xMin(), cfg.xMax());
        var newY = rnd.nextDouble(cfg.yMin(), cfg.yMax());
        for (int step = 0; step < iterPerSample; step++) {
            var affineTransformation = affines.get(rnd.nextInt(0, affines.size()));
            var variation = variations.get(rnd.nextInt(0, variations.size()));
            var pw = variation.apply(affineTransformation.transform(new Point(newX, newY)));
            if (cfg.xMin() <= pw.x() && cfg.xMax() >= pw.x()
                    && cfg.yMin() <= pw.y() && cfg.yMax() >= pw.y()) {
                drawSymmetry(canvas, pw, affineTransformation, symmetry, cfg);
            }
        }
    }
}
