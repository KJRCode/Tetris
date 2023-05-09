package com.example.welovetetris;

public class LBlock extends Pieces{
    public LBlock() {

        //call super constructor and instantiate instance variables
        super();
        width = 2;
        height = 3;

        //draw piece in mask
        mask[0][0] = true;
        mask[0][1] = true;
        mask[0][2] = true;
        mask[1][2] = true;
    }

}
