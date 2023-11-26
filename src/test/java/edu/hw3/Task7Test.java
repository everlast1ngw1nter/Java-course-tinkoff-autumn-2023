package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @Test
    void nullKeyCreateTest() {
        var tree = new TreeMap<>(new Task7.TreeMapNullComparator());
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    void manyKeysTest() {
        var tree = new TreeMap<>(new Task7.TreeMapNullComparator());
        tree.put("abc", 1);
        tree.put("abb", 42);
        tree.put("b", 13);
        tree.put(null, 1);
        var orderedKeysIter = tree.navigableKeySet().iterator();
        assertThat(orderedKeysIter.next())
                .isEqualTo(null);
        assertThat(orderedKeysIter.next())
                .isEqualTo("abb");
        assertThat(orderedKeysIter.next())
                .isEqualTo("abc");
        assertThat(orderedKeysIter.next())
                .isEqualTo("b");
    }
}
