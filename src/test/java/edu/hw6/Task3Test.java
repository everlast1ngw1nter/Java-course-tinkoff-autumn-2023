package edu.hw6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import static edu.hw6.Task3.globMatches;
import static edu.hw6.Task3.largerThan;
import static edu.hw6.Task3.magicNumber;
import static edu.hw6.Task3.regexContains;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    void globMatchesTest(@TempDir Path tempDir)
            throws IOException {
        DirectoryStream.Filter<Path> filter = globMatches("*.txt");
        Path filePath1 = tempDir.resolve("task3.txt");
        Path filePath2 = tempDir.resolve("task3.png");
        Path filePath3 = tempDir.resolve("task3.docx");
        assertTrue(filter.accept(filePath1));
        assertFalse(filter.accept(filePath2));
        assertFalse(filter.accept(filePath3));
    }

    @Test
    void regexContainsTest(@TempDir Path tempDir)
            throws IOException {
        DirectoryStream.Filter<Path> filter = regexContains("ask3");
        Path filePath1 = tempDir.resolve("task3.txt");
        Path filePath2 = tempDir.resolve("tak3.txt");
        Path filePath3 = tempDir.resolve("task3.docx");
        assertTrue(filter.accept(filePath1));
        assertFalse(filter.accept(filePath2));
        assertTrue(filter.accept(filePath3));
    }

    @Test
    void magicNumberTest(@TempDir Path tempDir)
            throws IOException {
        DirectoryStream.Filter<Path> filter = magicNumber((byte)0x89, (byte)'P',
                (byte)'N',(byte) 'G');
        Path filePath1 = Path.of("src/test/java/edu/hw6/Java-Logo.png");
        Path filePath2 = tempDir.resolve("task3.txt");
        filePath2.toFile().createNewFile();
        assertTrue(filter.accept(filePath1));
        assertFalse(filter.accept(filePath2));
    }

    @Test
    void largerThanTest(@TempDir Path tempDir)
            throws IOException {
        DirectoryStream.Filter<Path> filter = largerThan(10000);
        Path filePath1 = Path.of("src/test/java/edu/hw6/Java-Logo.png");
        Path filePath2 = tempDir.resolve("task3.txt");
        filePath2.toFile().createNewFile();
        assertTrue(filter.accept(filePath1));
        assertFalse(filter.accept(filePath2));
    }

    @Test
    void andTest(@TempDir Path tempDir) throws IOException {
        DirectoryStream.Filter<Path> filter = regexContains("ask3")
                .and(globMatches("*.txt"));
        Path filePath1 = tempDir.resolve("task3.txt");
        Path filePath2 = tempDir.resolve("tak3.txt");
        Path filePath3 = tempDir.resolve("tak3.docx");
        assertTrue(filter.accept(filePath1));
        assertFalse(filter.accept(filePath2));
        assertFalse(filter.accept(filePath3));
    }
}
