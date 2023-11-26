package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task13Test {
    private static final List<Animal> ANIMALS = Arrays.asList(
            new Animal("Dog One Two", Animal.Type.DOG, Animal.Sex.M,
                    6, 5, 15, true),
            new Animal("Cat      One         13", Animal.Type.CAT, Animal.Sex.F,
                    4, 423, 2, false),
            new Animal("DogOne", Animal.Type.DOG, Animal.Sex.M,
                    2, 5, 66, true),
            new Animal("Dog One", Animal.Type.DOG, Animal.Sex.F,
                    2, 5, 10, true)
//            new Animal(null, Animal.Type.SPIDER, Animal.Sex.F,
//                    8, 42, 42, true)
    );

    @Test
    void animalsNamesMoreTwoWordsTest() {
        List<Animal> animals = Task13.animalsNamesMoreTwoWords(ANIMALS);
        assertThat(animals.size())
                .isEqualTo(2);
    }
}
