package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Task8 {
    private Task8() {
    }

    public static Optional<Animal> findHeaviestAnimalBelowWeight(List<Animal> animals, int boundHeight) {
        return animals
                .stream()
                .filter(x -> x.height() < boundHeight)
                .max(Comparator.comparing(Animal::weight));
    }
}
