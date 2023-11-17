package edu.hw6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Task1 {
    private Task1() {
    }

    public static class DiskMap implements Map<String, String> {

        private static final Pattern FILE_STRING_PATTERN =
                Pattern.compile("^(.*):(.*)$");
        private final Map<String, String> diskMap;

        private final File fileMap;

        public DiskMap(File file) throws IOException {
            diskMap = new HashMap<>();
            fileMap = createDiskMapFile(file);
        }

        @SuppressWarnings("MultipleStringLiterals")
        private File createDiskMapFile(File file) throws IOException {
            try {
                if (file.isFile()) {
                    loadFromFile(file);
                    return file;
                } else if (file.createNewFile()) {
                    return file;
                } else {
                    throw new IOException("Cant create file for DiskMap");
                }
            } catch (IOException e) {
                throw new IOException("Cant create file for DiskMap");
            }
        }

        private void loadFromFile(File file) throws IOException {
            var lines = Files.readAllLines(file.toPath());
            for (var line : lines) {
                var matcher = FILE_STRING_PATTERN.matcher(line);
                if (matcher.find()) {
                    diskMap.put(matcher.group(1), matcher.group(2));
                }
            }
        }

        @Override
        public int size() {
            return diskMap.size();
        }

        @Override
        public boolean isEmpty() {
            return diskMap.isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            return diskMap.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return diskMap.containsValue(value);
        }

        @Override
        public String get(Object key) {
            return diskMap.get(key);
        }

        @Nullable
        @Override
        public String put(String key, String value) {
            try {
                putInFile(key, value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return diskMap.put(key, value);
        }

        private void putInFile(String key, String value) throws IOException {
            try (FileWriter writer = new FileWriter(fileMap, true)) {
                String text = key + ":" + value + "\n";
                writer.write(text);
            } catch (IOException e) {
                throw new IOException(e);
            }
        }

        @Override
        public String remove(Object key) {
            return diskMap.remove(key);
        }

        @Override
        public void putAll(@NotNull Map<? extends String, ? extends String> m) {
            diskMap.putAll(m);
        }

        @Override
        public void clear() {
            diskMap.clear();
        }

        @NotNull
        @Override
        public Set<String> keySet() {
            return diskMap.keySet();
        }

        @NotNull
        @Override
        public Collection<String> values() {
            return diskMap.values();
        }

        @NotNull
        @Override
        public Set<Entry<String, String>> entrySet() {
            return diskMap.entrySet();
        }
    }
}
