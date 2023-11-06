package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task3 {

    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(T[] words) {
        var dict = new HashMap<T, Integer>();
        for (T word : words) {
            dict.compute(word, (key, value) -> value == null ? 1 : value + 1);
        }
        return dict;
    }
}
