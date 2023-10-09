package edu.project1;

import java.util.HashMap;
import java.util.Map;

public class ConsoleCommands {

    private ConsoleCommands() {
    }

    final public static Map<String, Runnable> COMMAND_MAP = new HashMap<>() {
        {
            put("/help", Main::getInfo);
            put("/start", HangmanData::startGame);
            put("/gg", HangmanData::endGame);
        }
    };
}
