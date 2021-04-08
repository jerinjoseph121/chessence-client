package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;

public class King extends AbstractPiece {

    public King(Pair<Integer, Integer> coordinates, String color) {
        super(coordinates, color, "K");    //K -> KING
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece[][] boardMatrix) {
        ArrayList<Pair<Integer, Integer>> validDestinations = new ArrayList<>();
        int[][] possibleDistances = {{1, 0}, {0, 1}, {1, 1}, {-1, 0},
                {0, -1}, {-1, -1}, {1, -1}, {-1, 1}};
        for (var distance : possibleDistances) {

            int x = this.getCoordinates().getKey();
            int y = this.getCoordinates().getValue();

            if (!((((x + distance[0]) >= 0) && ((x + distance[0]) <= 7)) && (((y + distance[1]) >= 0) && ((y + distance[1]) <= 7))))
                continue;

            if ((boardMatrix[x + distance[0]][y + distance[1]] != null) && (boardMatrix[x + distance[0]][y + distance[1]].getColor() == this.getColor()))
                continue;

            validDestinations.add(new Pair<Integer, Integer>(x + distance[0], y + distance[1]));
        }
        return validDestinations;
    }
}
