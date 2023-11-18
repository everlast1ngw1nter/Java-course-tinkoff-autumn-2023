package edu.project3.statistic;

import edu.project3.LogInfo;
import java.util.List;

public class StatisticManager extends StatisticMaker<List<StatisticMaker<?>>> {
    private final List<StatisticMaker<?>> statisticMakers;

    public StatisticManager(List<StatisticMaker<?>> statisticMakers) {
        super("", List.of());
        this.statisticMakers = statisticMakers;
    }

    @Override
    public void makeStatistic(LogInfo logInfo) {
        for (var maker : statisticMakers) {
            maker.makeStatistic(logInfo);
        }
    }

    public List<StatisticMaker<?>> getStatistic() {
        return statisticMakers;
    }
}
