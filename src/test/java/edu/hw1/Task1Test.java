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
        assertThat(Task1.minutesToSeconds("42:4242"))
                .isEqualTo(-1);
    }

    @Test
    void incorrectInput() {
        assertThat(Task1.minutesToSeconds("sfasf"))
                .isEqualTo(-1);
        assertThat(Task1.minutesToSeconds(""))
                .isEqualTo(-1);
    }


    @Test
    void noMinutes() {
        assertThat(Task1.minutesToSeconds(":24"))
                .isEqualTo(-1);
    }

    @Test
    void noSeconds() {
        assertThat(Task1.minutesToSeconds("24:"))
                .isEqualTo(-1);
    }

    @Test
    void onlyOneDigitSecondsInput() {
        assertThat(Task1.minutesToSeconds("1:1"))
                .isEqualTo(-1);
    }

    @Test
    void negativeSeconds() {
        assertThat(Task1.minutesToSeconds("12:-11"))
            .isEqualTo(-1);
    }

    @Test
    void negativeMinutes() {
        assertThat(Task1.minutesToSeconds("-1:12"))
            .isEqualTo(-1);
    }

    @Test
    void correctTime() {
        assertThat(Task1.minutesToSeconds("0:00"))
            .isEqualTo(0);
        assertThat(Task1.minutesToSeconds("59:59"))
            .isEqualTo(3599);
        assertThat(Task1.minutesToSeconds("100000:01"))
            .isEqualTo(6000001);
    }
}
