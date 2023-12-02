package edu.hw8;

import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void myFixedThreadPoolTest() throws Exception {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        try (var pool = Task2FixedThreadPool.create(3))  {
            for (var i = 0; i < 17; i++) {
                pool.execute(runnable);
            }
            pool.start();
        }
    }
}
