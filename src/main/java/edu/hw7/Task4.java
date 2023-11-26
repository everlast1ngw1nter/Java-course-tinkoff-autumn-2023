package edu.hw7;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Task4 {

    private Task4() {
    }

    private static final double RADIUS = 1;

    @SuppressWarnings("MagicNumber")
    public static double singleThreadPiCounter(int totalSimulations) {
        var circleCount = 0;
        var totalCount = 0;
        var rnd = new Random();

        for (var i = 0; i < totalSimulations; i++) {
            if (isInCircle(rnd.nextDouble(0, 1),
                    rnd.nextDouble(0, 1), RADIUS)) {
                circleCount++;
            }
            totalCount++;
        }

        return ((double) circleCount / totalCount) * 4;
    }

    @SuppressWarnings("MagicNumber")
    public static double multiThreadPiCounter(int threads, int totalSimulations)
            throws InterruptedException, ExecutionException {

        var perEachThread = totalSimulations / threads;
        var circleCount = 0;
        var totalCount = 0;

        Callable<ThreadSimulationResult> callable = () -> {
            var threadCircleCount = 0;
            var threadTotalCount = 0;
            var currRnd = ThreadLocalRandom.current();
            for (var i = 0; i < perEachThread; i++) {
                if (isInCircle(currRnd.nextDouble(0, 1),
                        currRnd.nextDouble(0, 1), RADIUS)) {
                    threadCircleCount++;
                }
                threadTotalCount++;
            }
            return new ThreadSimulationResult(threadCircleCount, threadTotalCount);
        };

        try (ExecutorService service = Executors.newFixedThreadPool(threads)) {
            var futures = service.invokeAll(Collections.nCopies(threads, callable).stream().toList());
            for (var future : futures) {
                circleCount += future.get().circleCount;
                totalCount += future.get().totalCount;
            }
        }

        return ((double) circleCount / totalCount) * 4;
    }

    private static boolean isInCircle(double x, double y, double r) {
        return x * x + y * y <= r * r;
    }

    private record ThreadSimulationResult(int circleCount, int totalCount) {}
}
