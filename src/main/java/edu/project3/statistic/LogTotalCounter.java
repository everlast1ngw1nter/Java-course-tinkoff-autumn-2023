package edu.project3.statistic;

import edu.project3.LogInfo;

public class LogTotalCounter implements StatisticMaker {

    private long counter = 0;

    @Override
    public void makeStatistic(LogInfo logInfo) {
        counter++;
    }

    public Long getStatistic() {
        return counter;
    }
}
