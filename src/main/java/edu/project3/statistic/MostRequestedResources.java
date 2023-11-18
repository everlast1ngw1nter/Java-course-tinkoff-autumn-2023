package edu.project3.statistic;

import edu.project3.LogInfo;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostRequestedResources extends StatisticMaker<Map<String, Integer>> {
    private final int numberResources;
    private final Map<String, Integer> top;

    public MostRequestedResources(int numberResources) {
        super("Requested resources", List.of("Resource", "Number of requests"));
        this.top = new HashMap<>();
        this.numberResources = numberResources;
    }

    @Override
    public void makeStatistic(LogInfo logInfo) {
        var resource = logInfo.request().split(" ")[1];
        if (top.containsKey(resource)) {
            top.put(resource, top.get(resource) + 1);
        } else {
            top.put(resource, 1);
        }
    }

    public Map<String, Integer> getStatistic() {
        return top.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numberResources)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
