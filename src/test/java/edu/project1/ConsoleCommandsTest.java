package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleCommandsTest {

    @Test
    void startCommandTest() {
        ConsoleCommands.COMMAND_MAP.get("/start").run();
        boolean isGameStarted = HangmanData.isGameStarted();
        assertThat(isGameStarted).isTrue();
    }

    @Test
    void startAndEndCommandTest() {
        ConsoleCommands.COMMAND_MAP.get("/start").run();
        ConsoleCommands.COMMAND_MAP.get("/gg").run();
        boolean isGameStarted = HangmanData.isGameStarted();
        assertThat(isGameStarted).isFalse();
    }

    @Test
    void endCommandTest() {
        ConsoleCommands.COMMAND_MAP.get("/gg").run();
        boolean isGameStarted = HangmanData.isGameStarted();
        assertThat(isGameStarted).isFalse();
    }

    @Test
    void helpCommandTest() {
        ConsoleCommands.COMMAND_MAP.get("/start").run();
        ConsoleCommands.COMMAND_MAP.get("/gg").run();
        ConsoleCommands.COMMAND_MAP.get("/help").run();
        boolean isGameStarted = HangmanData.isGameStarted();
        assertThat(isGameStarted).isFalse();
    }

    @Test
    void complexCommandTest() {
        ConsoleCommands.COMMAND_MAP.get("/start").run();
        ConsoleCommands.COMMAND_MAP.get("/gg").run();
        ConsoleCommands.COMMAND_MAP.get("/start").run();
        ConsoleCommands.COMMAND_MAP.get("/start").run();
        ConsoleCommands.COMMAND_MAP.get("/gg").run();
        ConsoleCommands.COMMAND_MAP.get("/start").run();
        ConsoleCommands.COMMAND_MAP.get("/help").run();
        boolean isGameStarted = HangmanData.isGameStarted();
        assertThat(isGameStarted).isTrue();
    }
}
