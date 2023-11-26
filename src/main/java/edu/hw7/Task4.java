package edu.hw7;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Task4 {

    public static void singleThreadPiCounter() {
        long startTime = System.nanoTime();

        var circleCount = 0;
        var totalCount = 0;
        var rnd = new Random();
        for (var i = 0; i < 100000000; i++) {
            if (isInCircle(rnd.nextDouble(0, 1), rnd.nextDouble(0, 1), 1 )) {
                circleCount++;
            }
            totalCount++;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(((double) circleCount / totalCount) * 4);
        System.out.println(duration);
    }

    public static void multiThreadPiCounter() throws InterruptedException {
        var startTime = System.nanoTime();
        var threads = 2;
        var totalSimulations = 100000000;
        var perEachThread = totalSimulations / threads;
        var circleCount = new AtomicInteger();
        var totalCount = new AtomicInteger();
        Runnable runnable = () -> {
            var currRnd = ThreadLocalRandom.current();
            for (var i = 0; i < perEachThread; i++) {
                if (isInCircle(currRnd.nextDouble(0, 1),
                        currRnd.nextDouble(0, 1), 1 )) {
                    circleCount.getAndIncrement();
                }
                totalCount.getAndIncrement();
            }
        };
        var tasks = Stream.generate(() -> new Thread(runnable))
                .limit(threads)
                .toList();
        for (var task : tasks) {
            task.start();
        }
        for (var task : tasks) {
            task.join();
        }
        var endTime = System.nanoTime();
        var duration = endTime - startTime;
        System.out.println(((double) circleCount.get() / totalCount.get()) * 4);
        System.out.println(duration);
    }

    private static boolean isInCircle(double x, double y, double r) {
        return x * x + y * y <= r * r;
    }

    public static void main(String[] args)
            throws InterruptedException {
        multiThreadPiCounter();
        singleThreadPiCounter();
    }
}
