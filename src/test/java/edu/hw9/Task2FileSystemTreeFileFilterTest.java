package edu.hw9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2FileSystemTreeFileFilterTest {

    @Test
    void directoryFinderFileSystemTreeTest(@TempDir File tempDir)
            throws IOException {
        var newDir1 = tempDir.toPath().resolve("dir1").toFile();
        newDir1.mkdir();
        var newDir2 = tempDir.toPath().resolve("dir2").toFile();
        newDir2.mkdir();
        var finder = new Task2FileSystemTreeFileFilter(tempDir, file -> file.getName().endsWith("txt"));
        assertEquals(finder.find().size(), 0);
        newDir2.toPath().resolve(1 + ".txt").toFile().createNewFile();
        for (var i = 0; i < 42; i++) {
            var newFile = newDir1.toPath().resolve(i + ".txt").toFile();
            newFile.createNewFile();
        }
        for (var i = 0; i < 50; i++) {
            var newFile = newDir1.toPath().resolve(i + ".docx").toFile();
            newFile.createNewFile();
        }
        assertEquals(finder.find().size(), 43);
    }
}

