package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static List<Animal> sortByWeightDesc(List<Animal> animals) {
        return animals
                .stream()
                .sorted(Comparator.comparing(Animal::weight).reversed())
                .toList();
    }
}
