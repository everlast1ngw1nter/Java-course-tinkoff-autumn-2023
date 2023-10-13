package edu.hw1;

public final class Task2 {
    private Task2() {
    }

    private final static int DIGITS_COUNT = 10;

    public static int countDigits(final long number) {
        long absNumber = Math.abs(number);
        int counter = 0;
        do {
            absNumber /= DIGITS_COUNT;
            counter++;
        } while (absNumber != 0);
        return counter;
    }
}
