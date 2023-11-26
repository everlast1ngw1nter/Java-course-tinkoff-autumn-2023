package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task16Test {

    private static final List<Animal> ANIMALS = Arrays.asList(
            new Animal("DogA", Animal.Type.DOG, Animal.Sex.M,
                    6, 5, 15, true),
            new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                    4, 423, 2, false),
            new Animal("DogB", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 66, true),
            new Animal("DogA", Animal.Type.DOG, Animal.Sex.F,
                    2, 5, 10, true),
            new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.F,
                    8, 42, 42, true)
    );

    @Test
    void sortByTypeSexNameTest() {
        List<Animal> animals = Task16.sortByTypeSexName(ANIMALS);
        assertThat(animals.size())
                .isEqualTo(5);
        assertThat(animals.get(0))
                .isEqualTo(new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                        4, 423, 2, false));
        assertThat(animals.get(1))
                .isEqualTo(new Animal("DogA", Animal.Type.DOG, Animal.Sex.M,
                        6, 5, 15, true));
        assertThat(animals.get(2))
                .isEqualTo(new Animal("DogB", Animal.Type.DOG, Animal.Sex.M,
                        2, 5, 66, true));
        assertThat(animals.get(3))
                .isEqualTo(new Animal("DogA", Animal.Type.DOG, Animal.Sex.F,
                        2, 5, 10, true));
        assertThat(animals.get(4))
                .isEqualTo(new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.F,
                        8, 42, 42, true));
    }
}
