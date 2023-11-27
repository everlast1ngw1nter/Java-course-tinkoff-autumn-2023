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
        var dashParser =
                new DashDateParser(
                new SlashDateParser(
                        new DaysAgoDateParser(
                                new WordDateParser(null))));
        return dashParser.parseDate(string);
    }

    private static abstract class DateParser {
        DateParser nextParser;

        abstract Optional<LocalDate> parseDate(String date)
                throws ParseException;
    }

    private static class DashDateParser extends DateParser {
        private static final Pattern PATTERN = Pattern.compile("^\\d+-\\d{1,2}-\\d{1,2}$");

        public DashDateParser(DateParser nextParser) {
            this.nextParser = nextParser;
        }

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

        public SlashDateParser(DateParser nextParser) {
            this.nextParser = nextParser;
        }

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

        public DaysAgoDateParser(DateParser nextParser) {
            this.nextParser = nextParser;
        }

        @Override
        public Optional<LocalDate> parseDate(String date)
                throws ParseException {
            var matcher = PATTERN.matcher(date);
            if (matcher.find()) {
                var resultDate = LocalDate.now().minusDays(Long.parseLong(date.split(" ")[0]));
                return Optional.of(resultDate);
            }
            if (nextParser != null) {
                return nextParser.parseDate(date);
            }
            return Optional.empty();
        }
    }

    private static class WordDateParser extends DateParser {

        public WordDateParser(DateParser nextParser) {
            this.nextParser = nextParser;
        }

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
