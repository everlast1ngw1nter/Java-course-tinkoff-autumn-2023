package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task4 {

    private Task4() {
    }

    public static Animal getAnimalLargestName(List<Animal> animals) {
        return animals
                .stream()
                .max(Comparator.comparing((Animal x) -> x.name().length()))
                .orElse(null);
    }
}
