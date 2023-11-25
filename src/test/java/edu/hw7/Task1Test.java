package edu.hw7;

import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicLong;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    void plusOneInManyThreadsTest() throws InterruptedException {
        int threadsCount = 3;
        long timesInThread = 10000000;
        AtomicLong expected = new AtomicLong(threadsCount * timesInThread);
        assertEquals(Task1.plusOneInManyThreads(threadsCount, timesInThread).get(), expected.get());
    }
}
