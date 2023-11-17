package edu.hw6;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Task3 {

    private Task3() {
    }

    public static AbstractFilter regularFile = Files::isRegularFile;
    public static AbstractFilter readable = Files::isReadable;

    public static AbstractFilter regexContains(String string) {
        Pattern pattern = Pattern.compile(".*" + string + ".*");
        return path -> path.toString().matches(pattern.pattern());
    }

    public static AbstractFilter globMatches(String glob) {
        var matcher = FileSystems
                .getDefault()
                .getPathMatcher("glob:" + glob);
        return path -> matcher.matches(path.getFileName());
    }

    public static AbstractFilter magicNumber(byte... args) {
        return (path) -> {
            var allBytes = Files.readAllBytes(path);
            if (allBytes.length < args.length) {
                return false;
            }
            for (var i = 0; i < args.length; i++) {
                if (args[i] != allBytes[i]) {
                    return false;
                }
            }
            return true;
        };
    }

    public static AbstractFilter largerThan(int size) {
        return path -> path.toFile().length() > size;
    }

    public interface AbstractFilter extends DirectoryStream.Filter<Path> {
        default AbstractFilter and(AbstractFilter filter) {
            return path -> filter.accept(path) && this.accept(path);
        }
    }
}
