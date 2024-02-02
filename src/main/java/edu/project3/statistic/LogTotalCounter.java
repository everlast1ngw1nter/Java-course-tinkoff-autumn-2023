package edu.project3.statistic;

import edu.project3.LogInfo;
import java.util.List;

public class LogTotalCounter extends StatisticMaker<Long> {

    private long counter = 0;

    public LogTotalCounter() {
        super("Total requests", List.of());
    }

    @Override
    public void makeStatistic(LogInfo logInfo) {
        counter++;
    }

    @Override
    public Long getStatistic() {
        return counter;
    }
}
