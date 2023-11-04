package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task15 {
    private Task15() {
    }

    public static Map<Animal.Type, Integer> totalWeightAnimalBetweenYears(List<Animal> animals,
                                                                          int minYears,
                                                                          int maxYears) {
        Map<Animal.Type, Integer> animalCounter = new HashMap<>();
        animals
                .stream()
                .filter(x -> x.age() >= minYears && x.age() <= maxYears)
                .forEach(x -> {
                    if (animalCounter.containsKey(x.type())) {
                        animalCounter.put(x.type(), animalCounter.get(x.type()) + x.weight());
                    } else {
                        animalCounter.put(x.type(), x.weight());
                    }
                });
        return animalCounter;
    }
}
