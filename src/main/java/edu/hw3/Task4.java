package edu.hw3;

public class Task4 {

    private Task4() {
    }

    private static final int MIN_ARABIC = 1;
    private static final int MAX_ARABIC = 3999;
    private static final int MAX_ARABIC_DIGITS = 4;


    @SuppressWarnings("MagicNumber")
    public static String convertToRoman(int arabicNumber)  {
        if (arabicNumber < MIN_ARABIC || arabicNumber > MAX_ARABIC) {
            throw new IllegalArgumentException("The Arabic number must be in the range from 1 to 3999");
        }
        int[] digits = getNumberDigits(arabicNumber);
        StringBuilder builder = new StringBuilder();
        builder.append(convertDigitToRoman(digits[0], new RomanSymbols("M", "", "")));
        builder.append(convertDigitToRoman(digits[1], new RomanSymbols("C", "D", "M")));
        builder.append(convertDigitToRoman(digits[2], new RomanSymbols("X", "L", "C")));
        builder.append(convertDigitToRoman(digits[3], new RomanSymbols("I", "V", "X")));
        return builder.toString();
    }

    private static int[] getNumberDigits(int arabicNumber) {
        String strNumber = String.format("%0" + MAX_ARABIC_DIGITS + "d", arabicNumber);
        int[] digits = new int[MAX_ARABIC_DIGITS];
        for (int i = 0; i < strNumber.length(); i++) {
            digits[i] = strNumber.charAt(i) - '0';
        }
        return digits;
    }

    @SuppressWarnings("MagicNumber")
    private static String convertDigitToRoman(int digit, RomanSymbols symbols) {
        return switch (digit) {
            case 0, 1, 2, 3 -> symbols.lowest.repeat(digit);
            case 4 ->  symbols.lowest + symbols.middle;
            case 5, 6, 7, 8 -> symbols.middle + symbols.lowest.repeat(digit - 5);
            case 9 ->  symbols.lowest + symbols.biggest;
            default -> throw new IllegalArgumentException();
        };
    }

    private record RomanSymbols(String lowest, String middle, String biggest) {
    }
}
