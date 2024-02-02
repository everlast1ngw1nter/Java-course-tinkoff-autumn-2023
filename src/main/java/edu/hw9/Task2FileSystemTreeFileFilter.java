package edu.hw9;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;

public class Task2FileSystemTreeFileFilter {

    private final Function<File, Boolean> filter;

    private final File startFile;

    public Task2FileSystemTreeFileFilter(File startFile, Function<File, Boolean> filter) {
        this.filter = filter;
        this.startFile = startFile;
    }

    public List<File> find() {
        var task = new RecursiveFileFilter(startFile);
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(task);
        }
    }


    private class RecursiveFileFilter extends RecursiveTask<List<File>> {

        private final File file;

        RecursiveFileFilter(File path) {
            this.file = path;
        }

        @Override
        protected List<File> compute() {
            if (file.isFile() && filter.apply(file)) {
                return List.of(file);
            }
            var filesList = file.listFiles();
            if (filesList == null) {
                return List.of();
            }
            var forks = new ArrayList<ForkJoinTask<List<File>>>();
            for (var nextFile : filesList) {
                forks.add(new RecursiveFileFilter(nextFile).fork());
            }
            return new ArrayList<>(forks
                    .stream()
                    .map(ForkJoinTask::join)
                    .flatMap(List::stream)
                    .toList());
        }
    }
}
