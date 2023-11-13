package edu.hw4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task17Test {

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                        new Animal("DogA", Animal.Type.DOG, Animal.Sex.M,
                                6, 5, 15, true),
                        new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                                4, 423, 2, false))),
                Arguments.of(
                        Arrays.asList(
                        new Animal("SpiderA", Animal.Type.SPIDER, Animal.Sex.M,
                                6, 5, 15, true),
                        new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                                4, 423, 2, false))),
                Arguments.of(
                        Arrays.asList(
                        new Animal("BirdOne", Animal.Type.BIRD, Animal.Sex.M,
                                6, 5, 15, true),
                        new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                                4, 423, 2, false)))
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void notEnoughInfoTest(List<Animal> animals) {
        boolean spidersBytesMoreThanDogs = Task17.isSpidersBytesMoreThanDogs(animals);
        assertFalse(spidersBytesMoreThanDogs);
    }

    @Test
    void spidersBytesMoreTest() {
        List<Animal> animals = Arrays.asList(
                new Animal("DogA", Animal.Type.DOG, Animal.Sex.M,
                        6, 5, 15, true),
                new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                        4, 423, 2, false),
                new Animal("DogB", Animal.Type.DOG, Animal.Sex.M,
                        2, 5, 66, false),
                new Animal("DogA", Animal.Type.DOG, Animal.Sex.F,
                        2, 5, 10, false),
                new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.F,
                        8, 42, 41, true),
                new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.F,
                        8, 42, 42, false));
        boolean spidersBytesMoreThanDogs = Task17.isSpidersBytesMoreThanDogs(animals);
        assertTrue(spidersBytesMoreThanDogs);
    }

    @Test
    void dogsBytesMoreTest() {
        List<Animal> animals = Arrays.asList(
                new Animal("DogA", Animal.Type.DOG, Animal.Sex.M,
                        6, 5, 15, true),
                new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                        4, 423, 2, false),
                new Animal("DogB", Animal.Type.DOG, Animal.Sex.M,
                        2, 5, 66, true),
                new Animal("DogA", Animal.Type.DOG, Animal.Sex.F,
                        2, 5, 10, false),
                new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.F,
                        8, 42, 41, true),
                new Animal("SpiderBigName", Animal.Type.SPIDER, Animal.Sex.F,
                        8, 42, 42, false));
        boolean spidersBytesMoreThanDogs = Task17.isSpidersBytesMoreThanDogs(animals);
        assertFalse(spidersBytesMoreThanDogs);
    }
}

