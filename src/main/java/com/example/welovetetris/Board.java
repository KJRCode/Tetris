package com.example.welovetetris;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Winona Wherley
 * @author Karly Ripper
 *
 * this class creates an empty Board which we use in HelloApplication to create our frames
 * it also keeps track of how many lines have been cleared and how many points the player
 * has acquired, as well as has methods for moving the pieces right, left, and down
 */
public class Board {
    //creating and initializing class variables
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

    //setting up the Map of pieces
    private Map <Integer, Pieces> randPieces = new HashMap<>();
    private Random rand = new Random();

    //can delete this later
    //Pieces piece = new OneBlock();
    Pieces p = makePiece();

    /**
     * The Board constructor
     *
     * initializes class variables and sets all the squares in board and currentBoard to false
     */
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

    /**
     * @return boolean[][] board
     */
    public boolean[][] getArray() {
        return board;
    }

    /**
     * checks if a row rowNum is full and if it is it empties the row and moves all
     * the rows above it down one
     * @param rowNum the row being checked if it's full
     */
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

    /**
     * checks if the top row has anything in it to see if the game should end
     * @return true if the row is empty and false otherwise
     */
    public boolean topRowIsEmpty() {
        for (int i = 0; i < NUM_COLS; i++) {
            if (board[0][i]) {
                return false;
            }
        }
        return  true;
    }

    /**
     * Moves the current piece down by one row
     */
    public void moveDown() {
        if (y < NUM_ROWS-1) {
            if (!lowestPiece()) {
                currentBoard[y][x] = false;
                currentBoard[y][x+1] = false;
                currentBoard[y][x+2] =  false;
                currentBoard[y][x+3] =  false;
                y += 1;
                currentBoard[y][x] = true;
                currentBoard[y][x+1] = true;
                currentBoard[y][x+2] =  true;
                currentBoard[y][x+3] =  true;

                //positionChange(0, 1, x, y);
            } else {
                pieceLanded = true;
                board[y][x] = true;
                board[y][x+1] = true;
                board[y][x+2] =  true;
                board[y][x+3] =  true;
                currentBoard[y][x] = true;
                currentBoard[y][x+1] = true;
                currentBoard[y][x+2] =  true;
                currentBoard[y][x+3] =  true;
                x = 10;
                y = 0;
            }
        }
        else {
            pieceLanded = true;
            board[y][x] = true;
            board[y][x+1] = true;
            board[y][x+2] =  true;
            board[y][x+3] =  true;
            currentBoard[y][x] = true;
            currentBoard[y][x+1] = true;
            currentBoard[y][x+2] =  true;
            currentBoard[y][x+3] =  true;
            x = 10;
            y = 0;
        }
    }

    /**
     * moves the current piece left by one column
     */
    public void moveLeft() {
        if (x > 0) {
            if(!leftmostPiece()) {
                //pieceChange(currentBoard, 0, -1);
                currentBoard[y][x] = false;
                currentBoard[y][x+1] = false;
                currentBoard[y][x+2] =  false;
                currentBoard[y][x+3] =  false;
                x -= 1;
                currentBoard[y][x] = true;
                currentBoard[y][x+1] = true;
                currentBoard[y][x+2] =  true;
                currentBoard[y][x+3] =  true;
            }
        }
    }

    /**
     * moves the current piece right by one column
     */
    public void moveRight() {
        if (x < NUM_COLS-1-2) { //-2 is number of squares after the first one
            if (!rightmostPiece()) {
                //pieceChange(currentBoard, 0, 1);
                currentBoard[y][x] = false;
                currentBoard[y][x+1] = false;
                currentBoard[y][x+2] =  false;
                currentBoard[y][x+3] =  false;
                x += 1;
                currentBoard[y][x] = true;
                currentBoard[y][x+1] = true;
                currentBoard[y][x+2] =  true;
                currentBoard[y][x+3] =  true;
            }
        }
    }

    /**
     * updates the player's score
     */
    public void updateScore() {

    }

    /**
     * makes a new frame which has all of the pieces on it.  It's used in the HelloApplication
     * class to make the pieces move
     * @return a String of the board
     */
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

    /**
     * checks if the piece currently being played with can move lower without hitting any other pieces
     * @return true if there's a piece below the current piece and false otherwise
     */
    public boolean lowestPiece() {
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                    if (board[y + 1][x + c] || currentBoard[y + 1][x + c]) {
                        return true;
                    }
            }
        }
        return false;
    }

    /**
     * checks if the piece currently being played with can move right without hitting any other pieces
     * @return true if there's a piece to the right of the current piece and false otherwise
     */
    public boolean rightmostPiece() {
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                if (board[y][x + 4] || currentBoard[y][x + 4]) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * checks if the piece currently being played with can move left without hitting any other pieces
     * @return true if there's a piece to the left of the current piece and false otherwise
     */
    public boolean leftmostPiece() {
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                if (board[y][x - 1] || currentBoard[y][x - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * moves the piece from x, y to a new location
     * @param dx the amount moved right/left
     * @param dy the amount moved up/down
     * @param x the x coordinate of the current top left corner of the piece
     * @param y the y coordinate of the current top left corner of the piece
     */
    public void positionChange(int dx, int dy, int x, int y) {
        /*
                currentBoard[y][x] = false;
                currentBoard[y][x+1] = false;
                currentBoard[y][x+2] =  false;
                y += 1;
                currentBoard[y][x] = true;
                currentBoard[y][x+1] = true;
                currentBoard[y][x+2] =  true;
         */


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(p.occupies(i, j)) {
                    currentBoard[j+y][i+x] = false;
                }
            }
        }

        y += dy;
        x += dx;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(p.occupies(i, j)) {
                    //board[i+y][j+x] = true;
                    currentBoard[j+y][i+x] = true;
                }
            }
        }
    }

    /**
     * makes a new random Piece
     * @return a random Piece type, such as a JBlock or OBlock
     */
    public Pieces makePiece(){
        Pieces oneB = new OneBlock();
        Pieces jB = new JBlock();

        //randPieces.put(2, oneB);
        randPieces.put(1, jB);

        return randPieces.get(rand.nextInt(1)+1);
    }
}