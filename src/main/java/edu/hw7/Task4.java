package edu.hw7;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Task4 {
    
    private static final double r = 1;

    public static void singleThreadPiCounter(int totalSimulations) {
        var startTime = System.nanoTime();
        var circleCount = 0;
        var totalCount = 0;
        var rnd = new Random();

        for (var i = 0; i < totalSimulations; i++) {
            if (isInCircle(rnd.nextDouble(0, 1),
                    rnd.nextDouble(0, 1), r )) {
                circleCount++;
            }
            totalCount++;
        }

        var endTime = System.nanoTime();
        var duration = endTime - startTime;
        System.out.println(((double) circleCount / totalCount) * 4);
        System.out.println(duration);
    }

    public static void multiThreadPiCounter(int threads, int totalSimulations)
            throws InterruptedException, ExecutionException {

        var perEachThread = totalSimulations / threads;
        var circleCount = 0;
        var totalCount = 0;
        var startTime = System.nanoTime();

        Callable<ThreadSimulationResult> runnable = () -> {
            var threadCircleCount = 0;
            var threadTotalCount = 0;
            var currRnd = ThreadLocalRandom.current();
            for (var i = 0; i < perEachThread; i++) {
                if (isInCircle(currRnd.nextDouble(0, 1),
                        currRnd.nextDouble(0, 1), r )) {
                    threadCircleCount++;
                }
                threadTotalCount++;
            }
            return new ThreadSimulationResult(threadCircleCount, threadTotalCount);
        };

        try (ExecutorService service = Executors.newFixedThreadPool(threads)) {
            var futures = service.invokeAll(Collections.nCopies(5, runnable).stream().toList());
            for (var future : futures) {
                circleCount += future.get().circleCount;
                totalCount += future.get().totalCount;
            }
        }

        var endTime = System.nanoTime();
        var duration = endTime - startTime;
        System.out.println(((double) circleCount / totalCount) * 4);
        System.out.println(duration);
    }

    private static boolean isInCircle(double x, double y, double r) {
        return x * x + y * y <= r * r;
    }

    private record ThreadSimulationResult(int circleCount, int totalCount) {}

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        var totalSimulations = 100000000;
        var threads = 6;
        multiThreadPiCounter(threads, totalSimulations);
        singleThreadPiCounter(totalSimulations);
    }
}
