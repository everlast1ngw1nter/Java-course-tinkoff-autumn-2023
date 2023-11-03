package edu.hw4;

import java.util.List;

public class Task12 {
    private Task12() {
    }

    public static List<Animal> animalsWeightMoreThanHeight(List<Animal> animals) {
        return animals
                .stream()
                .filter(x -> x.weight() > x.height())
                .toList();
    }
}
