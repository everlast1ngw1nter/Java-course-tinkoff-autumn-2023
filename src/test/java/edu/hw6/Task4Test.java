package edu.hw6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    void writeToFileTest(@TempDir Path tempDir)
            throws IOException {
        Path filePath = tempDir.resolve("task4.txt");
        Task4.outputStreamComposition(filePath.toFile());
        var lines = Files.readAllLines(filePath);
        assertEquals(lines.size(), 1);
        assertEquals(lines.get(0),
                "Programming is learned by writing programs. ― Brian Kernighan");
    }
}
