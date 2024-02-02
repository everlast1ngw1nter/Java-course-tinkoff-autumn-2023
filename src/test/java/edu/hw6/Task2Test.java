package edu.hw6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @Test
    void createCopiesWithDataTest(@TempDir Path tempDir)
            throws IOException {
        Path filePath = tempDir.resolve("task2.txt");
        var inputData = Arrays.asList("1:2", "3:4");
        Files.write(filePath, inputData);
        for (var i = 0; i < 3; i++) {
            Task2.cloneFile(filePath);
        }
        try (var createdFiles = Files.walk(tempDir)) {
            var files = createdFiles.toList();
            assertTrue(files.contains(tempDir.resolve("task2 - копия.txt")));
            assertTrue(files.contains(tempDir.resolve("task2 - копия (2).txt")));
            assertTrue(files.contains(tempDir.resolve("task2 - копия (3).txt")));
            var newLines = Files.readAllLines(tempDir.resolve("task2 - копия (3).txt"));
            assertEquals(newLines.size(), inputData.size());
            for (var i = 0; i < newLines.size(); i++) {
                assertEquals(newLines.get(i), inputData.get(i));
            }
        }
    }
}
