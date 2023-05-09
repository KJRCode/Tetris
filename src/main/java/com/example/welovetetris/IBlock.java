package com.example.welovetetris;

public class IBlock extends Pieces{
    public IBlock(){

        //call super constructor and instantiate instance variables
        super();
        r = 0;
        c = 10;
        width = 1;
        height = 4;

        //draw piece in mask
        mask[0][0] = true;
        mask[0][1] = true;
        mask[0][2] = true;
        mask[0][3] = true;
    }

}
