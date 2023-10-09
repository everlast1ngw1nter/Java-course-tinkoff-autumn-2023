package edu.project1;

import java.util.HashSet;

public class WordInfo implements WordInformation {
    private final String word;

    private final HashSet<Character> wordLetters;

    public WordInfo(Dictionary dict) {
        word = dict.getWord();
        wordLetters = getLettersSet();
    }

    public String getMask(HashSet<Character> chars) {
        char[] arrayMask = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            arrayMask[i] = (chars.contains(arrayMask[i])) ? arrayMask[i] : '*';
        }
        return String.valueOf(arrayMask);
    }

    private HashSet<Character> getLettersSet() {
        HashSet<Character> set = new HashSet<>();
        for (char c : word.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public boolean isInWord(char letter) {
        return wordLetters.contains(letter);
    }
}
