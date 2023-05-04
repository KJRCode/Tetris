package com.example.welovetetris;

public class Board {
    final int NUM_ROWS = 20;
    final int NUM_COLS = 10;
    int numLinesCleared;
    int totalPoints;
    boolean[][] board;

    public Board() {
        board = new boolean[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                board[i][j] = false;
            }
        }
        numLinesCleared = 0;
        totalPoints = 0;
    }

    public void clearRow(int rowNum) {
        int counter = 0;
        for (int i = 0; i < NUM_COLS; i++) {
            if (!board[rowNum][i]) {
                return;
            }
        }

        for (int i = 0; i < NUM_COLS; i++) {
            board[rowNum][i] = false;
        }

        numLinesCleared++;
    }

    public void moveRowsDown() {

    }

    public boolean rowFull(int num) {
        return false;
    }

    public boolean rowEmpty(int num) {
        return false;
    }

    public boolean spaceEmpty(int row, int col) {
        return false;
    }

    public boolean topRowIsntEmpty() {
        return false;
    }

    public void updateScore() {

    }
}
