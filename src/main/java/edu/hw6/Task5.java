package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class Task5 {
    private Task5() {
    }

    public static class HackerNews {

        private static final Pattern titleName =
                Pattern.compile("\"title\":\"([a-zA-Z0-9 ]*)\"");
        public long[] hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
            var request = HttpRequest.newBuilder()
                    .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                    .GET()
                    .timeout(Duration.of(10, ChronoUnit.SECONDS))
                    .build();
            var response = newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return parseStringResponseBody(response.body());
        }

        public String news(long id) throws URISyntaxException, IOException, InterruptedException {
            var request = HttpRequest.newBuilder()
                    .uri(new URI("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
                    .GET()
                    .timeout(Duration.of(10, ChronoUnit.SECONDS))
                    .build();
            var response = newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            var matcher = titleName.matcher(response.body());
            if (!matcher.find()) {
                return "";
            }
            return matcher.group(1);

        }

        private long[] parseStringResponseBody(String body) {
            var bodyParts = body.substring(1, body.length() - 1).split(",");
            var bodyValues = new long[bodyParts.length];
            for (int i = 0; i < bodyParts.length; i++) {
                bodyValues[i] = Long.parseLong(bodyParts[i]);
            }
            return bodyValues;
        }
    }
}
