package com.example.welovetetris;

import java.lang.reflect.Array;
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
    protected Boolean[][] mask;
    protected int r;
    protected int c;

    /**
     * The constructor for the Pieces class.  Initializes the mask list and sets all of its
     * squares to false.
     */
    public Pieces() {
        mask = new Boolean[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mask[i][j] = false;
            }

        }
    }

    /**
     * checks if a certain square of mask is true or false
     * @param first the x coordinate
     * @param second the y coordinate
     * @return true if mask[first][second] is true
     */
    public boolean occupies(int first, int second) {
        return mask[first][second];
    }

    public int getR() { return r;}
    public int getC() { return c;}

    /*
    Piece will need a position
     */

    /**
     * rotates piece clockwise by 90 degrees
     */
    public void turnPieceClockwise() {
    }

    /**
     * abstract method
     * the shape of each of the pieces
     */
}
