package edu.hw8;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task2Test {
    @Test
    void Task2FibonacciCalculatorTest() throws Exception {
        var listNumbers = List.of(10, 6, 7, 19, 3, 4);
        var answer = Task2FibonacciCalculator.calculate(listNumbers);
        assertArrayEquals(new long[] {34, 5, 8, 2584, 1, 2}, answer);
    }
}
