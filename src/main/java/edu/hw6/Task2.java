package edu.hw6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class Task2 {

    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        var newPathName = findNextAvailableName(path);
        var fullNewPath = path.getParent().resolve(newPathName).toFile();
        if (!fullNewPath.createNewFile()) {
            throw new IOException("Cant create file in current directory");
        }
        copyFileUsingChannel(path.toFile(), fullNewPath);
    }

    private static Path findNextAvailableName(Path fileName) {
        var currIndex = 1;
        var newPath = createNewFileName(fileName, currIndex);
        while (newPath.toFile().exists()) {
            currIndex++;
            newPath = createNewFileName(fileName, currIndex);
        }
        return newPath;
    }

    @SuppressWarnings("MultipleStringLiterals")
    private static Path createNewFileName(Path fileName, int index) {
        var nameAndExtension = fileName.toString().split("\\.");
        var fileNameWithoutExtension = nameAndExtension[0];
        var fileExtension = nameAndExtension[1];
        if (index == 1) {
            String stringPath = fileNameWithoutExtension + " - копия." + fileExtension;
            return Path.of(stringPath);
        }
        String stringPath = fileNameWithoutExtension + " - копия (" + index + ")." + fileExtension;
        return Path.of(stringPath);
    }

    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        try (var inputStream = new FileInputStream(source);
             var outputStream = new FileOutputStream(dest)) {
            var sourceChannel = inputStream.getChannel();
            var destChannel = outputStream.getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }
    }
}
