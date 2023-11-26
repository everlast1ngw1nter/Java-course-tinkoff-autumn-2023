package edu.hw7;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Task1 {

    private Task1() {
    }

    public static AtomicLong plusOneInManyThreads(int threadsCount, long timesInThread)
            throws InterruptedException {
        var result = new AtomicLong(0);
        Runnable runnable = () -> {
            for (var i = 0; i < timesInThread; i++) {
                result.getAndIncrement();
            }
        };
        var threads = Stream.generate(() -> new Thread(runnable))
                .limit(threadsCount)
                .toList();
        for (var thread : threads) {
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }
        return result;
    }
}
