package com.example.welovetetris;

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
   public boolean[][] board;
    public boolean[][] currentBoard;
    private int x = 0;
    private int y = 0;
    private boolean pieceLanded = false;
    private char current = 'C';
    private Pieces oneB = new OneBlock();

    public Board() {
        board = new boolean[NUM_ROWS][NUM_COLS];
        currentBoard = new boolean[NUM_ROWS][NUM_COLS];

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                board[i][j] = false;
                currentBoard[i][j] = false;
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
        boolean[][] temp = new boolean[NUM_ROWS][NUM_COLS];

        for (int i = 0; i < NUM_COLS; i++) {
            if (!board[rowNum][i] && !currentBoard[rowNum][i]) {
                return;
            }
        }

        for (int i = 0; i < NUM_COLS; i++) {
            board[rowNum][i] = false;
            currentBoard[rowNum][i] = false;
        }
        numLinesCleared++;

        for (int i = rowNum+1; i >= 2; i--) {
            for (int j = 0; j < NUM_COLS; j++) {
                temp[i-1][j] = board[i-2][j];
                temp[i-1][j] = currentBoard[i-2][j];
            }
        }

        for (int i = rowNum; i >= 0; i--) {
            for (int j = 0; j < NUM_COLS; j++) {
                board[i][j] = temp[i][j];
                currentBoard[i][j] = temp[i][j];
            }
        }
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
            if (!lowestPiece()) {
                //pieceChange(currentBoard, 1, 0);
                currentBoard[y][x] = false;
                currentBoard[y][x+1] = false;
                currentBoard[y][x+2] =  false;
                y += 1;
                currentBoard[y][x] = true;
                currentBoard[y][x+1] = true;
                currentBoard[y][x+2] =  true;

            } else {
                pieceLanded = true;
                board[y][x] = true;
                board[y][x+1] = true;
                board[y][x+2] =  true;
                currentBoard[y][x] = true;
                currentBoard[y][x+1] = true;
                currentBoard[y][x+2] =  true;
                x = 10;
                y = 0;
            }
        }
        else {
            pieceLanded = true;
            board[y][x] = true;
            board[y][x+1] = true;
            board[y][x+2] =  true;
            currentBoard[y][x] = true;
            currentBoard[y][x+1] = true;
            currentBoard[y][x+2] =  true;
            x = 10;
            y = 0;
        }
    }
    public void moveLeft() {
        if (x > 0) {
            if(!leftmostPiece()) {
                //pieceChange(currentBoard, 0, -1);
                currentBoard[y][x] = false;
                currentBoard[y][x+1] = false;
                currentBoard[y][x+2] =  false;
                x -= 1;
                currentBoard[y][x] = true;
                currentBoard[y][x+1] = true;
                currentBoard[y][x+2] =  true;
            }
        }
    }
    public void moveRight() {
        if (x < NUM_COLS-1-2) { //-2 is number of squares after the first one
            if (!rightmostPiece()) {
                //pieceChange(currentBoard, 0, 1);
                currentBoard[y][x] = false;
                currentBoard[y][x+1] = false;
                currentBoard[y][x+2] =  false;
                x += 1;
                currentBoard[y][x] = true;
                currentBoard[y][x+1] = true;
                currentBoard[y][x+2] =  true;
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
                if (board[r][c] || currentBoard[r][c]) {//we stop in the upper left corner and draw the whole piece
                    frame.append("C");
                } else {
                    frame.append(' ');
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

    public boolean lowestPiece() {
        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 3; r++) {
                    if (board[y + 1][x + c] || currentBoard[y + 1][x + c]) {
                        return true;
                    }
            }
        }
        return false;
    }

    public boolean rightmostPiece() {
        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 3; r++) {
                if (board[y + r][x + 3] || currentBoard[y + r][x + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean leftmostPiece() {
        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 3; r++) {
                if (board[y + r][x - 1] || currentBoard[y + r][x - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}