package edu.project3.statistic;

import edu.project3.LogInfo;
import java.util.List;

public class StatisticManager {
    private final List<StatisticMaker<?>> statisticMakers;

    public StatisticManager(List<StatisticMaker<?>> statisticMakers) {
        this.statisticMakers = statisticMakers;
    }

    public void makeStatistic(LogInfo logInfo) {
        for (var maker : statisticMakers) {
            maker.makeStatistic(logInfo);
        }
    }

    public List<StatisticMaker<?>> getStatistic() {
        return statisticMakers;
    }
}
