package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {

    @ParameterizedTest
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177"})
    void licensePlateNumberTest(String licensePlateNumber) {
        assertTrue(Task5.isLicensePlateNumberCorrect(licensePlateNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123АВЕ777", "А123ВГ77", "А123ВЕ7777", "a123ВЕ777"})
    void incorrectLicensePlateNumberTest(String licensePlateNumber) {
        assertFalse(Task5.isLicensePlateNumberCorrect(licensePlateNumber));
    }
}
