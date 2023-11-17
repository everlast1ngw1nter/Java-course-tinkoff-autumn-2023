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
        try (var stream = new BufferedReader(new InputStreamReader(response.body()))) {
            String log;
            while (!((log = stream.readLine()) == null)) {
                var logInfo = LogInfo.create(log);
                System.out.println(logInfo);
            }
        }
    }
}
