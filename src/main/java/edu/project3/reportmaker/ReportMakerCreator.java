package edu.project3.reportmaker;

import edu.project3.statistic.StatisticManager;

public class ReportMakerCreator {

    private ReportMakerCreator() {
    }


    public static ReportMaker create(ReportFormat reportFormat, StatisticManager manager) {
        return switch (reportFormat) {
            case MARKDOWN -> new MarkdownReportMaker(manager);
            case ADOC -> new AdocReportMaker(manager);
        };
    }
}
