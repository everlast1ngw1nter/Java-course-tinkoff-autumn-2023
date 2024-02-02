package edu.hw11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Tests {

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "6, 8",
            "25, 75025",
    })
    void generateNewClass(int input, long expected)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        var method = Task3.generateFibClass().getMethod("fib", int.class);
        var fibRes = method.invoke(null, input);
        assertEquals(expected, fibRes);
    }
}
