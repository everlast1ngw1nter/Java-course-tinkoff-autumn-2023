package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task1 {

    private Task1() {
    }

    private static final String ALPHABET_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHABET_UPPER_CASE = ALPHABET_LOWER_CASE.toUpperCase();
    private static final Map<Character, Character> PAIRS = getAlphabetMirrorPairs();

    public static String atbash(String str) {
        char[] strArray = str.toCharArray();
        for (int i = 0; i < strArray.length; i++) {
            if (PAIRS.containsKey(strArray[i])) {
                strArray[i] = PAIRS.get(strArray[i]);
            }
        }
        return String.valueOf(strArray);
    }

    private static Map<Character, Character> getAlphabetMirrorPairs() {
        var mirrorPairs = new HashMap<Character, Character>();
        getMirrorPairsString(mirrorPairs, ALPHABET_LOWER_CASE);
        getMirrorPairsString(mirrorPairs, ALPHABET_UPPER_CASE);
        return mirrorPairs;
    }

    private static void getMirrorPairsString(Map<Character, Character> pairs, String str) {
        for (int i = 0; i < str.length(); i++) {
            pairs.put(str.charAt(i), str.charAt(str.length() - i - 1));
        }
    }
}
