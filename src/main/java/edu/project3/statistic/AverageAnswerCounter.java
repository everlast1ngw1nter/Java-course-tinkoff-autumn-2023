package edu.project3.statistic;

import edu.project3.LogInfo;
import java.util.List;

public class AverageAnswerCounter extends StatisticMaker<Long> {

    private long totalSize = 0;
    private long counter = 0;

    public AverageAnswerCounter() {
        super("Средний размер запроса", List.of());
    }

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
