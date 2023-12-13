package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2AndTests {

    @Test
    void redefineMethodTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        new ByteBuddy()
                .redefine(ArithmeticUtils.class)
                .method(named("sum"))
                .intercept(FixedValue.value(0))
                .make()
                .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        assertEquals(ArithmeticUtils.sum(3,8), 0);

    }

    static class ArithmeticByteBuddy {
        public static int sum(int a, int b) {
            return a * b;
        }
    }

    static class ArithmeticUtils {
        public static int sum(int a, int b) {
            return a + b;
        }
    }
}
