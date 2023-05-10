package com.example.welovetetris;

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
    protected int width;
    protected int height;

    /**
     * The constructor for the Pieces class.  Initializes the mask list and sets all of its
     * squares to false.
     */
    public Pieces() {
        mask = new Boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
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
}
