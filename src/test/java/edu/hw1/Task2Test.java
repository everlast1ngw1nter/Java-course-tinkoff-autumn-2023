package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {
    @Test
    void zero() {
        assertThat(Task2.countDigits((0)))
            .isEqualTo(1);
    }

    @Test
    void positiveNumbers() {
        assertThat(Task2.countDigits((123)))
            .isEqualTo(3);
        assertThat(Task2.countDigits((412512)))
            .isEqualTo(6);
        assertThat(Task2.countDigits((9)))
            .isEqualTo(1);
        assertThat(Task2.countDigits((10)))
            .isEqualTo(2);
    }

    @Test
    void negativeNumbers() {
        assertThat(Task2.countDigits((-123)))
            .isEqualTo(3);
        assertThat(Task2.countDigits((-5)))
            .isEqualTo(1);
        assertThat(Task2.countDigits((-99999)))
            .isEqualTo(5);
        assertThat(Task2.countDigits((-10)))
            .isEqualTo(2);
    }
}
