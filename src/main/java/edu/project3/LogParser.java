package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;


public class LogParser {
    private static final Pattern pattern =
            Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - (.*) \\[(\\d{2}/[A-Z][a-z]{2,8}/\\d{4}:\\d{2}:\\d{2}:\\d{2} \\+\\d{4})] \"(.*)\" (\\d{3}) (.*) \"(.*)\" \"(.*)\"");


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(new URI("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"))
                .GET()
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        var response =  newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofInputStream());
        try (var stream = new BufferedReader(new InputStreamReader(response.body()))) {

            String line;
            while (!((line = stream.readLine()) == null)) {
                Matcher matcher = pattern.matcher(line);
                if (!matcher.find()) {
                    continue;
                }
                var remote_addr = matcher.group(1);
                var remote_user = matcher.group(2);
                var time_local = matcher.group(3);
                var log_request = matcher.group(4);
                var status = matcher.group(5);
                var body_bytes_sent = matcher.group(6);
                var http_referer = matcher.group(7);
                var http_user_agent = matcher.group(8);
                var logInfo = new LogInfo(remote_addr, remote_user,
                        time_local, log_request,
                        status, body_bytes_sent,
                        http_referer, http_user_agent);
                System.out.println(logInfo);

            }
        }
    }

    public record LogInfo(String remote_addr, String remote_user,
                          String time_local, String request,
                          String status, String body_bytes_sent,
                          String http_referer, String http_user_agent) {}
}
