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
    private int x = 0;
    private int y = 0;
    private boolean pieceLanded = false;
    private char current = 'C';
    private Pieces oneB = new OneBlock();

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

    public int getX() { return x;}
    public int getY() { return y;}

    public boolean[][] getArray() {
        return board;
    }

    public void clearRow(int rowNum) {
        int counter = 0;
        boolean[][] temp = new boolean[NUM_ROWS][NUM_COLS];

        for (int i = 0; i < NUM_COLS; i++) {
            if (!board[rowNum][i]) {
                return;
            }
        }

        for (int i = 0; i < NUM_COLS; i++) {
            board[rowNum][i] = false;
        }
        numLinesCleared++;

        for (int i = rowNum+1; i >= 2; i--) {
            for (int j = 0; j < NUM_COLS; j++) {
                temp[i-1][j] = board[i-2][j];
            }
        }

        for (int i = rowNum; i >= 0; i--) {
            for (int j = 0; j < NUM_COLS; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }

    public boolean rowEmpty(int num) {
        return false;
    }

    public boolean spaceEmpty(int row, int col) {
        return false;
    }

    public boolean topRowIsEmpty() {
        for (int i = 0; i < NUM_COLS; i++) {
            if (board[0][i]) {
                return false;
            }
        }
        return  true;
    }
    public void moveDown() {
        if (y < NUM_ROWS-1) {
            if (!board[y+1][x]) {
                y += 1;
            } else {
                pieceLanded = true;
                board[y][x] = true;
                x = 10;
                y = 0;
            }
        }
        else {
            pieceLanded = true;
            board[y][x] = true;
            x = 10;
            y = 0;
        }
    }
    public void moveLeft() {
        if (x > 0) {
            if(!board[y][x-1]) {
                x -= 1;
            }
        }
    }

    public void moveRight() {
        if (x < NUM_COLS-1) {
            if(!board[y][x+1]) {
                x += 1;
            }
        }
    }
    public void updateScore() {

    }

    public String makeFrame() {
        StringBuilder frame = new StringBuilder();
        frame.append("T E T R I S\n");
        frame.append("-".repeat(NUM_COLS));

        // add each row of the board

        for (int r = 0; r < NUM_ROWS; r++) {
            frame.append('\n');
            // add a left border
            frame.append('|');
            // fill in this row
            for (int c = 0; c < NUM_COLS; c++) {
                //adding landed pieces
                if (board[r][c]) {//we stop in the upper left corner and draw the whole piece
                    addLandedPieces(frame);
                    /*
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {//can we do it without the for loops and just draw a letter at a time instead of drawing a group at a time?
                            if(oneB.occupies(i, j)) {
                                frame.append('F');
                            }
                        }
                    }

                     */
                    //I think that we need to be replacing things, and we are adding them instead
                    //we aren't adding extra spaces, we are adding extra letters to a board with the same amount of spaces as before
                    //so a line that used to have 19 spaces plus a letter now has 19 spaces plus 2 letters
                    //thus, it looks like spaces have been added, but really we just need to replace spaces with letters, get it?

                    //frame.append('F');
                } else {
                    //moving piece currently being played with
                    if (r == y && c == x) {
                        /*
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if(oneB.occupies(i, j)) {
                                    frame.append(current);
                                }
                            }
                        }

                         */
                        addCurrentPiece(frame);
                    }
                    else {
                        frame.append(' ');
                    }
                }
            }
            // add a right border
            frame.append('|');
        }
        // add a bottom border
        frame.append('\n');
        frame.append("-".repeat(NUM_COLS));
        return frame.toString();
    }

    public void addLandedPieces(StringBuilder frame) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {//can we do it without the for loops and just draw a letter at a time instead of drawing a group at a time?
                if(oneB.occupies(i, j)) {
                    frame.append('F');
                }
            }
        }
    }

    public void addCurrentPiece(StringBuilder frame) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(oneB.occupies(i, j)) {
                    frame.append(current);
                }
            }
        }
    }
}