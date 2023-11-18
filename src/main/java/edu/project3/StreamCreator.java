package edu.project3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.net.http.HttpClient.newHttpClient;

public class StreamCreator {
    private StreamCreator() {
    }

    public static List<InputStream> getLogStreams(String stringPath)
            throws URISyntaxException, IOException, InterruptedException {
        return getAllStreams(stringPath);
    }

    private static List<InputStream> getStreamFromDirectory(String path) {
        var directory = Path.of(path);
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

    private static List<InputStream> getAllStreams(String path)
            throws URISyntaxException, IOException, InterruptedException {
        if (path.startsWith("http")) {
            return List.of(getStreamFromHttp(path));
        } else {
            return getStreamFromFile(path);
        }
    }

    @SuppressWarnings("MagicNumber")
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
}
