package edu.project1;

import java.util.HashSet;

public class SessionInfo implements SessionInformation {
    private final WordInformation wordInfo;
    private final HashSet<Character> chars = new HashSet<>();

    public HashSet<Character> getChars() {
        return chars;
    }

    public SessionInfo(WordInformation wordInformation) {
        wordInfo = wordInformation;
        mask = wordInfo.getMask(chars);
    }

    private String mask;

    public String getMask() {
        return mask;
    }

    private int mistakes = 0;

    public int getMistakes() {
        return mistakes;
    }

    public boolean checkGuessedLetter(char letter) {
        if (wordInfo.isInWord(letter)) {
            chars.add(letter);
            mask = wordInfo.getMask(chars);
            return true;
        }
        chars.add(letter);
        mistakes += 1;
        return false;
    }
}
