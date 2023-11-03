package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {

    private static final List<Animal> ANIMALS = Arrays.asList(
            new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                    4, 3, 2, false),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 15, true),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 66, true),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.F,
                    2, 5, 10, true),
            new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.F,
                    42, 42, 42, true),
            new Animal("SpiderOne", Animal.Type.SPIDER, Animal.Sex.F,
                    42, 42, 42, true)
    );

    @Test
    void biggestWeightAnimalsTypesTest() {
        Map<Animal.Type, Animal> biggestWeightAnimals = Task6.biggestWeightAnimalsTypes(ANIMALS);
        assertThat(biggestWeightAnimals.get(Animal.Type.DOG))
                .isEqualTo(new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                        2, 5, 66, true));
        assertThat(biggestWeightAnimals.get(Animal.Type.CAT))
                .isEqualTo(new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                        4, 3, 2, false));
        assertThat(biggestWeightAnimals.get(Animal.Type.SPIDER).weight())
                .isEqualTo(42);
    }
}
