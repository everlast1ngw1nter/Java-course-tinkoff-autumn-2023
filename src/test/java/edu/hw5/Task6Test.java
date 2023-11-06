package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @ParameterizedTest
    @CsvSource({
            "achfdbaabgabcaabg, abc",
            "1, 1",
            "0111, 111"})
    void isSubstringTest(String string, String substring) {
        assertTrue(Task6.isSubstring(string, substring));
    }

    @ParameterizedTest
    @CsvSource({
            "achfdbaabgacaabg, abc",
            "1, 0",
            "0111, 1011"})
    void isNotSubstringTest(String string, String substring) {
        assertFalse(Task6.isSubstring(string, substring));
    }
}
