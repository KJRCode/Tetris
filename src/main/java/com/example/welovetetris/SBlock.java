package com.example.welovetetris;

public class SBlock extends Pieces{
    public SBlock() {

        //call super constructor and instantiate instance variables
        super();
        r = 0;
        c = 10;
        width = 2;
        height = 3;

        //draw piece in mask
        mask[0][0] = true;
        mask[0][1] = true;
        mask[1][1] = true;
        mask[1][2] = true;
    }
}
