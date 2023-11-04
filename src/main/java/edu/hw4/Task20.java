package edu.hw4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task20 {

    private Task20() {
    }

    public static Map<String, String> findRecordsErrorString(List<Animal> animals) {
        Map<String, Set<Task19.ValidationError>> animalRecordErrors = Task19.findRecordsError(animals);
        final Map<String, String> animalRecordErrorsString = new HashMap<>();
        animalRecordErrors
                .forEach((key, value) -> animalRecordErrorsString.put(key, String.valueOf(value)));
        return animalRecordErrorsString;
    }
}
