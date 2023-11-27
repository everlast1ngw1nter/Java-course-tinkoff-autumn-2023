package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {

    @ParameterizedTest
    @ValueSource(strings = {"110", "000000", "000111"})
    void threePlusSymbolsThirdIsZeroTest(String string) {
        assertTrue(Task7.THREE_PLUS_SYMBOLS_THIRD_IS_ZERO.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "0", "00"})
    void incorrectLenTest(String string) {
        assertFalse(Task7.THREE_PLUS_SYMBOLS_THIRD_IS_ZERO.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"11111", "1110"})
    void thirdIsNotZeroTest(String string) {
        assertFalse(Task7.THREE_PLUS_SYMBOLS_THIRD_IS_ZERO.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"101", "000000", "100111", "1", "0", "11",  ""})
    void startAndEndEqualTest(String string) {
        assertTrue(Task7.START_AND_END_EQUAL.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "01", "011", "11110", "101010"})
    void startAndEndNotEqualTest(String string) {
        assertFalse(Task7.START_AND_END_EQUAL.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"101", "00", "110", "1", "0", "11"})
    void lenBetweenOneAndThreeTest(String string) {
        assertTrue(Task7.LEN_BETWEEN_ONE_AND_THREE.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1110", "", "101010"})
    void lenNotBetweenOneAndThreeTest(String string) {
        assertFalse(Task7.LEN_BETWEEN_ONE_AND_THREE.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa0a", "1102", "aa", "031", "a",})
    void unidentifiedCharactersTest(String string) {
        assertFalse(Task7.THREE_PLUS_SYMBOLS_THIRD_IS_ZERO.matcher(string).find());
        assertFalse(Task7.START_AND_END_EQUAL.matcher(string).find());
        assertFalse(Task7.LEN_BETWEEN_ONE_AND_THREE.matcher(string).find());
    }
}
