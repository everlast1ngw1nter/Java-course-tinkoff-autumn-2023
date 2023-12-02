package edu.hw8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.Executors;

public class Task1Server {

    private Task1Server() {
    }

    private static final int PORT = 53;

    private static final Map<String, String> ANSWER_MAP = Map.of(
            "личности",
            "Не переходи на личности там, где их нет",
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления");

    public static void startServer(int threads) throws IOException {

        try (var server = new ServerSocket(PORT);
             var executor = Executors.newFixedThreadPool(threads)) {
            while (true) {
                var client = server.accept();
                Runnable runnable = () -> {
                    try (var reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                        var inputString = reader.readLine();
                        var writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                        writer.write(ANSWER_MAP.get(inputString));
                        writer.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                };
                executor.submit(runnable);
            }
        }
    }
}
