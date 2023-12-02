package edu.hw8;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {

    private static final List<String> REQUESTS = List.of(
            "личности", "интеллект",
            "оскорбления", "глупый");

    private static final Set<String> POSSIBLE_ANSWERS = Set.of(
            "Не переходи на личности там, где их нет",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
            "Чем ниже интеллект, тем громче оскорбления");

    @Test
    void answerTest() throws InterruptedException, ExecutionException {
        var serverThread = new Thread(() -> {
            try {
                Task1Server.startServer(4);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();
        Thread.sleep(5000);
        Callable<Void> callable = () -> {
            var rnd = ThreadLocalRandom.current();
            var ans = Task1Client.getCausticResponse(REQUESTS.get(rnd.nextInt(4)));
            assertTrue(POSSIBLE_ANSWERS.contains(ans));
            return null;
        };
        try (var executor = Executors.newFixedThreadPool(8)) {
            var tasks = Stream.generate(() -> callable)
                    .limit(64)
                    .toList();
            var futures = executor.invokeAll(tasks);
            for (var future : futures) {
                future.get();
            }
        }
    }
}
