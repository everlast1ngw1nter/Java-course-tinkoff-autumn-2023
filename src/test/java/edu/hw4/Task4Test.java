package edu.hw4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

class Task4Test {

    private static final List<Animal> ANIMALS = Arrays.asList(
            new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                    4, 3, 2, false),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 10, true),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 10, true),
            new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.M,
                    42, 42, 42, true),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 10, true),
            new Animal("SpiderOne", Animal.Type.SPIDER, Animal.Sex.M,
                    42, 42, 42, true)
    );

    @Test
    void findAnimalLargestNameTest() {
        Animal animalLargestName = Task4.findAnimalLargestName(ANIMALS);
        assertEquals(animalLargestName, new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.M,
                42, 42, 42, true));
    }
}
