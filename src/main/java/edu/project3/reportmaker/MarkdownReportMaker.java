package edu.project3.reportmaker;

import edu.project3.statistic.StatisticMaker;
import edu.project3.statistic.StatisticManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.util.Strings;

public class MarkdownReportMaker implements ReportMaker {

    private final List<String> result = new ArrayList<>();

    private final List<StatisticMaker<?>> stats;

    public MarkdownReportMaker(StatisticManager manager) {
        this.stats = manager.getStatistic();
    }

    @SuppressWarnings("MultipleStringLiterals")
    private void processTable(StatisticMaker<?> maker) {
        var stat = maker.getStatistic();
        if (stat == null) {
            return;
        }
        var resourceMap = (Map<Object, Object>) stat;
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
            if (!elem.headers.isEmpty()) {
                processTable(elem);
            } else {
                var stat = elem.getStatistic();
                if (stat == null) {
                    continue;
                }
                result.add(stat.toString() + '\n');
            }
        }
        return result;
    }
}
