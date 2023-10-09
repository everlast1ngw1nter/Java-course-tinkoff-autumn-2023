package edu.project1;

import java.util.Random;

public class WordDict implements Dictionary {

    private final static String[] WORDS = {"hangman", "java", "backend", "tinkoff"};
    private final static Random RND = new Random();

    public String getWord() {
        int rndNumber = RND.nextInt(WORDS.length);
        return WORDS[rndNumber];
    }
}
