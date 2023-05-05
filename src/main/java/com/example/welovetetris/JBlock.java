package com.example.welovetetris;

public class JBlock extends Pieces{
    public JBlock() {
        super();
        r = 0;
        c = 8;
        mask[0][0] = true;
        mask[1][0] = true;
        mask[2][0] = true;
        mask[3][0] = true;
        mask[3][1] = true;
    }
}
