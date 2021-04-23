package com.chessence;

import java.io.Serializable;

public class Move implements Serializable {
    private int[][] from;
    private int[][] to;

    public Move(int[][] from, int[][] to) {
        this.from = from;
        this.to = to;
    }

    public int[][] getFrom() {
        return from;
    }

    public int[][] getTo() {
        return to;
    }
}
