package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task15 {
    private Task15() {
    }

    public static Map<Animal.Type, Integer> totalWeightAnimalBetweenYears(List<Animal> animals,
                                                                          int minYears,
                                                                          int maxYears) {
        return animals
                .stream()
                .filter(x -> x.age() >= minYears && x.age() <= maxYears)
                .collect(Collectors.groupingBy(Animal::type,
                        Collectors.summingInt(Animal::weight)));
    }
}
