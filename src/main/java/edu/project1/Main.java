package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Scanner IN = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        LOGGER.info("Welcome to Hangman. Write /help to get info or write /start to start the game.");
        while (true) {
            if (HangmanData.isGameStarted()) {
                LOGGER.info("Guess a letter: ");
            }
            String input = IN.next();
            if (ConsoleCommands.COMMAND_MAP.containsKey(input)) {
                ConsoleCommands.COMMAND_MAP.get(input).run();
            } else if (input.matches("[a-z]")) {
                if (HangmanData.isGameStarted()) {
                    processLetter(input.charAt(0));
                } else {
                    LOGGER.info("You should start the game.");
                }
            } else {
                LOGGER.info("Incorrect input.");
            }
        }
    }

    public static void processLetter(char letter) {
        if (HangmanData.sessionInfo.getChars().contains(letter)) {
            LOGGER.info("This letter already been checked");
            return;
        }
        if (HangmanData.processLetter(letter)) {
            LOGGER.info("Hit!");
        } else {
            LOGGER.info("Missed...");
        }
        LOGGER.info(String.format("Attempts left: %d%n", HangmanData.getAttemptsCount()));
        LOGGER.info(String.format("The word: %s%n", HangmanData.sessionInfo.getMask()));
        String gameResult = HangmanData.checkGameResult();
        if (gameResult != null) {
            LOGGER.info(gameResult);
        }
    }

    public static void getInfo() {
        LOGGER.info("/start - start the game, /gg - give up, /help - info");
    }
}
