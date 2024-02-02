package edu.project3.statistic;

import edu.project3.LogInfo;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostPopularIP extends StatisticMaker<Map<String, Integer>> {
    private final int numberIP;
    private final Map<String, Integer> top;

    public MostPopularIP(int numberIP) {
        super("IP addresses", List.of("IP", "Number of requests"));
        this.top = new HashMap<>();
        this.numberIP = numberIP;
    }

    @Override
    public void makeStatistic(LogInfo logInfo) {
        var remoteAddress = logInfo.remoteAddress();
        if (top.containsKey(remoteAddress)) {
            top.put(remoteAddress, top.get(remoteAddress) + 1);
        } else {
            top.put(remoteAddress, 1);
        }
    }

    @Override
    public Map<String, Integer> getStatistic() {
        return top.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numberIP)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
