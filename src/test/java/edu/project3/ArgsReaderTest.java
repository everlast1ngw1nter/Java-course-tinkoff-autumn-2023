package edu.project3;

import edu.project3.reportmaker.ReportFormat;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ArgsReaderTest {

    @Test
    void getArgsInfoTest() {
        var args = new String[] {"--path", "logs/2023*", "--from",
                "2023-08-31", "--format", "adoc"};
        var reader = new ArgsReader(args).getArgsInfo();
        assertEquals(reader.stringPath(), "logs/2023*");
        assertEquals(reader.from(), LocalDate.of(2023,8,31));
        assertNull(reader.to());
        assertEquals(reader.reportFormat(), ReportFormat.ADOC);
    }
}
