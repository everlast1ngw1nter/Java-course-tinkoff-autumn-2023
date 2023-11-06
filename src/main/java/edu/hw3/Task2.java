package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    public static List<String> clusterize(String str) {
        List<String> clusters = new ArrayList<>();
        int lastAddedIndex = 0;
        int parenthesisBalance = 0;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '(' -> parenthesisBalance++;
                case ')' -> parenthesisBalance--;
                default -> throw new IllegalArgumentException("Input string must contain only '(' and ')' chars");
            }
            if (parenthesisBalance == 0) {
                clusters.add(str.substring(lastAddedIndex, i + 1));
                lastAddedIndex = i + 1;
            }
        }
        if (parenthesisBalance != 0) {
            throw new IllegalArgumentException("Input string must contain the same amount of '(' and ')' chars");
        }
        return clusters;
    }
}
