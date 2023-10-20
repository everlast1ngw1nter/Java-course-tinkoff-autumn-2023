package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    @Test
    void freqDictStringTest1() {
        var words = new String[] {"a", "bb", "a", "bb"};
        var dict = new HashMap<String, Integer>();
        dict.put("bb", 2);
        dict.put("a", 2);
        assertThat(Task3.freqDict(words))
                .isEqualTo(dict);
    }

    @Test
    void freqDictStringTest2() {
        var words = new String[] {"this", "and", "that", "and"};
        var dict = new HashMap<String, Integer>();
        dict.put("that", 1);
        dict.put("and", 2);
        dict.put("this", 1);
        assertThat(Task3.freqDict(words))
                .isEqualTo(dict);
    }

    @Test
    void freqDictStringTest3() {
        var words = new String[] {null, null, "null"};
        var dict = new HashMap<String, Integer>();
        dict.put("null", 1);
        dict.put(null, 2);
        assertThat(Task3.freqDict(words))
                .isEqualTo(dict);
    }

    @Test
    void freqDictStringTest4() {
        var words = new String[] {"код", "код", "код", "bug"};
        var dict = new HashMap<String, Integer>();
        dict.put("код", 3);
        dict.put("bug", 1);
        assertThat(Task3.freqDict(words))
                .isEqualTo(dict);
    }

    @Test
    void freqDictIntTest1() {
        var words = new Integer[] {1, 1, 2, 2};
        var dict = new HashMap<Integer, Integer>();
        dict.put(1, 2);
        dict.put(2, 2);
        assertThat(Task3.freqDict(words))
                .isEqualTo(dict);
    }

    @Test
    void freqDictIntTest2() {
        var words = new Integer[] {};
        var dict = new HashMap<Integer, Integer>();
        assertThat(Task3.freqDict(words))
                .isEqualTo(dict);
    }
}