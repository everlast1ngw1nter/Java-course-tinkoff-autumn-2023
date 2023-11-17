package edu.project3;

import edu.project3.statistic.AverageAnswerCounter;
import edu.project3.statistic.LogTotalCounter;
import edu.project3.statistic.MostFrequentDay;
import edu.project3.statistic.MostPopularIP;
import edu.project3.statistic.MostRequestedResources;
import edu.project3.statistic.PopularStatus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
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
        var totalCounter = new LogTotalCounter();
        var statusCounter = new PopularStatus(3);
        var averageAnswerSize = new AverageAnswerCounter();
        var mostRequestedResources = new MostRequestedResources(3);
        var mostPopularIP = new MostPopularIP(3);
        var mostFrequentDay = new MostFrequentDay();
        try (var stream = new BufferedReader(new InputStreamReader(response.body()))) {
            String log;
            while (!((log = stream.readLine()) == null)) {
                var logInfo = LogInfo.create(log);
                totalCounter.makeStatistic(logInfo);
                statusCounter.makeStatistic(logInfo);
                averageAnswerSize.makeStatistic(logInfo);
                mostRequestedResources.makeStatistic(logInfo);
                mostPopularIP.makeStatistic(logInfo);
                mostFrequentDay.makeStatistic(logInfo);
            }
            System.out.println(statusCounter.getStatistic());
            System.out.println(totalCounter.getStatistic());
            System.out.println(averageAnswerSize.getStatistic());
            System.out.println(mostRequestedResources.getStatistic());
            System.out.println(mostPopularIP.getStatistic());
            System.out.println(mostFrequentDay.getStatistic());
        }
    }
}
