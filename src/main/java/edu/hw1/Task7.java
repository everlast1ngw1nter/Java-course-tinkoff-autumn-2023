package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(final int n, final int shift) {
        return rotate(n, -shift);
    }

    public static int rotateRight(final int n, final int shift) {
        return rotate(n, shift);
    }

    private static int rotate(final int n, final int shift) {
        String binInt = Integer.toBinaryString(n);
        char[] numberChars = binInt.toCharArray();
        arrayRotate(numberChars, shift);
        String binNewInt = String.valueOf(numberChars);
        return Integer.parseInt(binNewInt, 2);
    }

    private static void arrayRotate(final char[] arrayChars, final int shift) {
        int newShift = shift % arrayChars.length;
        if (newShift < 0) {
            newShift += arrayChars.length;
        }
        int currIndex = 0;
        char currElem = arrayChars[0];
        for (int i = 0; i < arrayChars.length; i++) {
            currIndex = (currIndex + newShift) % arrayChars.length;
            char temp = arrayChars[currIndex];
            arrayChars[currIndex] = currElem;
            currElem = temp;
        }
    }
}
