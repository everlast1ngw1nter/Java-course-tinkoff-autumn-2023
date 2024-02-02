package edu.project3;

import edu.project3.reportmaker.AdocReportMaker;
import edu.project3.reportmaker.MarkdownReportMaker;
import edu.project3.statistic.LogTotalCounter;
import edu.project3.statistic.PopularStatus;
import edu.project3.statistic.StatisticManager;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportMakerTest {

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
    void adocReportMakerTest() {
        var stat = new StatisticManager(
                List.of(
                        new LogTotalCounter(),
                        new PopularStatus(2))
        );
        for (var logInfo : logInfos) {
            stat.makeStatistic(logInfo);
        }
        var adoc = new AdocReportMaker(stat);
        var report = adoc.makeReport();
        var expected = List.of(
                "==== Total requests",
                "4",
                "==== Response Codes",
                "[cols=2]",
                "|====",
                "|Status",
                "|Number of requests",
                "",
                "|304 pass:[<br>]404 pass:[<br>]",
                "|3 pass:[<br>]1 pass:[<br>]",
                "|====");
        assertEquals(report.size(), expected.size());
        for (var i = 0; i < report.size(); i++) {
            assertEquals(report.get(i).strip(), expected.get(i));
        }
    }

    @Test
    void markdownReportMakerTest() {
        var stat = new StatisticManager(
                List.of(
                        new LogTotalCounter(),
                        new PopularStatus(2))
        );
        for (var logInfo : logInfos) {
            stat.makeStatistic(logInfo);
        }
        var adoc = new MarkdownReportMaker(stat);
        var report = adoc.makeReport();
        var expected = List.of(
                "#### Total requests",
                "4",
                "#### Response Codes",
                "| Status | Number of requests |",
                "| ------ | ------ |",
                "| 304 | 3 |",
                "| 404 | 1 |",
                "");
        assertEquals(report.size(), expected.size());
        for (var i = 0; i < report.size(); i++) {
            assertEquals(report.get(i).strip(), expected.get(i));
        }
    }
}
