package edu.project1;

import java.util.HashSet;

public interface SessionInformation {

    HashSet<Character> getChars();

    String getMask();

    int getMistakes();

    boolean checkGuessedLetter(char letter);
}
