package com.example.welovetetris;

public abstract class Pieces {
    protected boolean[][] p;
    protected char pChar;
    public Pieces(){

    }
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
    public void moveLeft() {

    }
    public void moveRight() {

    }
    public abstract void pieceShape();
}
