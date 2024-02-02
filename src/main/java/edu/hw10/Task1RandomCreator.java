package edu.hw10;

import edu.hw10.Task1Annotations.Max;
import edu.hw10.Task1Annotations.Min;
import edu.hw10.Task1Annotations.NotNull;
import edu.hw10.Task1Annotations.RandomGenerator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Random;

public class Task1RandomCreator {

    private Task1RandomCreator() {

    }

    private static final Random RND = new Random();

    private static final Method[] METHODS = Task1RandomCreator.class.getDeclaredMethods();

    public static Object getRandomValue(Parameter parameter) throws Exception {
        var typeName = parameter.getType().getName();
        var optCurrAnn = Arrays.stream(METHODS)
                .filter((elem) -> elem.getAnnotation(RandomGenerator.class) != null
                        && elem.getAnnotation(RandomGenerator.class).typeName().equals(typeName))
                .findFirst();
        if (optCurrAnn.isPresent()) {
            return optCurrAnn.get().invoke(Task1RandomCreator.class, parameter);
        }
        throw new IllegalArgumentException("Generation of a variable of this type is not supported");
    }

    @RandomGenerator(typeName = "int")
    private static int generateInt(Parameter parameter) {
        var max = Integer.MAX_VALUE;
        var maxAnnotation = parameter.getAnnotation(Max.class);
        if (maxAnnotation != null) {
            max = maxAnnotation.value();
        }
        var min = Integer.MIN_VALUE;
        var minAnnotation = parameter.getAnnotation(Min.class);
        if (minAnnotation != null) {
            min = minAnnotation.value();
        }
        return RND.nextInt(min, max);
    }

    @RandomGenerator(typeName = "boolean")
    private static boolean generateBoolean(Parameter parameter) {
        return RND.nextBoolean();
    }

    @RandomGenerator(typeName = "java.lang.String")
    private static String generateString(Parameter parameter) {
        var notNullAnnotation = parameter.getAnnotation(NotNull.class);
        if (RND.nextBoolean() && notNullAnnotation == null) {
            return null;
        }
        return RandomStringGenerator.generate();
    }

    private static class RandomStringGenerator {
        private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        private static final int MAX_LENGTH = 6;

        private static final int MIN_LENGTH = 2;

        public static String generate() {
            var length = RND.nextInt(MIN_LENGTH, MAX_LENGTH);
            StringBuilder builder = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                builder.append(CHARACTERS.charAt(RND.nextInt(CHARACTERS.length())));
            }
            return builder.toString();
        }
    }
}
