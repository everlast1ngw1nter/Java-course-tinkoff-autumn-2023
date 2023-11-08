package edu.hw5;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

public class Task3 {
    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string)
            throws ParseException {
        var dashParser = new DashDateParser();
        var slashParser = new SlashDateParser();
        var daysAgoParser = new DaysAgoDateParser();
        var wordParser = new WordDateParser();
        dashParser.setNextParser(slashParser);
        slashParser.setNextParser(daysAgoParser);
        daysAgoParser.setNextParser(wordParser);
        return dashParser.parseDate(string);
    }

    private static abstract class DateParser {
        DateParser nextParser;

        public void setNextParser(DateParser parser) {
            this.nextParser = parser;
        }

        abstract Optional<LocalDate> parseDate(String date)
                throws ParseException;
    }

    private static class DashDateParser extends DateParser {
        private static final Pattern PATTERN = Pattern.compile("^\\d+-\\d{1,2}-\\d{1,2}$");

        @Override
        public Optional<LocalDate> parseDate(String date)
                throws ParseException {
            var matcher = PATTERN.matcher(date);
            if (matcher.find()) {
                var formatter = DateTimeFormatter.ofPattern("y-M-d");
                var localDate = LocalDate.parse(date, formatter);
                return Optional.of(localDate);
            }
            if (nextParser != null) {
                return nextParser.parseDate(date);
            }
            return Optional.empty();
        }
    }

    private static class SlashDateParser extends DateParser {
        private static final Pattern PATTERN = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d+$");

        @Override
        public Optional<LocalDate> parseDate(String date)
                throws ParseException {
            var matcher = PATTERN.matcher(date);
            if (matcher.find()) {
                var formatter = DateTimeFormatter.ofPattern("d/M/y");
                var localDate = LocalDate.parse(date, formatter);
                return Optional.of(localDate);
            }
            if (nextParser != null) {
                return nextParser.parseDate(date);
            }
            return Optional.empty();
        }
    }

    private static class DaysAgoDateParser extends DateParser {
        private static final Pattern PATTERN = Pattern.compile("^\\d+ days? ago$");

        @Override
        public Optional<LocalDate> parseDate(String date)
                throws ParseException {
            var matcher = PATTERN.matcher(date);
            if (matcher.find()) {
                var resultDate = LocalDate.now().plusDays(Long.parseLong(date.split(" ")[0]));
                return Optional.of(resultDate);
            }
            if (nextParser != null) {
                return nextParser.parseDate(date);
            }
            return Optional.empty();
        }
    }

    private static class WordDateParser extends DateParser {
        @Override
        public Optional<LocalDate> parseDate(String date)
                throws ParseException {
            Integer days = switch (date) {
                case "tomorrow" -> 1;
                case "today" -> 0;
                case "yesterday" -> -1;
                default -> null;
            };
            if (days != null) {
                var resultDate = LocalDate.now().plusDays(days.longValue());
                return Optional.of(resultDate);
            }
            if (nextParser != null) {
                return nextParser.parseDate(date);
            }
            return Optional.empty();
        }
    }
}
