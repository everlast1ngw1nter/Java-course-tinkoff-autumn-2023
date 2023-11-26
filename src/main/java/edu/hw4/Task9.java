package edu.hw4;

import java.util.List;

public class Task9 {
    private Task9() {
    }

    public static Integer countAllPaws(List<Animal> animals) {
        return animals
                .stream()
                .mapToInt(Animal::paws).sum();
    }
}
