package edu.hw1;

import java.util.Arrays;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(final long[] array1, final long[] array2)
            throws IllegalArgumentException {
        if (array1.length * array2.length == 0) {
            throw new IllegalArgumentException();
        }
        long minArray1 = min(array1);
        long maxArray1 = max(array1);
        long minArray2 = min(array2);
        long maxArray2 = max(array2);
        return minArray1 > minArray2 & maxArray1 < maxArray2;
    }

    private static long min(final long[] array) {
        return Arrays.stream(array).min().getAsLong();
    }

    private static long max(final long[] array) {
        return Arrays.stream(array).max().getAsLong();
    }
}
