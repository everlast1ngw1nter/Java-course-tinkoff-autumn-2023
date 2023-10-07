package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    @Test
    void tooManySeconds() {
        assertThat(Task1.minutesToSeconds("1:60"))
            .isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("999:999"))
            .isEqualTo(-1);
    }

    @Test
    void negativeSeconds() {
        assertThat(Task1.minutesToSeconds("12:-1"))
            .isEqualTo(-1);
    }

    @Test
    void negativeMinutes() {
        assertThat(Task1.minutesToSeconds("-1:12"))
            .isEqualTo(-1);
    }

    @Test
    void correctTime() {
        assertThat(Task1.minutesToSeconds("0:0"))
            .isEqualTo(0);
        assertThat(Task1.minutesToSeconds("59:59"))
            .isEqualTo(3599);
        assertThat(Task1.minutesToSeconds("100000:1"))
            .isEqualTo(6000001);
    }
}
