package edu.hw6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    @Test
    void createFromFileTest(@TempDir Path tempDir)
            throws IOException {
        Path filePath = tempDir.resolve("task1create.txt");
        Files.write(filePath, Arrays.asList("1:2", "3:4"));
        var map = new Task1.DiskMap(filePath.toFile());
        assertTrue(map.containsKey("1"));
        assertEquals(map.get("1"), "2");
        assertTrue(map.containsKey("3"));
        assertEquals(map.get("3"), "4");
    }

    @Test
    void addToFileTest(@TempDir Path tempDir)
            throws IOException {
        Path filePath = tempDir.resolve("task1add.txt");
        var map = new Task1.DiskMap(filePath.toFile());
        map.put("1", "2");
        map.put("4", "3");
        map.save();
        var strings = Files.readAllLines(filePath);
        assertEquals(strings.size(), 2);
        assertTrue(strings.contains("1:2"));
        assertTrue(strings.contains("4:3"));
    }
}
