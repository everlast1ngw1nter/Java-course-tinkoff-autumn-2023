package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    public static List<LocalDate> findAllFridayThirteenThisYear(int year) {
        List<LocalDate> fridaysThirteens = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, 1).with(NEXT_FRIDAY_THIRTEEN);
        while (date.getYear() == year) {
            fridaysThirteens.add(date);
            date = date.with(NEXT_FRIDAY_THIRTEEN);
        }
        return fridaysThirteens;
    }

    @SuppressWarnings("MagicNumber")
    private static final TemporalAdjuster NEXT_FRIDAY_THIRTEEN = TemporalAdjusters.ofDateAdjuster(date -> {
        LocalDate next = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        if (date.getDayOfMonth() >= 13) {
            next = next.with(TemporalAdjusters.firstDayOfNextMonth());
        }
        next = next.withDayOfMonth(13);
        while (!next.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            next = next.plusMonths(1);
        }
        return next;
    });
}
