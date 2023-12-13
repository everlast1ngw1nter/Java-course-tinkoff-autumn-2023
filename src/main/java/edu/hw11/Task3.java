package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import java.lang.reflect.InvocationTargetException;

public class Task3 {

    public static Object generateNewClass()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var newClass = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("fib"))
                .intercept(FixedValue.value(0))
                .make()
                .load(Task3.class.getClassLoader())
                .getLoaded();
        return newClass.getConstructor().newInstance();
    }
}
