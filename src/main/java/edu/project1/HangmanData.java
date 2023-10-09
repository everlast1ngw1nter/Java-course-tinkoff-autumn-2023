package edu.project1;

public class HangmanData {

    private HangmanData() {
    }

    static SessionInformation sessionInfo;

    final static int POSSIBLE_MISTAKES = 5;

    public static boolean checkGameStarted() {
        return !(sessionInfo == null);
    }

    public static void startGame() {
        sessionInfo = new SessionInfo(new WordInfo(new WordDict()));
    }

    public static void endGame() {
        sessionInfo = null;
    }

    public static boolean processLetter(char letter) {
        return sessionInfo.checkGuessedLetter(letter);
    }

    public static String checkGameResult() {
        if (sessionInfo.getMistakes() == POSSIBLE_MISTAKES) {
            endGame();
            return "You lost!";
        }
        if (!sessionInfo.getMask().contains("*")) {
            endGame();
            return "You won!";
        }
        return null;
    }

    public static int getAttemptsCount() {
        return POSSIBLE_MISTAKES - sessionInfo.getMistakes();
    }
}
