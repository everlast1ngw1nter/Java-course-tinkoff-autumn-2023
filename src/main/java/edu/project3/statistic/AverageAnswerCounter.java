package edu.project3.statistic;

import edu.project3.LogInfo;

public class AverageAnswerCounter implements StatisticMaker {

    private long totalSize = 0;
    private long counter = 0;

    @Override
    public void makeStatistic(LogInfo logInfo) {
        totalSize += logInfo.bodyBytesSent();
        counter++;
    }

    public Long getStatistic() {
        if (counter == 0) {
            return 0L;
        }
        return totalSize / counter;
    }
}
