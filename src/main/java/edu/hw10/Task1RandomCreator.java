package edu.hw10;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public class Task1RandomCreator {

    private static final Random rnd = new Random();

    private static final Map<String, Callable<?>> MAPPER = Map.of(
            "int", rnd::nextInt,
            "long", rnd::nextLong,
            "boolean", rnd::nextBoolean,
            "java.lang.String", () -> "aboba"
    );

    public static Object getRandomParameter(String typeName) throws Exception {
        var callable = MAPPER.get(typeName);
        return callable.call();
    }
}
