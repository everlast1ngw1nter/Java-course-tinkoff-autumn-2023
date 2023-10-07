package edu.hw1;

public final class Task2 {
    private Task2() {
    }

    public static int countDigits(final long number) {
        final int digitsCount = 10;
        long absNumber = Math.abs(number);
        int counter = 0;
        do {
            absNumber /= digitsCount;
            counter++;
        } while (absNumber != 0);
        return counter;
    }
}
