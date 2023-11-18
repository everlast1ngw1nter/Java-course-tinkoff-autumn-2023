package edu.project3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamCreatorTest {
    @Test
    void getLogStreamsHttpTest() throws URISyntaxException, IOException, InterruptedException {
        var logStreams = StreamCreator.getLogStreams("https://raw.githubusercontent.com/elastic/examples/"
                + "master/Common%20Data%20Formats/nginx_logs/nginx_logs");
        assertEquals(logStreams.size(), 1);
    }

    @Test
    void getLogStreamsDirectoryTest(@TempDir Path tempDir) throws URISyntaxException, IOException, InterruptedException {
        tempDir.resolve("file1.txt").toFile().createNewFile();
        tempDir.resolve("file2.txt").toFile().createNewFile();
        var logStreams = StreamCreator.getLogStreams(tempDir.toString());
        assertEquals(logStreams.size(), 2);
        for (var stream : logStreams) {
            stream.close();
        }
    }
}
