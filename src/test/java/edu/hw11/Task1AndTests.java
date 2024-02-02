package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1AndTests {

    @Test
    void helloByteBuddyTest()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var newClass = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello, ByteBuddy!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();
        assertEquals(newClass.getConstructor().newInstance().toString(), "Hello, ByteBuddy!");
    }
}
