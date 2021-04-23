package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;

public abstract class AbstractPiece {
    private boolean isWhite;
    private Pair<Integer, Integer> coordinates;
    private String imagePath;

    public AbstractPiece(Pair<Integer, Integer> coordinates, boolean isWhite, String imageNameSuffix) {
        this.isWhite = isWhite;
        this.coordinates = coordinates;
        this.imagePath = "../../images/chessPieces/fancy/" + ((!isWhite) ? "B" : "W") + imageNameSuffix + ".gif";
    }

    abstract public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece boardMatrix[][]);

    public AbstractPiece[][] move(Pair<Integer, Integer> coordinates, AbstractPiece boardMatrix[][]) {
        //FUNCTION TO MOVE THIS PARTICULAR PIECE:
        if (getValidDestinations(boardMatrix).contains(coordinates)) {
            //check if the destination coordinate is a valid coordinate
            boardMatrix[this.coordinates.getKey()][this.coordinates.getValue()] = null;
            this.coordinates = coordinates;
            if(boardMatrix[coordinates.getKey()][coordinates.getValue()] instanceof King)
            {
                //game over -> this player wins.
                System.out.println("GAME OVER!");
            }
            boardMatrix[coordinates.getKey()][coordinates.getValue()] = this;
        }
        return boardMatrix;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return this.coordinates;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
