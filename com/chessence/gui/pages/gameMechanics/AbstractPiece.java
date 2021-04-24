package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;

public abstract class AbstractPiece{
    private boolean isWhite;
    private Pair<Integer, Integer> coordinates;
    private String imagePath;
    private boolean didMove;

    public AbstractPiece(Pair<Integer, Integer> coordinates, boolean isWhite, String imageNameSuffix) {
        this.isWhite = isWhite;
        this.coordinates = coordinates;
        this.imagePath = "../../images/chessPieces/fancy/" + ((!isWhite) ? "B" : "W") + imageNameSuffix + ".gif";
        this.didMove = false;
    }

    abstract public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece boardMatrix[][], boolean check);

    public AbstractPiece[][] move(Pair<Integer, Integer> coordinates, AbstractPiece boardMatrix[][]) {
        //FUNCTION TO MOVE THIS PARTICULAR PIECE:
        if (getValidDestinations(boardMatrix, false).contains(coordinates)) {
            //check if the destination coordinate is a valid coordinate

            if(boardMatrix[this.coordinates.getKey()][this.coordinates.getValue()] instanceof King)
            {
                // Checks castling condition (Short)
                if ((coordinates.getValue() - this.coordinates.getValue()) == 2){
                    // Moves the rook to its respective position when castling
                    boardMatrix[this.coordinates.getKey()][5] = boardMatrix[this.coordinates.getKey()][7];
                    boardMatrix[this.coordinates.getKey()][5].coordinates = new Pair<Integer, Integer>(this.coordinates.getKey(), 5);
                    boardMatrix[this.coordinates.getKey()][7] = null;
                    GameRules.changeCastled(boardMatrix[this.coordinates.getKey()][this.coordinates.getValue()].isWhite());
                }
                // Check castling condition (Long)
                else if ((coordinates.getValue() - this.coordinates.getValue()) == -2){
                    System.out.println("Long");
                    // Moves the rook to its respective position when castling
                    boardMatrix[this.coordinates.getKey()][3] = boardMatrix[this.coordinates.getKey()][0];
                    boardMatrix[this.coordinates.getKey()][3].coordinates = new Pair<Integer, Integer>(this.coordinates.getKey(), 3);
                    boardMatrix[this.coordinates.getKey()][0] = null;
                    GameRules.changeCastled(boardMatrix[this.coordinates.getKey()][this.coordinates.getValue()].isWhite());
                }

                //game over -> this player wins.
//                System.out.println("GAME OVER!");
            }
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

    public boolean isWhite() {
        return isWhite;
    }

    public void startMove(){
        this.didMove = true;
    }

    public boolean getDidMove(){
        return this.didMove;
    }
}
