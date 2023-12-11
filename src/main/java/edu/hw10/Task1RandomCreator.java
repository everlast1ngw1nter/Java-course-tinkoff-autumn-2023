package edu.hw10;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import edu.hw10.Task1Annotations.*;

public class Task1RandomCreator {

    private static final Random rnd = new Random();

    private static final Method[] METHODS = Task1RandomCreator.class.getDeclaredMethods();

    public static Object getRandomParameter(Parameter parameter) throws Exception {
        var typeName = parameter.getType().getName();
        var currAnn = Arrays.stream(METHODS)
                .filter((elem) -> elem.getAnnotation(RandomGenerator.class) != null
                        && elem.getAnnotation(RandomGenerator.class).typeName().equals(typeName))
                .findFirst()
                .get();
        return currAnn.invoke(Task1RandomCreator.class, parameter);
    }

    @RandomGenerator(typeName = "int")
    private static int generateInt(Parameter parameter) {
        var annotations = parameter.getAnnotations();
        return rnd.nextInt();
    }

    @RandomGenerator(typeName = "boolean")
    private static boolean generateBoolean(Parameter parameter) {
        var annotations = parameter.getAnnotations();
        return rnd.nextBoolean();
    }

    @RandomGenerator(typeName = "java.lang.String")
    private static String generateString(Parameter parameter) {
        var annotations = parameter.getAnnotations();
        return "aboba";
    }
}
