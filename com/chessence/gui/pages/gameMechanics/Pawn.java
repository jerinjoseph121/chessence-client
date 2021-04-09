package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;

public class Pawn extends AbstractPiece {

    public Pawn(Pair<Integer, Integer> coordinates, String color) {
        super(coordinates, color, "P");    //P -> PAWN
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece[][] boardMatrix) {
        ArrayList<Pair<Integer, Integer>> validDestinations = new ArrayList<>();
        int[][] possibleDistances = {{1, 0}, {1, 1}, {1, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
        for (var distance : possibleDistances) {

            int x = this.getCoordinates().getKey();
            int y = this.getCoordinates().getValue();

            if (!((((x + distance[0]) >= 0) && ((x + distance[0]) <= 7)) && (((y + distance[1]) >= 0) && ((y + distance[1]) <= 7))))
                continue;

            if ((boardMatrix[x + distance[0]][y + distance[1]] != null) && (boardMatrix[x + distance[0]][y + distance[1]].getColor() == this.getColor()))
                continue;

            if (this.getColor() == "black" && distance[0]==-1)
                continue;

            if (this.getColor() == "white" && distance[0]==1)
                continue;

            if ((boardMatrix[x + distance[0]][y + distance[1]] == null) && (distance[1] != 0))
                continue;

            if ((boardMatrix[x + distance[0]][y + distance[1]] != null) && (distance[1] == 0))
                continue;

            validDestinations.add(new Pair<Integer, Integer>(x + distance[0], y + distance[1]));
        }
        return validDestinations;
    }
}