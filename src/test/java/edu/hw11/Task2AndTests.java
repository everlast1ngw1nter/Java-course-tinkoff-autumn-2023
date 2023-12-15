package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2AndTests {

    @Test
    void redefineMethodTest()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var dynamicType = new ByteBuddy()
                .subclass(ArithmeticUtils.class)
                .method(named("sum"))
                .intercept(MethodDelegation.to(new ArithmeticByteBuddy()))
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        assertEquals(dynamicType.getDeclaredConstructor().newInstance().sum(3,8), 24);

    }

    public static class ArithmeticByteBuddy {
        public int sum(int a, int b) {
            return a * b;
        }
    }

    public static class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }
}
