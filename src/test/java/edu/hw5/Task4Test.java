package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {

    @ParameterizedTest
    @ValueSource(strings = {"@", "@121", "%|||", "ghdgjgsjg|", "cvcv^fbf"})
    void passwordIsCorrectTest(String password) {
        assertTrue(Task4.isPasswordCorrect(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "121", "asfsd"})
    void passwordIsNotCorrectTest(String password) {
        assertFalse(Task4.isPasswordCorrect(password));
    }
}
