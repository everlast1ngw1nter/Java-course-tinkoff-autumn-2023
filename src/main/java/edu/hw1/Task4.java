package edu.hw1;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(final String string) {
        char[] chars = string.toCharArray();
        for (var i = 0; i < string.length() / 2; i++) {
            char temp = chars[2 * i];
            chars[2 * i] = chars[2 * i + 1];
            chars[2 * i + 1] = temp;
        }
        return String.valueOf(chars);
    }
}
