package edu.project3.reportmaker;

import edu.project3.statistic.StatisticMaker;
import edu.project3.statistic.StatisticManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdocReportMaker implements ReportMaker {
    private final List<String> result = new ArrayList<>();

    private final List<StatisticMaker<?>> stats;

    public AdocReportMaker(StatisticManager manager) {
        this.stats = manager.getStatistic();
    }

    @SuppressWarnings("MultipleStringLiterals")
    private void processTable(StatisticMaker<?> maker) {
        var stat = maker.getStatistic();
        if (stat == null) {
            return;
        }
        var resourceMap = (Map<Object, Object>) stat;
        result.add("[cols=2]");
        result.add("|====");
        for (var elem : maker.headers) {
            result.add("|" + elem);
        }
        result.add("");
        var firstColumn = new StringBuilder("|");
        var secondColumn = new StringBuilder("|");
        for (var entry : resourceMap.entrySet()) {
            firstColumn.append(entry.getKey().toString()).append(" pass:[<br>]");
            secondColumn.append(entry.getValue().toString()).append(" pass:[<br>]");
        }
        result.add(firstColumn.toString());
        result.add(secondColumn.toString());
        result.add("|====");
    }

    public List<String> makeReport() {
        for (var elem : stats) {
            result.add("==== " + elem.mainHeader + '\n');
            if (!elem.headers.isEmpty()) {
                processTable(elem);
            } else {
                var stat = elem.getStatistic();
                if (stat == null) {
                    continue;
                }
                result.add(stat .toString() + '\n');
            }
        }
        return result;
    }
}
