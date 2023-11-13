package edu.hw4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Arrays;
import java.util.List;

class Task2Test {

    private static final List<Animal> ANIMALS = Arrays.asList(
            new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                    4, 3, 2, false),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 10, true),
            new Animal("FishOne", Animal.Type.FISH, Animal.Sex.M,
                    5, 42, 3, true),
            new Animal("SpiderOne", Animal.Type.SPIDER, Animal.Sex.M,
                    42, 42, 42, true)
    );

    @Test
    void sortByWeightDescTest() {
        List<Animal> sortedAnimals = Task2.sortByWeightDesc(ANIMALS, 3);
        List<Integer> weights = Arrays.asList(42, 10, 3);
        assertEquals(sortedAnimals.size(), 3);
        for (int i = 0; i < sortedAnimals.size(); i++) {
            assertEquals(sortedAnimals.get(i).weight(), weights.get(i));
        }
    }

    @Test
    void takeNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> Task2.sortByWeightDesc(ANIMALS, -2));
    }
}
