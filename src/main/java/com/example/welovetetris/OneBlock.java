package com.example.welovetetris;

public class OneBlock extends Pieces{
    public OneBlock() {
        super();
        r = 0;
        c = 10;
        width = 3;
        height = 2;
        mask[0][0] = true;
        mask[1][0] = true;
        mask[2][0] = true;
        //mask[3][0] = true;
        mask[0][1] = true;

    }


}