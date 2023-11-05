package edu.hw5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Task1 {

    private Task1() {
    }

    private static final int MILLIS_IN_SECOND = 1000;

    private static final int SECONDS_IN_MINUTE = 60;

    private static final int MINUTES_IN_HOURS = 60;


    public static String getAverageSessionTime(List<String> sessionsTime)
            throws ParseException {
        if (sessionsTime.isEmpty()) {
            throw new IllegalArgumentException();
        }
        long totalTime = 0L;
        for (String session : sessionsTime) {
            var bounds = session.split(" - ");
            var timePattern = new SimpleDateFormat("yyyy-MM-dd, hh:mm", Locale.ENGLISH);
            Date startTime = timePattern.parse(bounds[0]);
            Date endTime = timePattern.parse(bounds[1]);
            long differenceMillis = endTime.getTime() - startTime.getTime();
            if (differenceMillis < 0) {
                throw new IllegalArgumentException();
            }
            totalTime += differenceMillis;
        }
        long averageTime = totalTime / sessionsTime.size();
        return convertMillisTohhmm(averageTime);
    }

    private static String convertMillisTohhmm(long millis) {
        int hours = (int) (millis / (MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOURS));
        int minutes = (int) (millis / (MILLIS_IN_SECOND * SECONDS_IN_MINUTE) - hours * MINUTES_IN_HOURS);
        return hours + "ч " + minutes + "м";
    }
}
