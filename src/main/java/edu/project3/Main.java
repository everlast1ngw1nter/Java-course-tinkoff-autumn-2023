package edu.project3;

import edu.project3.reportmaker.ReportMakerCreator;
import edu.project3.statistic.AverageAnswerCounter;
import edu.project3.statistic.LogTotalCounter;
import edu.project3.statistic.MostFrequentDay;
import edu.project3.statistic.MostPopularIP;
import edu.project3.statistic.MostRequestedResources;
import edu.project3.statistic.PopularStatus;
import edu.project3.statistic.StatisticManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private Main() {
    }

    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args)
            throws URISyntaxException, IOException, InterruptedException {
        var argsInfo = new ArgsReader(args).getArgsInfo();

        var statisticMakers = List.of(
                new LogTotalCounter(), new PopularStatus(3),
                new AverageAnswerCounter(), new MostRequestedResources(3),
                new MostPopularIP(3), new MostFrequentDay());
        var manager = new StatisticManager(statisticMakers);

        for (var inputStream : StreamCreator.getLogStreams(argsInfo.stringPath())) {
            try (var stream = new BufferedReader(new InputStreamReader(inputStream))) {
                String log;
                while (!((log = stream.readLine()) == null)) {
                    var logInfo = LogInfo.create(log);
                    if (inTimeBounds(logInfo.localTime(), argsInfo.from(), argsInfo.to())) {
                        manager.makeStatistic(logInfo);
                    }
                }
            }
        }
        var maker = ReportMakerCreator.create(argsInfo.reportFormat(), manager);
        for (var elem : maker.makeReport()) {
            LOGGER.info(elem);
        }
    }

    private static boolean inTimeBounds(OffsetDateTime logTime, LocalDate from, LocalDate to) {
        if (from != null) {
            var fromWithOffset = OffsetDateTime.of(from.atStartOfDay(), logTime.getOffset());
            if (logTime.isBefore(fromWithOffset)) {
                return false;
            }
        }
        if (to != null) {
            var toWithOffset = OffsetDateTime.of(to.atStartOfDay(), logTime.getOffset());
            return !logTime.isAfter(toWithOffset);
        }
        return true;
    }
}
