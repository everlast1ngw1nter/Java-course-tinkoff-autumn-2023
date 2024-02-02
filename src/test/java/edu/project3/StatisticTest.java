package edu.project3;

import edu.project3.statistic.AverageAnswerCounter;
import edu.project3.statistic.LogTotalCounter;
import edu.project3.statistic.MostFrequentDay;
import edu.project3.statistic.MostPopularIP;
import edu.project3.statistic.MostRequestedResources;
import edu.project3.statistic.PopularStatus;
import edu.project3.statistic.StatisticManager;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatisticTest {

    private static final List<LogInfo> logInfos = List.of(
            LogInfo.create("80.91.33.133 - - [17/May/2015:08:05:24 +0000] \"GET /downloads/product_1"
                    + " HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)\""),
            LogInfo.create("65.39.197.164 - - [21/May/2015:12:05:51 +0000] \"GET /downloads/product_1"
                    + " HTTP/1.1\" 404 328 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\""),
            LogInfo.create("54.183.198.11 - - [21/May/2015:12:05:41 +0000] \"GET /downloads/product_1"
                    + " HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\""),
            LogInfo.create("54.183.198.11 - - [21/May/2015:12:05:37 +0000] \"GET /downloads/product_1"
                    +" HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\""));

    @Test
    void popularStatusTest() {
        var stat = new PopularStatus(3);
        for (var logInfo : logInfos) {
            stat.makeStatistic(logInfo);
        }
        var getStat = stat.getStatistic();
        assertEquals(getStat.size(), 2);
        assertTrue(getStat.containsKey(304));
        assertTrue(getStat.containsKey(404));
        assertEquals(getStat.get(304), 3);
        assertEquals(getStat.get(404), 1);
    }

    @Test
    void mostRequestedResourcesTest() {
        var stat = new MostRequestedResources(3);
        for (var logInfo : logInfos) {
            stat.makeStatistic(logInfo);
        }
        var getStat = stat.getStatistic();
        assertEquals(getStat.size(), 1);
        assertTrue(getStat.containsKey("/downloads/product_1"));
        assertEquals(getStat.get("/downloads/product_1"), 4);
    }

    @Test
    void mostPopularIPTest() {
        var stat = new MostPopularIP(1);
        for (var logInfo : logInfos) {
            stat.makeStatistic(logInfo);
        }
        var getStat = stat.getStatistic();
        assertEquals(getStat.size(), 1);
        assertTrue(getStat.containsKey("54.183.198.11"));
        assertEquals(getStat.get("54.183.198.11"), 2);
    }

    @Test
    void mostFrequentDayTest() {
        var stat = new MostFrequentDay();
        for (var logInfo : logInfos) {
            stat.makeStatistic(logInfo);
        }
        var getStat = stat.getStatistic();
        assertEquals(getStat, LocalDate.of(2015, 5 , 21));
    }

    @Test
    void logTotalCounterTest() {
        var stat = new LogTotalCounter();
        for (var logInfo : logInfos) {
            stat.makeStatistic(logInfo);
        }
        var getStat = stat.getStatistic();
        assertEquals(getStat, 4);
    }

    @Test
    void averageAnswerCounterTest() {
        var stat = new AverageAnswerCounter();
        for (var logInfo : logInfos) {
            stat.makeStatistic(logInfo);
        }
        var getStat = stat.getStatistic();
        assertEquals(getStat, 82);
    }

    @Test
    void statisticManagerTest() {
        var stat = new StatisticManager(
                List.of(
                new AverageAnswerCounter(),
                new LogTotalCounter(),
                new PopularStatus(2))
        );
        for (var logInfo : logInfos) {
            stat.makeStatistic(logInfo);
        }
        var getStat = stat.getStatistic();
        assertEquals(getStat.size(), 3);
        assertEquals(getStat.get(0).getStatistic(), 82L);
        assertEquals(getStat.get(1).getStatistic(), 4L);
        assertTrue(getStat.get(2).getStatistic() instanceof Map);
    }
}
