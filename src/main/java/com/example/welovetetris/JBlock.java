package com.example.welovetetris;

public class JBlock extends Pieces{
    public JBlock() {

        //call super constructor and instantiate instance variables
        super();
        r = 0;
        c = 10;
        width = 3;
        height = 2;

        //draw piece in mask
        mask[0][0] = true;
        mask[1][0] = true;
        mask[2][0] = true;
        mask[0][1] = true;
    }
}
