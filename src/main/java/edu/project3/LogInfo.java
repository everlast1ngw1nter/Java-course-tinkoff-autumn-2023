package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

public record LogInfo(String remoteAddress, String remoteUser,
                      OffsetDateTime localTime, String request,
                      Integer status, Long bodyBytesSent,
                      String httpReferer, String httpUserAgent) {
    private static final Pattern pattern =
            Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - (.*) \\[(\\d{2}/[A-Z][a-z]{2,8}/\\d{4}:\\d{2}:\\d{2}:\\d{2} \\+\\d{4})] \"(.*)\" (\\d{3}) (.*) \"(.*)\" \"(.*)\"");

    private static OffsetDateTime parseOffsetDateTime(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        return OffsetDateTime.parse(stringDate, formatter);
    }

    public static LogInfo create(String log) {
        var matcher = pattern.matcher(log);
        if (!matcher.find()) {
            return null;
        }
        var remoteAddress = matcher.group(1);
        var remoteUser = matcher.group(2);
        var localTime = parseOffsetDateTime(matcher.group(3));
        var request = matcher.group(4);
        var status = Integer.parseInt(matcher.group(5));
        var bodyBytesSent = Long.parseLong(matcher.group(6));
        var httpReferer = matcher.group(7);
        var httpUserAgent = matcher.group(8);
        return new LogInfo(remoteAddress, remoteUser,
                localTime, request,
                status, bodyBytesSent,
                httpReferer, httpUserAgent);
    }
}