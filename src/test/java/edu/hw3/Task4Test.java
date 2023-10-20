package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {
    @Test
    void convertToRomanCorrectTest1() {
        assertThat(Task4.convertToRoman(2))
                .isEqualTo("II");
    }

    @Test
    void convertToRomanCorrectTest2() {
        assertThat(Task4.convertToRoman(900))
                .isEqualTo("CM");
    }

    @Test
    void convertToRomanCorrectTest3() {
        assertThat(Task4.convertToRoman(16))
                .isEqualTo("XVI");
    }

    @Test
    void convertToRomanCorrectTest4() {
        assertThat(Task4.convertToRoman(12))
                .isEqualTo("XII");
    }

    @Test
    void convertToRomanCorrectTest5() {
        assertThat(Task4.convertToRoman(1976))
                .isEqualTo("MCMLXXVI");
    }

    @Test
    void convertToRomanIncorrectTest1() {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(-13));
    }

    @Test
    void convertToRomanIncorrectTest2() {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(0));
    }

    @Test
    void convertToRomanIncorrectTest3() {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(4000));
    }

    @Test
    void convertToRomanIncorrectTest4() {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(525125));
    }
}
