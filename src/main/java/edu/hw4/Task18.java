package edu.hw4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Task18 {
    private Task18() {
    }

    @SafeVarargs
    public static Animal getHeaviestFish(List<Animal>... animals) {
        return Arrays.stream(animals)
                .flatMap(List::stream)
                .filter(x -> x.type().equals(Animal.Type.FISH))
                .max(Comparator.comparing(Animal::weight))
                .orElse(null);
    }
}
