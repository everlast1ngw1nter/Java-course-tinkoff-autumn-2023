package edu.project4.render;

import edu.project4.AffineTransformation;
import edu.project4.FractalImage;
import edu.project4.ImageConfig;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class MultiThreadRenderer implements Renderer {

    private final ImageConfig config;

    private final int threads;

    public MultiThreadRenderer(ImageConfig config, int threads) {
        this.config = config;
        this.threads = threads;
    }

    @Override
    public FractalImage render(FractalImage canvas, List<Transformation> variations, int samples,
                               int iterPerSample, int symmetry) {
        var affines = getAffines(10);
        try (var pool = Executors.newFixedThreadPool(threads)) {
            var tasks = Stream.generate(() -> new RunnableDrawer(
                    samples / threads, iterPerSample,
                            affines, variations, canvas, symmetry))
                    .limit(threads)
                    .toList();
            var futures = pool.invokeAll(tasks);
            for (var future : futures) {
                future.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return canvas;
    }

    private class RunnableDrawer implements Callable<Void> {
        private final int samples;
        private final int iterPerSample;
        private final int symmetry;
        private final List<AffineTransformation> affines;
        private final List<Transformation> variations;
        private final FractalImage canvas;

        RunnableDrawer(int samples, int iterPerSample, List<AffineTransformation> affines,
                       List<Transformation> variations, FractalImage canvas, int symmetry) {
            this.samples = samples;
            this.iterPerSample = iterPerSample;
            this.affines = affines;
            this.variations = variations;
            this.canvas = canvas;
            this.symmetry = symmetry;
        }

        @Override
        public Void call() {
            for (int num = 0; num < samples; ++num) {
                var rnd = ThreadLocalRandom.current();
                drawPerSample(rnd, canvas, config, iterPerSample, symmetry, affines,  variations);
            }
            return null;
        }
    }
}
