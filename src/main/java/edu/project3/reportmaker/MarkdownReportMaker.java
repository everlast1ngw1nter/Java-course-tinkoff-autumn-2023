package edu.project3.reportmaker;

import edu.project3.statistic.StatisticMaker;
import edu.project3.statistic.StatisticManager;
import org.apache.logging.log4j.util.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarkdownReportMaker implements ReportMaker {

    private final List<String> result = new ArrayList<>();

    private final List<StatisticMaker<?>> stats;

    public MarkdownReportMaker(StatisticManager manager) {
        this.stats = manager.getStatistic();
    }

    private void processTable(StatisticMaker<?> maker) {
        var resourceMap = (Map<Object, Object>) maker.getStatistic();
        result.add(String.format("| %s | %s |", maker.headers.get(0), maker.headers.get(1)));
        result.add("| ------ | ------ |");
        for (var entry : resourceMap.entrySet()) {
            result.add(String.format("| %s | %s |", entry.getKey(), entry.getValue()));
        }
        result.add(Strings.EMPTY);
    }

    public List<String> makeReport() {
        for (var elem : stats) {
            result.add("#### " + elem.mainHeader + '\n');
            if (!elem.headers.isEmpty()){
                processTable(elem);
            } else {
                result.add(elem.getStatistic().toString() + '\n');
            }
        }
        return result;
    }
}
