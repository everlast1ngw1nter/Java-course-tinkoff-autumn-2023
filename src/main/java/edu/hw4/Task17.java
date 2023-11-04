package edu.hw4;

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;

public class Task17 {
    private Task17() {
    }

    public static boolean isSpidersBytesMoreThanDogs(List<Animal> animals) {
        Double spiderBitesFrequency = getBytesStatistic(animals, Animal.Type.SPIDER);
        Double dogBitesFrequency = getBytesStatistic(animals, Animal.Type.DOG);
        if (spiderBitesFrequency == null || dogBitesFrequency == null) {
            return false;
        }
        return spiderBitesFrequency > dogBitesFrequency;
    }

    private static Double getBytesStatistic(List<Animal> animals, Animal.Type type) {
        Map<Boolean, List<Animal>> animalBites = animals
                .stream()
                .filter(x -> x.type().equals(type))
                .collect(groupingBy(Animal::bites));
        if (animalBites.isEmpty()) {
            return null;
        } else if (animalBites.containsKey(true) && !animalBites.containsKey(false)) {
            return 1D;
        } else if (!animalBites.containsKey(true) && animalBites.containsKey(false)) {
            return 0D;
        }
        double bites = animalBites.get(true).size();
        double notBites = animalBites.get(false).size();
        return bites / (bites + notBites);
    }
}
