package edu.hw1;

public final class Task8 {
    private Task8() {
    }

    private final static int LEN = 8;

    public static boolean knightBoardCapture(final int[][] board)
            throws IllegalArgumentException {
        isLengthCorrect(board);
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                if (isKnightCapture(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isKnightCapture(final int[][] board,
                                           final int i,
                                           final int j) {
        final int[][] moves = new int[][] {
                {1, 2}, {2, 1}, {-1, 2}, {2, -1},
                {1, -2}, {-2, 1}, {-1, -2}, {-2, -1}
        };
        for (int[] move : moves) {
            int newI = i + move[0];
            int newJ = j + move[1];
            if (inBounds(newI, newJ) && board[newI][newJ] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean inBounds(final int i, final int j) {
        return !(LEN <= i | LEN <= j | i < 0 | j < 0);
    }

    private static void isLengthCorrect(final int[][] board)
            throws IllegalArgumentException {
        if (board.length != LEN) {
            throw new IllegalArgumentException();
        }
        for (int[] elem : board) {
            if (elem.length != LEN) {
                throw new IllegalArgumentException();
            }
        }
    }
}
