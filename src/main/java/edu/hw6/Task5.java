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
        private static final Pattern TITLE_NAME =
                Pattern.compile("\"title\":\"([a-zA-Z0-9 ]*)\"");

        public long[] hackerNewsTopStories()
                throws IOException, InterruptedException {
            var uri = getURI("https://hacker-news.firebaseio.com/v0/topstories.json");
            var request = createRequest(uri);
            var response = getResponse(request);
            return parseStringResponseBody(response.body());
        }

        public String news(long id) throws IOException, InterruptedException {
            var uri = getURI("https://hacker-news.firebaseio.com/v0/item/" + id + ".json");
            var request = createRequest(uri);
            var response = getResponse(request);
            return getNewsTitle(response);
        }

        private String getNewsTitle(HttpResponse<String> response) {
            var matcher = TITLE_NAME.matcher(response.body());
            if (!matcher.find()) {
                return "";
            }
            return matcher.group(1);
        }

        private HttpResponse<String> getResponse(HttpRequest request)
                throws IOException, InterruptedException {
            return newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        }

        private URI getURI(String address) {
            try {
                return new URI(address);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Incorrect address");
            }
        }

        @SuppressWarnings("MagicNumber")
        private HttpRequest createRequest(URI uri) {
            return HttpRequest.newBuilder()
                        .uri(uri)
                        .GET()
                        .timeout(Duration.of(10, ChronoUnit.SECONDS))
                        .build();
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
