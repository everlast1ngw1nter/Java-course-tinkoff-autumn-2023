package edu.project4.render;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.PointUtils;
import edu.project4.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.Random;
import static edu.project4.PointUtils.rotate;

public class SingleThreadRenderer implements Renderer{
    @Override
    public FractalImage render(FractalImage canvas, List<Transformation> variations, int samples,
                               short iterPerSample, int symmetry) {
        var rnd = new Random();
        for (int num = 0; num < samples; ++num) {
            Point pw = PointUtils.getRandomPoint(0, 0, canvas.height(), canvas.width());

            for (short step = 0; step < iterPerSample; step++) {
                Transformation variation =  variations.get(rnd.nextInt(0, variations.size()));
                pw = variation.apply(pw);
                double theta2 = 0.0;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, s++) {
                    var pwr = rotate(pw, theta2);
                    if (!canvas.contains(pwr)) {
                        continue;
                    }
                    Pixel pixel = canvas.pixel(pwr);
                    changeColor(pixel);

                    // 1. делаем лок на время работы с пикселем
                    // 2. подмешиваем цвет и увеличиваем hit count
                }
            }
        }
        return canvas;
    }

    private void changeColor(Pixel pixel) {
        pixel.incrementHitCount();
    }
}
