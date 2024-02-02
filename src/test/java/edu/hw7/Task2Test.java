package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    void getFactorial() {
        assertEquals(Task2.getFactorial(5), 120L);
    }
}
