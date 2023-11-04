package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task20Test {

    @Test
    void findRecordsErrorTest() {
        List<Animal> animals = Arrays.asList(
                new Animal("DogC12", Animal.Type.DOG, Animal.Sex.M,
                        6, 5, 15, true),
                new Animal("CatA", Animal.Type.CAT, Animal.Sex.F,
                        -54, 423, -2, false),
                new Animal("CatB", Animal.Type.CAT, Animal.Sex.F,
                        54, 423, 2, false));

        Map<String, String> recordsErrors = Task20.findRecordsErrorString(animals);
        assertThat(recordsErrors)
                .isNotNull();
        assertTrue(recordsErrors.containsKey("DogC12"));
        assertThat(recordsErrors.get("DogC12"))
                .isEqualTo(("[ValidationError[fieldName=name, error=INCORRECT_NAME]]"));
        assertTrue(recordsErrors.containsKey("CatA"));
        assertThat(recordsErrors.get("CatA"))
                .isIn("[ValidationError[fieldName=weight, error=NEGATIVE_VALUE], ValidationError[fieldName=age, error=NEGATIVE_VALUE]]",
                        "[ValidationError[fieldName=age, error=NEGATIVE_VALUE], ValidationError[fieldName=weight, error=NEGATIVE_VALUE]]");
        assertFalse(recordsErrors.containsKey("CatB"));
    }
}
