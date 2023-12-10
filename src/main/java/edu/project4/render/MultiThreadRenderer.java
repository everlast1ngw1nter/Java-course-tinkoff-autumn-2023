package edu.project4.render;

import edu.project4.AffineTransformation;
import edu.project4.FractalImage;
import edu.project4.Point;
import edu.project4.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static edu.project4.PointUtils.rotate;

public class MultiThreadRenderer implements Renderer{

    private static final double XMIN = -1.777;
    private static final double XMAX = 1.777;
    private static final double YMIN = -1;
    private static final double YMAX = 1;

    @Override
    public FractalImage render(FractalImage canvas, List<Transformation> variations, int samples,
                               int iterPerSample, int symmetry) {
        var rnd = new Random();
        var affines = new ArrayList<AffineTransformation>();
        for (var k = 0; k < 1; k++) {
            affines.add(new AffineTransformation());
        }
        for (int num = 0; num < samples; ++num) {
            var newX = rnd.nextDouble(XMIN,XMAX);
            var newY = rnd.nextDouble(YMIN,YMAX);
            for (int step = -20; step < iterPerSample; step++) {
                var affineTransformation = affines.get(rnd.nextInt(0, affines.size()));
                var pw = new Point(newX, newY);
                var tpw = affineTransformation.transform(pw);
                var variation = variations.get(rnd.nextInt(0, variations.size()));
                pw = variation.apply(tpw);
                if (step >= 0 && XMIN <= pw.x() && XMAX >= pw.x()
                        && YMIN <= pw.y() && YMAX >= pw.y()) {
                    double theta2 = 0.0;
                    for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                        var pwr = rotate(pw, theta2);
                        var x1 = canvas.width() - (int)(((XMAX-pwr.x())/(XMAX-XMIN)) * canvas.width());
                        var y1 = canvas.height() - (int)(((YMAX-pwr.y())/(YMAX-YMIN)) * canvas.height());
                        pwr = new Point(x1, y1);
                        if (!canvas.contains(pwr)) {
                            continue;
                        }
                        updatePixel(canvas, pwr, affineTransformation);
                    }
                }
            }
        }
        return canvas;
    }
}
