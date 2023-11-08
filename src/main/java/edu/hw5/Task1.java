package edu.hw5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Task1 {

    private Task1() {
    }

    private static final SimpleDateFormat TIME_PATTERN =
            new SimpleDateFormat("yyyy-MM-dd, hh:mm", Locale.ENGLISH);

    public static String getAverageSessionTime(List<String> sessionsTime)
            throws ParseException {
        if (sessionsTime.isEmpty()) {
            throw new IllegalArgumentException("Empty sessionTime list");
        }
        Duration totalTime = Duration.ZERO;
        for (String session : sessionsTime) {
            var bounds = session.split(" - ");
            Date startTime = TIME_PATTERN.parse(bounds[0]);
            Date endTime = TIME_PATTERN.parse(bounds[1]);
            Duration currentDuration = Duration.between(startTime.toInstant(), endTime.toInstant());
            if (currentDuration.isNegative()) {
                throw new IllegalArgumentException("Negative time interval");
            }
            totalTime = totalTime.plus(currentDuration);
        }
        Duration averageTime = totalTime.dividedBy(sessionsTime.size());
        return averageTime.toHours() + "ч " + averageTime.toMinutesPart() + "м";
    }
}
