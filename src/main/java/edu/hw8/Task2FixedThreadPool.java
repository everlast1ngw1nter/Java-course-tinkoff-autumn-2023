package edu.hw8;

import java.util.ArrayList;
import java.util.List;

public class Task2FixedThreadPool implements Task2ThreadPool {

    private final List<Thread> threadList;
    private final List<Runnable> tasks;
    private final int threads;

    private Task2FixedThreadPool(int threads) {
        this.threads = threads;
        threadList = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public static Task2FixedThreadPool create(int threads) {
        return new Task2FixedThreadPool(threads);
    }

    @Override
    public void start() {
        for (var i = 0; i < threads; i++) {
            List<Runnable> threadTasks = new ArrayList<>();
            for (var j = i; j < tasks.size(); j = j + threads) {
                threadTasks.add(tasks.get(j));
            }
            threadList.add(new Thread(new MultiRunnable(threadTasks)));
        }
        for (var thread : threadList) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        tasks.add(runnable);
    }

    @Override
    public void close() throws Exception {
        for (var thread : threadList) {
            thread.join();
        }
    }

    private record MultiRunnable(List<Runnable> runnables) implements Runnable {
        @Override
            public void run() {
                for (var runnable : runnables) {
                    runnable.run();
                }
            }
        }
}
