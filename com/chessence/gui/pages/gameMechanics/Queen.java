package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;

public class Queen extends AbstractPiece {

    public Queen(Pair<Integer, Integer> coordinates, String color) {
        super(coordinates, color, "Q");    //Q -> QUEEN
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece[][] boardMatrix) {
        ArrayList<Pair<Integer, Integer>> validDestinations = new ArrayList<>();

        ArrayList<int[]> possibleDistances = new ArrayList<>();

        int[][] pathways = {{1, 0}, {0, 1}, {1, 1}, {-1, 0},
                {0, -1}, {-1, -1}, {1, -1}, {-1, 1}};

        for(var path : pathways){
            for(int i=1; i<16; i++){
                possibleDistances.add(new int[]{path[0]*i, path[1]*i});
            }
        }

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