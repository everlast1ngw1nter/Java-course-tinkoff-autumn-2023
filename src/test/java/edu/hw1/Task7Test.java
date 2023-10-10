package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    void rotateRightTests() {
        assertThat(Task7.rotateRight(8, 1))
            .isEqualTo(4);
        assertThat(Task7.rotateRight(0, 4))
            .isEqualTo(0);
        assertThat(Task7.rotateRight(8, 11))
            .isEqualTo(1);
    }

    @Test
    void rotateLeftTests() {
        assertThat(Task7.rotateLeft(16, 1))
            .isEqualTo(1);
        assertThat(Task7.rotateLeft(17, 2))
            .isEqualTo(6);
        assertThat(Task7.rotateLeft(17, 5))
            .isEqualTo(17);
    }
}
