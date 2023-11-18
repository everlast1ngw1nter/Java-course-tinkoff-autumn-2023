package edu.project3;

import edu.project3.reportmaker.AdocReportMaker;
import edu.project3.reportmaker.MarkdownReportMaker;
import edu.project3.reportmaker.ReportFormat;
import edu.project3.statistic.AverageAnswerCounter;
import edu.project3.statistic.LogTotalCounter;
import edu.project3.statistic.MostFrequentDay;
import edu.project3.statistic.MostPopularIP;
import edu.project3.statistic.MostRequestedResources;
import edu.project3.statistic.PopularStatus;
import edu.project3.statistic.StatisticManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.net.http.HttpClient.newHttpClient;


public class LogParser {

    public static void main(String[] args)
            throws URISyntaxException, IOException, InterruptedException {
//        String stringPath = null;
        String stringPath = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        LocalDate from = null;
        LocalDate to = null;
        var reportFormat = ReportFormat.MARKDOWN;
        for (var i = 0; i < args.length; i = i + 2) {
            switch (args[i]) {
                case "--path" -> stringPath  = args[i + 1];
                case "--from" -> from = LocalDate.parse(args[i + 1], DateTimeFormatter.ISO_LOCAL_DATE);
                case "--to" -> to = LocalDate.parse(args[i + 1], DateTimeFormatter.ISO_LOCAL_DATE);
                case "--format" -> reportFormat = getReportFormat(args[i + 1]);
            }
        }

        var statisticMakers = List.of(
                new LogTotalCounter(), new PopularStatus(3),
                new AverageAnswerCounter(), new MostRequestedResources(3),
                new MostPopularIP(3), new MostFrequentDay());
        var manager = new StatisticManager(statisticMakers);

        for (var inputStream : getLogStream(stringPath)) {
            try (var stream = new BufferedReader(new InputStreamReader(inputStream))) {
                String log;
                while (!((log = stream.readLine()) == null)) {
                    var logInfo = LogInfo.create(log);
                    manager.makeStatistic(logInfo);
                }
            }
        }
        var md = new MarkdownReportMaker(manager);
        for (var elem : md.makeReport()) {
            System.out.println(elem);
        }
    }

    private static List<InputStream> getStreamFromDirectory(String path) {
        var directory = Path.of(path).getParent();
        try (Stream<Path> paths = Files.walk(directory)) {
            return paths
                    .skip(1)
                    .map(x -> getStreamFromFile(x.toString()))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        } catch (IOException ignore) {
            return List.of(FileInputStream.nullInputStream());
        }
    }


    private static List<InputStream> getStreamFromFile(String path) {
        File file = Path.of(path).toFile();
        if (file.isFile()) {
            try {
                return List.of(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                return List.of(FileInputStream.nullInputStream());
            }
        }
        if (file.isDirectory()) {
            return getStreamFromDirectory(path);
        }
        return List.of(FileInputStream.nullInputStream());
    }

    private static List<InputStream> getLogStream(String path)
            throws URISyntaxException, IOException, InterruptedException {
        if (path.startsWith("http")) {
            return List.of(getStreamFromHttp(path));
        } else {
            return getStreamFromFile(path);
        }
    }

    private static InputStream getStreamFromHttp(String path)
            throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(new URI(path))
                .GET()
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        var response =  newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofInputStream());
        return response.body();
    }

    private static ReportFormat getReportFormat(String stringFormat) {
        return switch (stringFormat) {
            case "adoc" -> ReportFormat.ADOC;
            default -> ReportFormat.MARKDOWN;
        };
    }
}
