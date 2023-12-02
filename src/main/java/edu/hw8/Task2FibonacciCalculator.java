package edu.hw8;

import java.util.List;

public class Task2FibonacciCalculator {

    public static long[] calculate(List<Integer> numbers)
            throws Exception {
        var countedFibonacci = new long[numbers.size()];
        try (var pool = Task2FixedThreadPool.create(4)) {
            for (var i = 0; i < numbers.size(); i++) {
                pool.execute(new RunnableFibonacciCalculator(countedFibonacci, i, numbers.get(i)));
            }
            pool.start();
        }
        return countedFibonacci;
    }

    private record RunnableFibonacciCalculator(long[] answerList, int listIndex, int number)
            implements Runnable {
        @Override
            public void run() {
                if (number < 2) {
                    this.answerList[listIndex] = 1;
                    return;
                }
                long prev = 0;
                long curr = 1;
                for (var i = 0; i < number - 2; i++) {
                    var next = prev + curr;
                    prev = curr;
                    curr = next;
                }
                this.answerList[listIndex] = curr;
            }
        }
}
