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
    public boolean[][] board;
    public boolean[][] currentBoard;
    private int x = 10;
    private int y = 0;

    //setting up the Map of pieces
    private Map <Integer, Pieces> randPieces = new HashMap<>();
    private Random rand = new Random();
    private Pieces p;

    /**
     * The Board constructor
     *
     * initializes class variables and sets all the squares in board and currentBoard to false
     */
    public Board() {
        //intializing boolean[][]s for the board
        board = new boolean[NUM_ROWS][NUM_COLS];
        currentBoard = new boolean[NUM_ROWS][NUM_COLS];

        //gets a random piece shape
        p = makePiece();

        //sets the boards to false initially
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                board[i][j] = false;
                currentBoard[i][j] = false;
            }
        }
    }

    /**
     *gets the piece currently being used
     * @return Pieces p
     */
    public Pieces getPiece() {
       return p;
    }

    /**
     * checks if a row rowNum is full and if it is it empties the row and moves all
     * the rows above it down one
     * @param rowNum the row being checked if it's full
     */
    public void clearRow(int rowNum) {
        int counter = 0;
        boolean[][] temp = new boolean[NUM_ROWS][NUM_COLS];

        //checks if the row is full and returns if it isn't
        for (int i = 0; i < NUM_COLS; i++) {
            if (!board[rowNum][i] && !currentBoard[rowNum][i]) {
                return;
            }
        }

        //sets all the squares in the row to false
        for (int i = 0; i < NUM_COLS; i++) {
            board[rowNum][i] = false;
            currentBoard[rowNum][i] = false;
        }

        //sets all the squares in the temp board to the squares in the row below
        //in board and currentBoard
        for (int i = rowNum+1; i >= 2; i--) {
            for (int j = 0; j < NUM_COLS; j++) {
                temp[i-1][j] = board[i-2][j];
                temp[i-1][j] = currentBoard[i-2][j];
            }
        }

        //sets board and currentBoard equal to temp and therefore moves the row down
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
            //if any square in the row isn't false then the row isn't empty
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
        //moves the piece down if it's not on the bottom of the board
        //and there's not a piece directly below it
        if ((y < NUM_ROWS-p.height) && !lowestPiece()) {
            positionChange(0, 1, currentBoard, true);
        } else {
            //lands the piece by setting board and currentBoard to true
            positionChange(0,0,board, true);
            positionChange(0,0,currentBoard, true);

            //puts a new random piece at the top of the board
            x = 10;
            y = 0;

            //checks if any rows are empty and clears them if they are
            for (int i = 0; i < NUM_ROWS; i++) {
                clearRow(i);
            }

            //makes a new piece at the top of the board
            p = makePiece();
        }
    }

    /**
     * moves the current piece left by one column
     */
    public void moveLeft() {
        //makes sure piece isn't against the left border
        if (x > 0) {
            //checks if there's a piece directly to the left of the current piece
            if(!leftmostPiece()) {
                positionChange(-1, 0, currentBoard, true);
            }
        }
    }

    /**
     * moves the current piece right by one column
     */
    public void moveRight() {
        //makes sure piece isn't against the right border
        if (x < NUM_COLS- p.width) {
            //checks if there's a piece directly to the right of the current piece
            if (!rightmostPiece()) {
                positionChange(1, 0, currentBoard, true);
            }
        }
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
                //add an X if the square is true (which means that a piece is there)
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
        boolean pieceUnder = false;

        //checks if a piece can move down without hitting another piece
        for (int c = 0; c < p.width; c++) {
            for (int r = 0; r < p.height; r++) {
                if (p.occupies(c, r)){
                    if (!p.occupies(c, r + 1)){
                        if (board[y + r + 1][x +c]){
                            pieceUnder = true;
                        }
                    }
                }
            }
        }


       //checks if a piece can move down without hitting a piece below it

        return pieceUnder;
    }

    /**
     * checks if the piece currently being played with can move right without hitting any other pieces
     * @return true if there's a piece to the right of the current piece and false otherwise
     */
    public boolean rightmostPiece() {
        boolean pieceRight = false;

        //checks if a piece can move right without hitting another piece
        for (int c = 0; c < p.width; c++) {
            for (int r = 0; r < p.height; r++) {
                if (p.occupies(c, r)){
                    if (!p.occupies(c + 1, r)){
                        if (board[y + r][x + c + 1]){
                            pieceRight = true;
                        }
                    }
                }
            }
        }
        return pieceRight;
    }


    /**
     * checks if the piece currently being played with can move left without hitting any other pieces
     * @return true if there's a piece to the left of the current piece and false otherwise
     */
    public boolean leftmostPiece() {
        boolean pieceLeft = false;

        //checks if the piece can move left without hitting another piece
        for (int c = 0; c < p.width; c++) {
            for (int r = 0; r < p.height; r++) {
                if (p.occupies(c, r)) {
                    if (c >= 1 && !p.occupies(c - 1, r)){
                        if (board[y + r][x + c - 1]){
                            pieceLeft = true;
                        }
                    }
                    else if (c == 0) {
                        if (board[y + r][x + c - 1]){
                            pieceLeft = true;
                        }
                    }
                }
            }
        }
        return pieceLeft;
    }

    /**
     * moves the piece from x, y to a new location
     * @param dx the amount moved right/left
     * @param dy the amount moved up/down
     */
    public void positionChange(int dx, int dy, boolean[][] b, boolean tf) {

        //sets the piece's current location to false
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(p.occupies(i, j)) {
                    b[j+y][i+x] = false;
                }
            }
        }

        //changes x and y by dx and dy amounts
        y += dy;
        x += dx;

        //makes the new location true if variable tf is true.  Otherwise does nothing
        //and essentially makes a piece disappear
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
        //make an object for each of the piece types
        Pieces jB = new JBlock();
        Pieces iB = new IBlock();
        Pieces lB = new LBlock();
        Pieces oB = new OBlock();
        Pieces sB = new SBlock();
        Pieces tB = new TBlock();
        Pieces zB = new ZBlock();

        //add each piece to the map
        randPieces.put(1, jB);
        randPieces.put(2, iB);
        randPieces.put(3, lB);
        randPieces.put(4, oB);
        randPieces.put(5, sB);
        randPieces.put(6, tB);
        randPieces.put(7, zB);

        //return a random piece from the map
        return randPieces.get(rand.nextInt(1, 8));
    }
}