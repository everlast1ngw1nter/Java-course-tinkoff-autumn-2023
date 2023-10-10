package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    void notEmptyString() {
        assertThat(Task4.fixString("123456"))
            .isEqualTo("214365");
        assertThat(Task4.fixString("1"))
            .isEqualTo("1");
        assertThat(Task4.fixString("badce"))
            .isEqualTo("abcde");
        assertThat(Task4.fixString("hTsii  s aimex dpus rtni.g"))
            .isEqualTo("This is a mixed up string.");
    }

    @Test
    void emptyString() {
        assertThat(Task4.fixString(""))
            .isEqualTo("");
    }
}
