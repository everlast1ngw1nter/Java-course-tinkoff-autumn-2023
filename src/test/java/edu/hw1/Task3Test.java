package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task3Test {
    @Test
    void isNestable() {
        assertThat(Task3.isNestable(new long[] {1, 2, 3, 4}, new long[] {0, 6}))
            .isTrue();
        assertThat(Task3.isNestable(new long[] {3, 1}, new long[] {4, 0}))
            .isTrue();
    }

    @Test
    void isNotNestable() {
        assertThat(Task3.isNestable(new long[] {9, 9, 8}, new long[] {8, 9}))
            .isFalse();
        assertThat(Task3.isNestable(new long[] {1, 2, 3, 4}, new long[] {2, 3}))
            .isFalse();
    }

    @Test
    void emptyArray() {
        long[] a1 = new long[] {9, 9, 8};
        long[] a2 = new long[] {};
        assertThrows(IllegalArgumentException.class, () -> Task3.isNestable(a1, a2));
    }
}
