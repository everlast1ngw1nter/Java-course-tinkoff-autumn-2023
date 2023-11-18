package edu.project3;

import edu.project3.reportmaker.ReportFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ArgsReader {

    private final String[] args;

    public ArgsReader(String[] args) {
        this.args = args;
    }

    public ArgsInfo getArgsInfo() {
        String stringPath = null;
        LocalDate from = null;
        LocalDate to = null;
        var reportFormat = ReportFormat.MARKDOWN;
        for (var i = 0; i < args.length; i = i + 2) {
            switch (args[i]) {
                case "--path" -> stringPath = args[i + 1];
                case "--from" -> from = LocalDate.parse(args[i + 1], DateTimeFormatter.ISO_LOCAL_DATE);
                case "--to" -> to = LocalDate.parse(args[i + 1], DateTimeFormatter.ISO_LOCAL_DATE);
                case "--format" -> reportFormat = getReportFormat(args[i + 1]);
            }
        }
        return new ArgsInfo(stringPath, from, to, reportFormat);
    }

    private static ReportFormat getReportFormat(String stringFormat) {
        return switch (stringFormat) {
            case "adoc" -> ReportFormat.ADOC;
            default -> ReportFormat.MARKDOWN;
        };
    }

    public record ArgsInfo(String stringPath, LocalDate from,
                           LocalDate to, ReportFormat reportFormat) {
    }
}

