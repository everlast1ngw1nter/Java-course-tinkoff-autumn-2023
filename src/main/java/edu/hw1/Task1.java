package edu.hw1;

public final class Task1 {
    private Task1() {
    }

    private final static int MINUTE_IN_SECONDS = 60;

    public static long minutesToSeconds(final String inputTime) {
        if (!inputTime.matches("^\\d+:\\d{2}$")) {
            return -1;
        }
        String[] parsedTime = inputTime.split(":");
        long minutes = Long.parseLong(parsedTime[0]);
        long seconds = Long.parseLong(parsedTime[1]);
        if (minutes < 0 | seconds < 0 | seconds >= MINUTE_IN_SECONDS) {
            return -1;
        }
        return minutes * MINUTE_IN_SECONDS + seconds;
    }
}
