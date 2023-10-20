package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @Test
    void convertToRomanCorrectTest1() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7.TreeMapNullComparator());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
