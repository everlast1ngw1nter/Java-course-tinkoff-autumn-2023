package edu.hw4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

class Task1Test {

    private static final List<Animal> ANIMALS = Arrays.asList(
            new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                    4, 3, 2, false),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 9, true),
            new Animal("FishOne", Animal.Type.FISH, Animal.Sex.M,
                        5, 42, 42, true),
            new Animal("SpiderOne", Animal.Type.SPIDER, Animal.Sex.M,
                    42, 42, 42, true)
    );

    @Test
    void sortByAgeTest() {
        List<Animal> sortedAnimals = Task1.sortByAge(ANIMALS);
        List<Integer> ages = Arrays.asList(2, 4, 5, 42);
        assertEquals(sortedAnimals.size(), ages.size());
        for (int i = 0; i < sortedAnimals.size(); i++) {
            assertEquals(sortedAnimals.get(i).age(), ages.get(i));
        }
    }
}

