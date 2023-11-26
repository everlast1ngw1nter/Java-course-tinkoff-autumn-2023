package edu.hw4;

import java.util.List;

public class Task13 {
    private Task13() {
    }

    public static List<Animal> animalsNamesMoreTwoWords(List<Animal> animals) {
        return animals
                .stream()
                .filter(x -> x.name().strip().split("\\s+").length > 2)
                .toList();
    }
}
