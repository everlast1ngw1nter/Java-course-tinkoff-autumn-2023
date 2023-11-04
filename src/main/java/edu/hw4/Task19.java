package edu.hw4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task19 {

    private Task19() {
    }

    public static Map<String, Set<ValidationError>> findRecordsError(List<Animal> animals) {
        Map<String, Set<ValidationError>> animalRecordErrors = new HashMap<>();
        animals
                .forEach(x -> findErrors(x, animalRecordErrors));
        return animalRecordErrors;
    }


    public static void findErrors(Animal animal, Map<String, Set<ValidationError>> animalRecordErrors) {
        var setErrors = new HashSet<ValidationError>();
        if (!animal.name().matches("^[a-zA-Z\\s]+$")) {
            setErrors.add(new ValidationError("name", ValidationError.Error.INCORRECT_NAME));
        }
        if (animal.age() < 0) {
            setErrors.add(new ValidationError("age", ValidationError.Error.NEGATIVE_VALUE));
        }
        if (animal.weight() < 0) {
            setErrors.add(new ValidationError("weight", ValidationError.Error.NEGATIVE_VALUE));
        }
        if (animal.height() < 0) {
            setErrors.add(new ValidationError("height", ValidationError.Error.NEGATIVE_VALUE));
        }
        if (!setErrors.isEmpty()) {
            animalRecordErrors.put(animal.name(), setErrors);
        }
    }



    public record ValidationError(String fieldName, Error error) {

        enum Error {
                INCORRECT_NAME,
                NEGATIVE_VALUE
            }
        }
}
