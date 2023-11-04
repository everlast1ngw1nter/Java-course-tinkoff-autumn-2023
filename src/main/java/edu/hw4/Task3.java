package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    private Task3() {
    }

    public static Map<Animal.Type, Integer> countAnimalByType(List<Animal> animals) {
        Map<Animal.Type, Integer> animalCounter = new HashMap<>();
        animals
                .forEach(x -> animalCounter.compute(x.type(),
                        (key, value) -> value == null ? 1 : value + 1));
        return animalCounter;
    }
}
