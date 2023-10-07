package edu.hw1;

public final class Task1 {
    private Task1() {
    }

    public static long minutesToSeconds(final String inputTime) {
        String[] parsedTime = inputTime.split(":");
        final int minuteInSeconds = 60;
        long minutes = Long.parseLong(parsedTime[0]);
        long seconds = Long.parseLong(parsedTime[1]);
        if (minutes < 0 | seconds < 0 | seconds >= minuteInSeconds) {
            return -1;
        }
        return minutes * minuteInSeconds + seconds;
    }
}
