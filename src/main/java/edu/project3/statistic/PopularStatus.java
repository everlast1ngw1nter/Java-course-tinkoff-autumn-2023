package edu.project3.statistic;

import edu.project3.LogInfo;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PopularStatus extends StatisticMaker<Map<Integer, Integer>> {
    private final int numberStatuses;

    private final Map<Integer, Integer> top;

    public PopularStatus(int numberStatuses) {
        super("Response Codes", List.of("Status", "Number of requests"));
        this.top = new HashMap<>();
        this.numberStatuses = numberStatuses;
    }

    @Override
    public void makeStatistic(LogInfo logInfo) {
        var status = logInfo.status();
        if (top.containsKey(status)) {
            top.put(status, top.get(status) + 1);
        } else {
            top.put(status, 1);
        }
    }

    public Map<Integer, Integer> getStatistic() {
        return top.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numberStatuses)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
