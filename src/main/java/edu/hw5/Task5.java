package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private Task5() {
    }

    private static final Pattern LICENSE_PLATE_NUMBER_PATTERN =
            Pattern.compile("^[А-Я]\\d{3}[А-Я]{2}\\d{3}$");

    public static boolean isLicensePlateNumberCorrect(String licensePlateNumber) {
        return LICENSE_PLATE_NUMBER_PATTERN.matcher(licensePlateNumber).find();
    }
}
