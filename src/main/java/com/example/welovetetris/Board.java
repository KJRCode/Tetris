package com.example.welovetetris;
import java.util.Map;


/**
 * @author Winona Wherley
 * @author Karly Ripper
 *
 * this class creates an empty Board which we use in HelloApplication to create our frames
 * it also keeps track of how many lines have been cleared and how many points the player
 * has acquired
 */
public class Board {
    //creating and initializing private variables
    final int NUM_ROWS = 20;
    final int NUM_COLS = 20;
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

    public boolean[][] getArray() {
        return board;
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