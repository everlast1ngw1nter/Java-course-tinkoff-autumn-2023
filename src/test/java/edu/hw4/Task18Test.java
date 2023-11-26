package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task18Test {

    @Test
    void noFishTest() {
        List<Animal> animals1 = Arrays.asList(
                new Animal("DogC", Animal.Type.DOG, Animal.Sex.M,
                        6, 5, 15, true),
                new Animal("CatA", Animal.Type.CAT, Animal.Sex.F,
                        4, 423, 2, false));
        List<Animal> animals2 = Arrays.asList(
                new Animal("DogA", Animal.Type.BIRD, Animal.Sex.M,
                        6, 5, 15, true),
                new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                        4, 423, 2, false));
        List<Animal> animals3 = Arrays.asList(
                new Animal("DogB", Animal.Type.SPIDER, Animal.Sex.M,
                        6, 5, 12, true),
                new Animal("CatTwo", Animal.Type.CAT, Animal.Sex.F,
                        4, 423, 2, false));
        Animal heaviestFish = Task18.getHeaviestFish(animals1, animals2, animals3);
        assertThat(heaviestFish)
                .isNull();
    }


    @Test
    void heaviestFishTest() {
        List<Animal> animals1 = Arrays.asList(
                new Animal("DogC", Animal.Type.DOG, Animal.Sex.M,
                        6, 5, 15, true),
                new Animal("CatA", Animal.Type.CAT, Animal.Sex.F,
                        4, 423, 2, false));
        List<Animal> animals2 = Arrays.asList(
                new Animal("DogA", Animal.Type.FISH, Animal.Sex.M,
                        6, 5, 15, true),
                new Animal("CatOne", Animal.Type.CAT, Animal.Sex.F,
                        4, 423, 2, false));
        List<Animal> animals3 = Arrays.asList(
                new Animal("DogB", Animal.Type.FISH, Animal.Sex.M,
                        6, 5, 12, true),
                new Animal("CatTwo", Animal.Type.CAT, Animal.Sex.F,
                        4, 423, 2, false));
        Animal heaviestFish = Task18.getHeaviestFish(animals1, animals2, animals3);
        assertThat(heaviestFish)
                .isEqualTo(new Animal("DogA", Animal.Type.FISH, Animal.Sex.M,
                        6, 5, 15, true));
    }
}
