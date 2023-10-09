package edu.project1;

import java.util.Scanner;

public class Main {

    final static Scanner IN = new Scanner(System.in);

    private Main() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) {
        System.out.println("Welcome to Hangman. Write /help to get info or write /start to start the game.");
        while (true) {
            if (HangmanData.checkGameStarted()) {
                System.out.println("Guess a letter: ");
            }
            String input = IN.next();
            if (ConsoleCommands.COMMAND_MAP.containsKey(input)) {
                ConsoleCommands.COMMAND_MAP.get(input).run();
            } else if (input.matches("[a-z]")) {
                if (HangmanData.checkGameStarted()) {
                    processLetter(input.charAt(0));
                } else {
                    System.out.println("You should start the game.");
                }
            } else {
                System.out.println("Incorrect input.");
            }
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void processLetter(char letter) {
        if (HangmanData.sessionInfo.getChars().contains(letter)) {
            System.out.println("This letter already been checked");
            return;
        }
        if (HangmanData.processLetter(letter)) {
            System.out.println("Hit!");
        } else {
            System.out.println("Missed...");
        }
        System.out.printf("Attempts left: %d%n", HangmanData.getAttemptsCount());
        System.out.printf("The word: %s%n", HangmanData.sessionInfo.getMask());
        String gameResult = HangmanData.checkGameResult();
        if (gameResult != null) {
            System.out.println(gameResult);
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void getInfo() {
        System.out.println("/start - start the game, /gg - give up, /help - info");
    }
}
