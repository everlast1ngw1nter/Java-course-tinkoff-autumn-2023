package edu.project3;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInfoTest {
    @Test
    void createLogInfoTest() {
        var logInfo = LogInfo.create("93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0"
                + " \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"");
        assertEquals(logInfo.remoteAddress(), "93.180.71.3");
        assertEquals(logInfo.remoteUser(), "-");
        assertEquals(logInfo.localTime(), OffsetDateTime.of(
                LocalDate.of(2015,5,17),
                LocalTime.of(8,5,32),
                ZoneOffset.ofHours(0)));
        assertEquals(logInfo.status(), 304);
        assertEquals(logInfo.bodyBytesSent(), 0);
        assertEquals(logInfo.httpReferer(), "-");
        assertEquals(logInfo.httpUserAgent(), "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)");
    }
}
