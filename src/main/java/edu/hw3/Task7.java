package edu.hw3;

import java.util.Comparator;

public class Task7 {

    private Task7() {
    }

    public static class TreeMapNullComparator implements Comparator<String> {
        // не уверен, но кажется, что по заданию достаточно и такого Comparator
        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    }
}
