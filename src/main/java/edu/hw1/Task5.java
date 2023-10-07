package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(final long number) {
        if (Task2.countDigits(number) == 1) {
            return false;
        }
        if (isPalindrome(number)) {
            return true;
        }
        long descendant = getDescendant(number);
        return isPalindromeDescendant(descendant);
    }

    private static boolean isPalindrome(final long number) {
        char[] chars = ("" + number).toCharArray();
        for (var i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    private static long getDescendant(final long number) {
        String temp = Long.toString(number);
        int[] digits = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            digits[i] = temp.charAt(i) - '0';
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < digits.length / 2; i++) {
            builder.append(digits[2 * i] + digits[2 * i + 1]);
        }
        if (digits.length % 2 == 1) {
            builder.append(digits[digits.length - 1]);
        }
        return Long.parseLong(builder.toString());
    }
}
