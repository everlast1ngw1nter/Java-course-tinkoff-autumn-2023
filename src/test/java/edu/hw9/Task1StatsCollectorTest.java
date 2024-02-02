package edu.hw9;

import org.junit.jupiter.api.Test;
import java.util.List;
import edu.hw9.Task1StatsCollector.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1StatsCollectorTest {

    @Test
    void statsCollectorTest()
            throws InterruptedException {
        var statCol = new Task1StatsCollector(3, 2);
        var data = List.of(
                new StatInfo(MetricName.MIN, List.of(0.1, 0.05, 1.4, 5.1, 0.3)),
                new StatInfo(MetricName.MAX, List.of(0.1, 0.05, 1.4, 5.1, 0.3)),
                new StatInfo(MetricName.AVG, List.of(4.0, 6.0)),
                new StatInfo(MetricName.SUM, List.of(4.0, 6.0)),
                new StatInfo(MetricName.MIN, List.of(-5.0, 6.0)));
        statCol.start(data);
        Thread.sleep(1000);
        var processedData = statCol.getProcessedData();
        assertTrue(processedData.contains(new ResultInfo(MetricName.MIN, 0.05)));
        assertTrue(processedData.contains(new ResultInfo(MetricName.MAX, 5.1)));
        assertTrue(processedData.contains(new ResultInfo(MetricName.AVG, 5.0)));
        assertTrue(processedData.contains(new ResultInfo(MetricName.SUM, 10.0)));
        assertTrue(processedData.contains(new ResultInfo(MetricName.MIN, -5.0)));
    }
}
