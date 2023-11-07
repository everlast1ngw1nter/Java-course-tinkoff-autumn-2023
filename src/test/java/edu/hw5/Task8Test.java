package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    @ParameterizedTest
    @ValueSource(strings = {"1", "000", "00101", "101010101"})
    void oddLenTest(String string){
        assertTrue(Task8.ODD_LEN.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "00", "0010", "10101011"})
    void evenLenTest(String string){
        assertFalse(Task8.ODD_LEN.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "00", "100", "101010101", "011111"})
    void startZeroEvenLenOrOneOddLenTest(String string){
        assertTrue(Task8.START_ZERO_EVEN_LEN_OR_ONE_ODD_LEN.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "0", "11", "01111"})
    void startZeroOddLenOrOneEvenLenTest(String string){
        assertFalse(Task8.START_ZERO_EVEN_LEN_OR_ONE_ODD_LEN.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1111", "0001", "100010000001"})
    void numberZerosIsMultipleThreeTest(String string){
        assertTrue(Task8.NUMBER_ZEROS_IS_MULTIPLE_THREE.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "00", "01011", "10001001"})
    void numberZerosIsNotMultipleThreeTest(String string){
        assertFalse(Task8.NUMBER_ZEROS_IS_MULTIPLE_THREE.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1111", "00011", "1", "000", "110"})
    void not11Or111Test(String string){
        assertTrue(Task8.NOT_11_OR_111.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111"})
    void stringEquals11Or111Test(String string){
        assertFalse(Task8.NOT_11_OR_111.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "111", "01010", "1", "01", "0"})
    void everyOddNumberIsOneTest(String string){
        assertTrue(Task8.EVERY_ODD_NUMBER_IS_ONE.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "10", "111110", "1110"})
    void notEveryOddNumberIsOneTest(String string){
        assertFalse(Task8.EVERY_ODD_NUMBER_IS_ONE.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "100", "00000", "001", "0001000"})
    void moreOneZeroLessTwoOnesTest(String string){
        assertTrue(Task8.MORE_ONE_ZERO_LESS_TWO_ONES.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "11000", "", "1", "10001"})
    void lessTwoZeroMoreOneOnesTest(String string){
        assertFalse(Task8.MORE_ONE_ZERO_LESS_TWO_ONES.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "100", "10101", "001", "0101010", ""})
    void noConsecutiveOnesTest(String string){
        assertTrue(Task8.NO_CONSECUTIVE_ONES.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "011", "10111", "010101011"})
    void consecutiveOnesTest(String string){
        assertFalse(Task8.NO_CONSECUTIVE_ONES.matcher(string).find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a11", "012", "101t", "11%", "1000L"})
    void unidentifiedSymbolsTest(String string){
        assertFalse(Task8.NO_CONSECUTIVE_ONES.matcher(string).find());
        assertFalse(Task8.NUMBER_ZEROS_IS_MULTIPLE_THREE.matcher(string).find());
        assertFalse(Task8.MORE_ONE_ZERO_LESS_TWO_ONES.matcher(string).find());
        assertFalse(Task8.ODD_LEN.matcher(string).find());
        assertFalse(Task8.NOT_11_OR_111.matcher(string).find());
        assertFalse(Task8.EVERY_ODD_NUMBER_IS_ONE.matcher(string).find());
        assertFalse(Task8.START_ZERO_EVEN_LEN_OR_ONE_ODD_LEN.matcher(string).find());
    }
}
