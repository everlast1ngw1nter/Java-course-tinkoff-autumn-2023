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
                .forEach(x -> {
                    if (animalCounter.containsKey(x.type())) {
                        animalCounter.put(x.type(), animalCounter.get(x.type()) + 1);
                    } else {
                        animalCounter.put(x.type(), 1);
                    }
                });
        return animalCounter;
    }
}
