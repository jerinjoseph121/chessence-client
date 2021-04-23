package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;

public class Knight extends AbstractPiece {

    public Knight(Pair<Integer, Integer> coordinates, boolean isWhite) {
        super(coordinates, isWhite, "N");    //N -> KNIGHT
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece[][] boardMatrix) {
        ArrayList<Pair<Integer, Integer>> validDestinations = new ArrayList<>();

        int[][] possibleDistances = {{1, 2}, {2, 1}, {-1, 2}, {2, -1},
                {1, -2}, {-2, 1}, {-1, -2}, {-2, -1}};

        for (var distance : possibleDistances) {

            int x = this.getCoordinates().getKey();
            int y = this.getCoordinates().getValue();

            if (!((((x + distance[0]) >= 0) && ((x + distance[0]) <= 7)) && (((y + distance[1]) >= 0) && ((y + distance[1]) <= 7))))
                continue;

            if ((boardMatrix[x + distance[0]][y + distance[1]] != null) && (boardMatrix[x + distance[0]][y + distance[1]].isWhite() == this.isWhite()))
                continue;

            validDestinations.add(new Pair<Integer, Integer>(x + distance[0], y + distance[1]));
        }
        return validDestinations;
    }
}