package edu.hw1;

public final class Task8 {
    private Task8() {
    }

    public static boolean knightBoardCapture(final int[][] board)
            throws IllegalArgumentException {
        final int len = 8;
        isLengthCorrect(board, len);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
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
            if (inBounds(board, newI, newJ) && board[newI][newJ] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean inBounds(final int[][] board,
                                    final int i,
                                    final int j) {
        return !(board.length <= i | board.length <= j | i < 0 | j < 0);
    }

    private static void isLengthCorrect(final int[][] board, final int len)
            throws IllegalArgumentException {
        if (board.length != len) {
            throw new IllegalArgumentException();
        }
        for (int[] elem : board) {
            if (elem.length != len) {
                throw new IllegalArgumentException();
            }
        }
    }
}
