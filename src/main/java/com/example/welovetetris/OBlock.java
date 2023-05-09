package com.example.welovetetris;

public class OBlock extends Pieces{
    public OBlock() {
        super();
        r = 0;
        c = 10;
        width = 2;
        height = 2;
        mask[0][0] = true;
        mask[0][1] = true;
        mask[1][0] = true;
        mask[1][1] = true;
    }
}
