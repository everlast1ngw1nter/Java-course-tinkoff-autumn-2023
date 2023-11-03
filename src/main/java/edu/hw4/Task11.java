package edu.hw4;

import java.util.List;

public class Task11 {
    private Task11() {
    }

    public static List<Animal> canBiteHeightMoreHundred(List<Animal> animals) {
        return animals
                .stream()
                .filter(Animal::bites)
                .filter(x -> x.height() > 100)
                .toList();
    }
}
