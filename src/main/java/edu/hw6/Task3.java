package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public class Task3 {

    private Task3() {
    }

    public interface AbstractFilter extends DirectoryStream.Filter<Path> {
        default AbstractFilter and(AbstractFilter filter) {
            return (path) -> filter.accept(path) && this.accept(path);
        }
    }

    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;


    public static void main(String[] args) throws IOException {
        DirectoryStream.Filter<Path> filter = regularFile
                .and(readable)
                .and(largerThan(100_000))
//                .and(magicNumber(0x89, 'P', 'N', 'G'))
                .and(globMatches("*.png"))
                .and(regexContains("[-]"));

        Path path = Path.of("C:\\Users\\haier\\Desktop\\dir1");
        DirectoryStream<Path> dirStream =  Files.newDirectoryStream(path, filter);
        for (var i : dirStream) {
            var k = i;
            var a = 1;
        }
        var h = 1;

    }

    private static AbstractFilter regexContains(String s) {
        return (path) -> path.getFileName().toString().matches(s);
    }

    private static AbstractFilter globMatches(String glob) {
        PathMatcher matcher = FileSystems.getDefault()
                .getPathMatcher("glob:" + glob);
        return matcher::matches;
    }

    private static AbstractFilter magicNumber(int i, char p, char n, char g) {
        return null;
    }

    private static AbstractFilter largerThan(int i) {
        return (path) -> path.toFile().length() > i;
    }
}
