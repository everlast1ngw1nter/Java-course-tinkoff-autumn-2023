package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    @Test
    void defaultConnectionManagerTest() {
        var manager = new Task3.DefaultConnectionManager();
        int faultyConnectionCounter = 0;
        int stableConnectionCounter = 0;
        for (int i = 0; i < 100; i++) {
            String connection = manager.getConnection().getClass().getName();
            if (connection.equals(Task3.FaultyConnection.class.getName())) {
                faultyConnectionCounter++;
            }
            if (connection.equals(Task3.StableConnection.class.getName())) {
                stableConnectionCounter++;
            }
        }
        assertThat(faultyConnectionCounter * stableConnectionCounter)
                .isNotEqualTo(0);
    }

    @Test
    void faultyConnectionManagerTest() {
        var manager = new Task3.FaultyConnectionManager();
        int controlInt = 10;
        int faultyConnectionCounter = 0;
        for (int i = 0; i < controlInt; i++) {
            String connection = manager.getConnection().getClass().getName();
            if (connection.equals(Task3.FaultyConnection.class.getName())) {
                faultyConnectionCounter++;
            }
        }
        assertThat(faultyConnectionCounter)
                .isEqualTo(controlInt);
    }
}
