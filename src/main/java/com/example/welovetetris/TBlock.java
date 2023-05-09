package com.example.welovetetris;

public class TBlock extends Pieces{
    public TBlock() {
        super();
        r = 0;
        c = 10;
        width = 3;
        height = 2;
        mask[0][0] = true;
        mask[1][0] = true;
        mask[2][0] = true;
        mask[1][1] = true;
    }

    public void pieceShape() {

    }
}
