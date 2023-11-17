package edu.hw6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Task2 {

    private Task2() {

    }

    public static void cloneFile(Path path) throws IOException {
        var copies = getAllCopies(path);
        var fileName = path.getFileName();
        var nextFileIndex = findMinNextIndex(copies);
        var newPathName = createNewFileName(fileName, nextFileIndex);
        var fullNewPath = path.getParent().resolve(newPathName).toFile();
        if (!fullNewPath.createNewFile()) {
            throw new IOException("Cant create file in current directory");
        }
        copyFileUsingChannel(path.toFile(), fullNewPath);
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

    private static int findMinNextIndex(List<Integer> indexes) {
        indexes.sort(Comparator.naturalOrder());
        for (var index = 0; index < indexes.size(); index++) {
            if (indexes.get(index) != index + 1) {
                return index + 1;
            }
        }
        return indexes.size() + 1;
    }

    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        try (var inputStream = new FileInputStream(source);
             var outputStream = new FileOutputStream(dest)) {
            var sourceChannel = inputStream.getChannel();
            var destChannel = outputStream.getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    private static List<Integer> getAllCopies(Path path) throws IOException {
        var allCopies = new ArrayList<Integer>();
        var fileName = path.getFileName();
        var nameAndExtension = fileName.toString().split("\\.");
        var fileNameWithoutExtension = nameAndExtension[0];
        var fileExtension = nameAndExtension[1];
        Pattern copyPattern =
                Pattern.compile(fileNameWithoutExtension + " - копия ?\\(?(\\d+)?\\)?\\." + fileExtension);
        var siblings = getSiblings(path);
        for (var elem : siblings) {
            var name = elem.getFileName().toString();
            var matcher = copyPattern.matcher(name);
            if (!matcher.find()) {
                continue;
            }
            var copyNumber = matcher.group(1);
            if (copyNumber == null) {
                allCopies.add(1);
            } else {
                allCopies.add(Integer.valueOf(matcher.group(1)));
            }
        }
        return allCopies;
    }

    private static List<Path> getSiblings(Path path) throws IOException {
        var directory = path.getParent();
        try (Stream<Path> paths = Files.walk(directory)) {
            return paths
                    .skip(1)
                    .toList();
        } catch (IOException ex) {
            throw new IOException();
        }
    }
}
