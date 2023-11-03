package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task11Test {

    private static final List<Animal> ANIMALS = Arrays.asList(
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    6, 5, 15, true),
            new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                    4, 423, 2, false),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 66, true),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.F,
                    2, 5, 10, true),
            new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.F,
                    8, 42, 42, true),
            new Animal("SpiderOne", Animal.Type.SPIDER, Animal.Sex.F,
                    42, 142, 42, true)
    );

    @Test
    void canBiteHeightMoreHundredTest() {
        List<Animal> animals = Task11.canBiteHeightMoreHundred(ANIMALS);
        assertThat(animals.size())
                .isEqualTo(1);
        assertThat(animals.get(0))
                .isEqualTo(new Animal("SpiderOne", Animal.Type.SPIDER, Animal.Sex.F,
                        42, 142, 42, true));
    }
}
