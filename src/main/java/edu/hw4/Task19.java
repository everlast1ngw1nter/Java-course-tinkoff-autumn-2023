package edu.hw4;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task19 {

    private Task19() {
    }

    public static Map<String, Set<ValidationError>> findRecordsError(List<Animal> animals) {
        return animals
                .stream()
                .collect(Collectors.toMap(Animal::name, Task19::findErrors))
                .entrySet()
                .stream()
                .filter(x -> !x.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public static Set<ValidationError> findErrors(Animal animal) {
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
        return setErrors;
    }


    public record ValidationError(String fieldName, Error error) {

        enum Error {
                INCORRECT_NAME,
                NEGATIVE_VALUE
            }
        }
}
