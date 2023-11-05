package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {

    @ParameterizedTest
    @ValueSource(strings = {"110", "000000", "000111"})
    void stringIsCorrect1Test(String string) {
        assertTrue(string.matches(Task7.THREE_PLUS_SYMBOLS_THIRD_IS_ZERO.pattern()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "0", "00", "11111", "1110", "aa0a", "1102"})
    void stringIsNotCorrect1Test(String string) {
        assertFalse(string.matches(Task7.THREE_PLUS_SYMBOLS_THIRD_IS_ZERO.pattern()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"101", "000000", "100111", "1", "0", "11"})
    void stringIsCorrect2Test(String string) {
        assertTrue(string.matches(Task7.START_AND_END_EQUAL.pattern()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "01", "011", "11110", "101010", "aa", ""})
    void stringIsNotCorrect2Test(String string) {
        assertFalse(string.matches(Task7.START_AND_END_EQUAL.pattern()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"101", "00", "110", "1", "0", "11"})
    void stringIsCorrect3Test(String string) {
        assertTrue(string.matches(Task7.LEN_BETWEEN_ONE_AND_THREE.pattern()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1110", "", "031", "a", "101010"})
    void stringIsNotCorrect3Test(String string) {
        assertFalse(string.matches(Task7.START_AND_END_EQUAL.pattern()));
    }
}
