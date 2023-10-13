package edu.project1;

import java.util.Arrays;
import java.util.HashSet;

public class WordInfo implements WordInformation {
    private final String word;

    private final char[] mask;

    public WordInfo(Dictionary dict) {
        word = dict.getWord();
        mask = new char[word.length()];
        Arrays.fill(mask, '*');
    }

    public String getMask(HashSet<Character> chars) {
        for (int i = 0; i < word.length(); i++) {
            if (chars.contains(word.charAt(i))) {
                mask[i] = word.charAt(i);
            }
        }
        return String.valueOf(mask);
    }

    public boolean isInWord(char letter) {
        return word.contains(String.valueOf(letter));
    }
}
