package edu.project3.converter;

import edu.project3.statistic.StatisticMaker;
import edu.project3.statistic.StatisticManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarkdownConverter implements Converter {

    private final Logger LOGGER = LogManager.getLogger();

    private final List<String> result = new ArrayList<>();

    private final List<StatisticMaker<?>> stats;

    public MarkdownConverter(StatisticManager manager) {
        this.stats = manager.getStatistic();
    }

    private void processTable(StatisticMaker<?> maker) {
        Map<String, String> resourceMap = (Map<String, String>) maker.getStatistic();
        result.add(String.format("| %15s | %15s |%n", maker.headers.get(0), maker.headers.get(1)));
        for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
            result.add(String.format("| %15s | %15s |%n", entry.getKey(), entry.getValue()));
        }
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
