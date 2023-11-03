package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task6 {
    private Task6() {
    }

    public static Map<Animal.Type, Animal> biggestWeightAnimalsTypes(List<Animal> animals) {
        Map<Animal.Type, Animal> animalCounter = new HashMap<>();
        animals
                .forEach(x -> {
                    if (animalCounter.containsKey(x.type())) {
                        if (animalCounter.get(x.type()).weight() < x.weight()) {
                            animalCounter.put(x.type(), x);
                        }

                    } else {
                        animalCounter.put(x.type(), x);
                    }
                });
        return animalCounter;
    }
}
