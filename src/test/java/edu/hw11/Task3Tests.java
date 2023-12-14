package edu.hw11;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Tests {

    @Test
    void generateNewClass()
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        var res = Task3.generateNewClass();
        assertEquals(res, 2);
    }
}
