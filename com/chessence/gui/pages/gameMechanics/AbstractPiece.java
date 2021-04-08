package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;

public abstract class AbstractPiece {
    private String color;
    private Pair<Integer, Integer> coordinates;
    private String imagePath;

    public AbstractPiece(Pair<Integer, Integer> coordinates, String color, String imageNameSuffix) {
        this.color = color;
        this.coordinates = coordinates;
        this.imagePath = "../../images/chessPieces/fancy/" + ((color == "black") ? "B" : "W") + imageNameSuffix + ".gif";
    }

    abstract public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece boardMatrix[][]);

    public AbstractPiece[][] move(Pair<Integer, Integer> coordinates, AbstractPiece boardMatrix[][]) {
        //FUNCTION TO MOVE THIS PARTICULAR PIECE:
        if (getValidDestinations(boardMatrix).contains(coordinates)) {
            //check if the destination coordinate is a valid coordinate
            boardMatrix[this.coordinates.getKey()][this.coordinates.getValue()] = null;
            this.coordinates = coordinates;
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

    public String getColor() {
        return color;
    }
}
