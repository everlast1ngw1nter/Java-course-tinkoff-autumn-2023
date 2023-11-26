package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task19Test {

    @Test
    void findRecordsErrorTest() {
        List<Animal> animals = Arrays.asList(
                new Animal("DogC12", Animal.Type.DOG, Animal.Sex.M,
                        6, 5, 15, true),
                new Animal("CatA", Animal.Type.CAT, Animal.Sex.F,
                        -54, 423, -2, false),
                new Animal("CatB", Animal.Type.CAT, Animal.Sex.F,
                        54, 423, 2, false));

        Map<String, Set<Task19.ValidationError>> recordsErrors = Task19.findRecordsError(animals);
        assertThat(recordsErrors)
                .isNotNull();
        assertTrue(recordsErrors.containsKey("DogC12"));
        assertTrue(recordsErrors.get("DogC12").contains(new Task19.ValidationError("name",
                Task19.ValidationError.Error.INCORRECT_NAME)));
        assertTrue(recordsErrors.containsKey("CatA"));
        assertTrue(recordsErrors.get("CatA").contains(new Task19.ValidationError("weight",
                Task19.ValidationError.Error.NEGATIVE_VALUE)));
        assertTrue(recordsErrors.get("CatA").contains(new Task19.ValidationError("age",
                Task19.ValidationError.Error.NEGATIVE_VALUE)));
        assertFalse(recordsErrors.containsKey("CatB"));
    }
}
