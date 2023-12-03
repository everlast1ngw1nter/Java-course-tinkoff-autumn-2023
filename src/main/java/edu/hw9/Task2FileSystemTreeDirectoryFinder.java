package edu.hw9;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Task2FileSystemTreeDirectoryFinder {

    private final int filesInDirectory;

    private final File startFile;

    public Task2FileSystemTreeDirectoryFinder(File startFile, int filesInDirectory) {
        this.filesInDirectory = filesInDirectory;
        this.startFile = startFile;
    }

    public List<File> find() {
        var task = new RecursiveDirectoryFinder(startFile);
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(task);
        }
    }


    private class RecursiveDirectoryFinder extends RecursiveTask<List<File>> {

        private final File file;

        public RecursiveDirectoryFinder(File path) {
            this.file = path;
        }

        @Override
        protected List<File> compute() {
            if (file.isFile() || file.listFiles() == null) {
                return List.of();
            }

            var forks = new ArrayList<ForkJoinTask<List<File>>>();
            var filesList = file.listFiles();
            for (var nextFile : filesList) {
                forks.add(new RecursiveDirectoryFinder(nextFile).fork());
            }
            var found = new ArrayList<>(forks
                    .stream()
                    .map(ForkJoinTask::join)
                    .flatMap(List::stream)
                    .toList());
            if (filesList.length > filesInDirectory) {
                found.add(file);
            }
            return found;
        }
    }
}
