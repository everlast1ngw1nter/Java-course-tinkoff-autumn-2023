package edu.project4.render;

import edu.project4.FractalImage;
import edu.project4.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(FractalImage canvas, List<Transformation> variations, int samples,
                        int iterPerSample, int symmetry);
}
