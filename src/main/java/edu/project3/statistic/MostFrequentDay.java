package edu.project3.statistic;

import edu.project3.LogInfo;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MostFrequentDay implements StatisticMaker {
    private final Map<LocalDate, Integer> top = new HashMap<>();

    @Override
    public void makeStatistic(LogInfo logInfo) {
        var localTime = logInfo.localTime().toLocalDate();
        if (top.containsKey(localTime)) {
            top.put(localTime, top.get(localTime) + 1);
        } else {
            top.put(localTime, 1);
        }
    }

    public LocalDate getStatistic() {
        return top.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
