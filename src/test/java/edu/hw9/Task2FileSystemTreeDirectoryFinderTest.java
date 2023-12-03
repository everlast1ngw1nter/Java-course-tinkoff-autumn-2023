package edu.hw9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2FileSystemTreeDirectoryFinderTest {

    @Test
    void directoryFinderFileSystemTreeTest(@TempDir File tempDir)
            throws IOException {
        var newDir1 = tempDir.toPath().resolve("dir1").toFile();
        newDir1.mkdir();
        var newDir2 = tempDir.toPath().resolve("dir2").toFile();
        newDir2.mkdir();
        var finder = new Task2FileSystemTreeDirectoryFinder(tempDir, 100);
        assertEquals(finder.find().size(), 0);
        for (var i = 0; i < 101; i++) {
            var newFile = newDir1.toPath().resolve(String.valueOf(i)).toFile();
            newFile.createNewFile();
        }
        assertEquals(finder.find().size(), 1);
    }
}
