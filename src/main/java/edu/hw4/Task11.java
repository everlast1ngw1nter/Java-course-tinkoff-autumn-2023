package edu.hw4;

import java.util.List;

public class Task11 {
    private Task11() {
    }

    private static final int NIN_HEIGHT = 100;

    public static List<Animal> canBiteHeightMoreHundred(List<Animal> animals) {
        return animals
                .stream()
                .filter(Animal::bites)
                .filter(x -> x.height() > NIN_HEIGHT)
                .toList();
    }
}
