package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {
    @Test
    void countKTestCorrect() {
        assertThat(Task6.countK(6174))
            .isEqualTo(0);
        assertThat(Task6.countK(8352))
            .isEqualTo(1);
        assertThat(Task6.countK(3087))
            .isEqualTo(2);
        assertThat(Task6.countK(3524))
            .isEqualTo(3);
    }

    @Test
    void isNotFourDigit() {
        assertThrows(IllegalArgumentException.class, () -> Task6.countK(617));
    }

    @Test
    void isNegative() {
        assertThrows(IllegalArgumentException.class, () -> Task6.countK(-1617));
    }
}
