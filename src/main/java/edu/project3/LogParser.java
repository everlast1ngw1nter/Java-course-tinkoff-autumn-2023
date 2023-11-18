package edu.project3;

import edu.project3.reportmaker.AdocReportMaker;
import edu.project3.reportmaker.MarkdownReportMaker;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static java.net.http.HttpClient.newHttpClient;


public class LogParser {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(new URI("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"))
                .GET()
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        var response =  newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofInputStream());
        var statisticMakers = List.of(
                new LogTotalCounter(), new PopularStatus(3),
                new AverageAnswerCounter(), new MostRequestedResources(3),
                new MostPopularIP(3), new MostFrequentDay());
        var manager = new StatisticManager(statisticMakers);
        try (var stream = new BufferedReader(new InputStreamReader(response.body()))) {
            String log;
            while (!((log = stream.readLine()) == null)) {
                var logInfo = LogInfo.create(log);
                manager.makeStatistic(logInfo);
            }
            var md = new MarkdownReportMaker(manager);
            for (var elem : md.makeReport()) {
                System.out.println(elem);
            }
        }
    }
}
