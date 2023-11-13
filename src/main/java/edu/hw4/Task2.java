package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static List<Animal> sortByWeightDesc(List<Animal> animals, int take) {
        if (take < 0) {
            throw new IllegalArgumentException("Argument take must be not negative");
        }
        return animals
                .stream()
                .sorted(Comparator.comparing(Animal::weight).reversed())
                .limit(take)
                .toList();
    }
}
