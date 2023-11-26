package edu.hw4;

import java.util.List;

public class Task14 {
    private Task14() {
    }

    public static boolean containsDogMoreThatHeight(List<Animal> animals, int height) {
        return animals
                .stream()
                .filter(x -> x.type().equals(Animal.Type.DOG))
                .anyMatch(x -> x.height() > height);
    }
}
