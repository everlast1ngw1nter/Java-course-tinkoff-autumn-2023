package edu.hw7;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {

    private static final double EPSILON = 0.01d;

    @Test
    void singleThreadPiCounterTest() {
        for (var i = 0; i < 10; i++) {
            assertTrue(Math.abs(Math.PI - Task4.singleThreadPiCounter(1000000)) < EPSILON);
        }
    }

    @Test
    void multiThreadPiCounterTest()
            throws ExecutionException, InterruptedException {
        for (var threads = 1; threads < 9; threads++) {
            for (var i = 0; i < 10; i++) {
                assertTrue(Math.abs(Math.PI - Task4.multiThreadPiCounter(threads, 1000000)) < EPSILON);
            }
        }
    }
}
