package edu.hw1;

public final class Task6 {
    private Task6() {
    }

    public static int countK(final long number)
            throws IllegalArgumentException {
        final int numberDigit = 4;
        if (number < 0 | Task2.countDigits(number) != numberDigit) {
            throw new IllegalArgumentException();
        }
        return countK(number, 0);
    }

    private static int countK(final long number, final int depth) {
        final int endNumber = 6174;
        if (number == endNumber) {
            return depth;
        }
        int[] digitsPositions = getDigitsPositions(number);
        long maxNumber = getMaxNumber(digitsPositions);
        long minNumber = getMinNumber(digitsPositions);
        return countK(maxNumber - minNumber, depth + 1);
    }

    private static int[] getDigitsPositions(final long number) {
        final int digitsCount = 10;
        String temp = Long.toString(number);
        int[] digits = new int[digitsCount];
        for (int i = 0; i < temp.length(); i++) {
            digits[temp.charAt(i) - '0'] += 1;
        }
        return digits;
    }

    @SuppressWarnings("ModifiedControlVariable")
    private static long getMaxNumber(final int[] digitsPositions) {
        final int digitsCount = 10;
        long maxNumber = 0;
        int currDigitCounter = 0;
        for (int i = digitsPositions.length - 1; i > -1; i--) {
            if (digitsPositions[i] == currDigitCounter) {
                currDigitCounter = 0;
                continue;
            }
            currDigitCounter += 1;
            maxNumber = maxNumber * digitsCount + i;
            i++;
        }
        return maxNumber;
    }

    @SuppressWarnings("ModifiedControlVariable")
    private static long getMinNumber(final int[] digitsPositions) {
        final int digitsCount = 10;
        long minNumber = 0;
        int currDigitCounter = 0;
        for (int i = 0; i < digitsCount; i++) {
            if (digitsPositions[i] == currDigitCounter) {
                currDigitCounter = 0;
                continue;
            }
            currDigitCounter++;
            minNumber = minNumber * digitsCount + i;
            i--;
        }
        return minNumber;
    }
}
