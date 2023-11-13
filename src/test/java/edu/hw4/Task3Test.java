package edu.hw4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Task3Test {

    private static final List<Animal> ANIMALS = Arrays.asList(
            new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                    4, 3, 2, false),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 10, true),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 10, true),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 10, true),
            new Animal("SpiderOne", Animal.Type.SPIDER, Animal.Sex.M,
                    42, 42, 42, true),
            new Animal("SpiderOne", Animal.Type.SPIDER, Animal.Sex.M,
                    42, 42, 42, true)
    );

    @Test
    void countAnimalByTypeTest() {
        Map<Animal.Type, Long> countedAnimals = Task3.countAnimalByType(ANIMALS);
        assertEquals(countedAnimals.get(Animal.Type.CAT), 1);
        assertEquals(countedAnimals.get(Animal.Type.DOG), 3);
        assertEquals(countedAnimals.get(Animal.Type.SPIDER), 2);
        assertNull(countedAnimals.get(Animal.Type.FISH));
        assertNull(countedAnimals.get(Animal.Type.BIRD));
    }
}
