package edu.project4.render;

import edu.project4.FractalImage;
import edu.project4.ImageConfig;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.Random;

public class SingleThreadRenderer implements Renderer {


    private final ImageConfig config;

    public SingleThreadRenderer(ImageConfig config) {
        this.config = config;
    }

    @Override
    public FractalImage render(FractalImage canvas, List<Transformation> variations, int samples,
                               int iterPerSample, int symmetry) {
        var rnd = new Random();
        var affines = getAffines(10);
        for (int num = 0; num < samples; ++num) {
            drawPerSample(rnd, canvas, config, iterPerSample, symmetry, affines,  variations);
        }
        return canvas;
    }
}
