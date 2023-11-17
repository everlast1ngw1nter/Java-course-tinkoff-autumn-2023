package edu.hw6;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {
    @Test
    void newsTest()
            throws IOException, InterruptedException {
        var hackerNews = new Task5.HackerNews();
        var title = hackerNews.news(37570037);
        assertEquals(title, "JDK 21 Release Notes");
    }
}
