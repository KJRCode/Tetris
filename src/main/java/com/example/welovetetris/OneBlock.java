package com.example.welovetetris;

public class OneBlock extends Pieces{
    public OneBlock() {
        super();
        super.pChar = 'Y';
        super.p = new boolean[4][4];
    }

    public void pieceShape() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                p[i][j] = false;
            }
        }

        p[0][2] = true;
    }
}
