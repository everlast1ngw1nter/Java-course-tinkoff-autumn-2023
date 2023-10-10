package edu.hw1;

public final class Task6 {
    private Task6() {
    }

    private final static int NUMBER_DIGIT = 4;
    private final static int END_NUMBER = 6174;
    private final static int DIGITS_COUNT = 10;

    @SuppressWarnings("MagicNumber")
    public static int countK(final long number)
            throws IllegalArgumentException {
        if (number < 0 | Task2.countDigits(number) != NUMBER_DIGIT
                | number == 1000 | number % 1111 == 0) {
            throw new IllegalArgumentException();
        }
        return countK(number, 0);
    }

    private static int countK(final long number, final int depth) {

        if (number == END_NUMBER) {
            return depth;
        }
        int[] digitsPositions = getDigitsPositions(number);
        long maxNumber = getMaxNumber(digitsPositions);
        long minNumber = getMinNumber(digitsPositions);
        return countK(maxNumber - minNumber, depth + 1);
    }

    private static int[] getDigitsPositions(final long number) {
        String temp = Long.toString(number);
        int[] digits = new int[DIGITS_COUNT];
        for (int i = 0; i < temp.length(); i++) {
            digits[temp.charAt(i) - '0'] += 1;
        }
        return digits;
    }

    @SuppressWarnings("ModifiedControlVariable")
    private static long getMaxNumber(final int[] digitsPositions) {
        long maxNumber = 0;
        int currDigitCounter = 0;
        for (int i = digitsPositions.length - 1; i > -1; i--) {
            if (digitsPositions[i] == currDigitCounter) {
                currDigitCounter = 0;
                continue;
            }
            currDigitCounter += 1;
            maxNumber = maxNumber * DIGITS_COUNT + i;
            i++;
        }
        return maxNumber;
    }

    @SuppressWarnings("ModifiedControlVariable")
    private static long getMinNumber(final int[] digitsPositions) {
        long minNumber = 0;
        int currDigitCounter = 0;
        for (int i = 0; i < DIGITS_COUNT; i++) {
            if (digitsPositions[i] == currDigitCounter) {
                currDigitCounter = 0;
                continue;
            }
            currDigitCounter++;
            minNumber = minNumber * DIGITS_COUNT + i;
            i--;
        }
        return minNumber;
    }
}
