package com.example.welovetetris;

public class LBlock extends Pieces{
    public LBlock() {
        super();
        width = 2;
        height = 3;
        mask[0][0] = true;
        mask[0][1] = true;
        mask[0][2] = true;
        mask[1][2] = true;
    }

    public void pieceShape() {

    }
}
