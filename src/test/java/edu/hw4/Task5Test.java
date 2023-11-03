package edu.hw4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

class Task5Test {

    private static final List<Animal> ANIMALS = Arrays.asList(
            new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                    4, 3, 2, false),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 10, true),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 10, true),
            new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.F,
                    42, 42, 42, true),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.F,
                    2, 5, 10, true),
            new Animal("SpiderOne", Animal.Type.SPIDER, Animal.Sex.F,
                    42, 42, 42, true)
    );

    @Test
    void animalSexCounterTest() {
        Animal.Sex moreFreqSex = Task5.animalSexCounter(ANIMALS);
        assertEquals(moreFreqSex, Animal.Sex.F);
    }
}
