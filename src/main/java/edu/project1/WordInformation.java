package edu.project1;

import java.util.HashSet;

public interface WordInformation {
    String getMask(HashSet<Character> chars);

    boolean isInWord(char letter);
}
