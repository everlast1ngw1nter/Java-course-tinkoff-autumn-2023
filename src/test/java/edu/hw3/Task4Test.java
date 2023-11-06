package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {

    @ParameterizedTest
    @CsvSource({
            ("2, II"),
            ("900, CM"),
            ("16, XVI"),
            ("12, XII"),
            ("1976, MCMLXXVI"),})
    void convertToRomanTest(int input, String expected) {
        assertThat(Task4.convertToRoman(input))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {-13, 0, 4000, 525125})
    void incorrectArabicNumberTest(int input) {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(input));
    }
}
