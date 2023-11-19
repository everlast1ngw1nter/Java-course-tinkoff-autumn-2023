package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task7 {
    private Task7() {
    }

    public static Animal oldestAnimal(List<Animal> animals, int place) {
        if (place < 0) {
            throw new IllegalArgumentException("Argument place must be not negative");
        }
        return animals
                .stream()
                .sorted(Comparator.comparing(Animal::age).reversed())
                .skip(place - 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not enough array elements"));
    }
}
