package edu.hw7;

import java.util.stream.LongStream;

public class Task2 {

    private Task2() {
    }

    public static Long getFactorial(int number) {
        var allNumbers = LongStream
                .rangeClosed(1, number)
                .boxed()
                .toList();
        return allNumbers
                .parallelStream()
                .reduce(1L, (x, y) -> x * y);
    }
}
