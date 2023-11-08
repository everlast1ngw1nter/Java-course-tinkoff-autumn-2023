package edu.hw5;

import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task1Test {

    @Test
    void getAverageSessionTimeTest()
            throws ParseException {
        List<String> sessions = new ArrayList<>();
        sessions.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        sessions.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        var avg = Task1.getAverageSessionTime(sessions);
        assertThat(avg)
                .isEqualTo("3ч 40м");
    }

    @Test
    void negativeTimeTest() {
        List<String> sessions = new ArrayList<>();
        sessions.add("2022-03-12, 20:20 - 2022-03-12, 20:10");
        sessions.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        assertThrows(IllegalArgumentException.class, () -> Task1.getAverageSessionTime(sessions));
    }

    @Test
    void emptyDataTimeTest() {
        List<String> sessions = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> Task1.getAverageSessionTime(sessions));
    }
}