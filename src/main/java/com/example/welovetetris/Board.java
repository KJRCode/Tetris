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
    //Pieces p = makePiece();
    private Pieces p;

    /**
     * The Board constructor
     *
     * initializes class variables and sets all the squares in board and currentBoard to false
     */
    public Board() {
        board = new boolean[NUM_ROWS][NUM_COLS];
        currentBoard = new boolean[NUM_ROWS][NUM_COLS];

        //get random piece here instead of oneBlock
        p = makePiece();

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                board[i][j] = false;
                currentBoard[i][j] = false;
            }
        }

        numLinesCleared = 0;
        totalPoints = 0;
    }

    public Pieces getPiece() {
       return p;
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
        if ((y < NUM_ROWS-p.height) && !lowestPiece()) {
            positionChange(0, 1, currentBoard, true);
        } else {
            pieceLanded = true;
            positionChange(0,0,board, true);
            positionChange(0,0,currentBoard, true);

            x = 10;
            y = 0;
            p = makePiece();
        }
    }

    /**
     * moves the current piece left by one column
     */
    public void moveLeft() {
        if (x > 0) {
            if(!leftmostPiece()) {
                positionChange(-1, 0, currentBoard, true);
            }
        }
    }

    /**
     * moves the current piece right by one column
     */
    public void moveRight() {
        if (x < NUM_COLS- p.width) { //-2 is number of squares after the first one
            if (!rightmostPiece()) {
                positionChange(1, 0, currentBoard, true);
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
                if (board[r][c] || currentBoard[r][c]) {
                    frame.append("X");
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
        for (int c = 0; c < p.width; c++) {
            for (int r = 0; r < p.height; r++) {
                    if (board[y + p.height][x + c] || currentBoard[y + p.height][x + c]) {
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
                if (board[y][x + p.width] || currentBoard[y][x + p.width]) {
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
     */
    public void positionChange(int dx, int dy, boolean[][] b, boolean tf) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(p.occupies(i, j)) {
                    b[j+y][i+x] = false;
                }
            }
        }

        y += dy;
        x += dx;

        if (tf) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (p.occupies(i, j)) {
                        b[j + y][i + x] = true;
                    }
                }
            }
        }
    }

    /**
     * makes a new random Piece
     * @return a random Piece type, such as a JBlock or OBlock
     */
    public Pieces makePiece(){
        //Pieces oneB = new OneBlock();
        Pieces jB = new JBlock();
        Pieces iB = new IBlock();
        Pieces lB = new LBlock();
        Pieces oB = new OBlock();
        Pieces sB = new SBlock();
        Pieces tB = new TBlock();
        Pieces zB = new ZBlock();

        //randPieces.put(2, oneB);
        randPieces.put(1, jB);
        randPieces.put(2, iB);
        randPieces.put(3, lB);
        randPieces.put(4, oB);
        randPieces.put(5, sB);
        randPieces.put(6, tB);
        randPieces.put(7, zB);


        return randPieces.get(rand.nextInt(1, 8));
    }
}