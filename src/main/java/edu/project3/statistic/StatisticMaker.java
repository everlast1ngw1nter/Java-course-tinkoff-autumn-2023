package edu.project3.statistic;

import edu.project3.LogInfo;
import java.util.List;

public abstract class StatisticMaker<T> {

    public final List<String> headers;

    public final String mainHeader;

    public StatisticMaker(String mainHeader, List<String> headers) {
        this.mainHeader = mainHeader;
        this.headers = headers;
    }

    public abstract void makeStatistic(LogInfo logInfo);

    public abstract T getStatistic();
}
