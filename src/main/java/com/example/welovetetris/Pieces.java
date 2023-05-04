package com.example.welovetetris;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Winona Wherley
 * @author Karly Ripper
 *
 * this is an abstract class that's extended by all of the different kinds of
 * pieces that we'll need.
 */
public abstract class Pieces{

    //setting up class variables
    protected boolean[][] p;
    protected char pChar;
    public Pieces(){
        Map<Integer, Integer> coordinates = new HashMap<>();
        coordinates.put(0, 10);
        coordinates.put(0, 11);
    }

    /**
     * rotates piece clockwise by 90 degrees
     */
    public void turnPieceClockwise() {
        Boolean[][] pieceSpin = new Boolean[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pieceSpin[j][3-i] = p[i][j];
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                p[i][j] = pieceSpin[i][j];
            }
        }
    }

    //TODO: don't need moveLeft and moveRight methods anymore???
    public void moveLeft() {

    }
    public void moveRight() {

    }

    /**
     * abstract method
     * the shape of each of the pieces
     */
    public abstract void pieceShape();

    public boolean[][] getPieceShape() {
        return p;
    }
}
