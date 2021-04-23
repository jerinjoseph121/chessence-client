package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;



public class Pawn extends AbstractPiece {

    private final Pair<Integer, Integer> startPosition;

    public Pawn(Pair<Integer, Integer> coordinates, boolean isWhite) {
        super(coordinates, isWhite, "P");    //P -> PAWN
        startPosition = this.getCoordinates();
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece[][] boardMatrix, boolean check) {
        ArrayList<Pair<Integer, Integer>> validDestinations = new ArrayList<>();
        int[][] possibleDistances = {{1, 0}, {1, 1}, {1, -1}, {-1, 0}, {-1, 1}, {-1, -1}, {2, 0}, {-2, 0}};
        for (var distance : possibleDistances) {

            int x = this.getCoordinates().getKey();
            int y = this.getCoordinates().getValue();

            if (!((((x + distance[0]) >= 0) && ((x + distance[0]) <= 7)) && (((y + distance[1]) >= 0) && ((y + distance[1]) <= 7))))
                continue;

            if ((boardMatrix[x + distance[0]][y + distance[1]] != null) && (boardMatrix[x + distance[0]][y + distance[1]].isWhite() == this.isWhite()))
                continue;

            //Checks direction of movement based on the color
            if (!this.isWhite() && distance[0]==-1)
                continue;

            if (this.isWhite() && distance[0]==1)
                continue;

            //Moves pawn diagonally if enemy is at that position
            if ((boardMatrix[x + distance[0]][y + distance[1]] == null) && (distance[1] != 0))
                continue;

            if ((boardMatrix[x + distance[0]][y + distance[1]] != null) && (distance[1] == 0))
                continue;

            //Makes pawn able to move 2 steps at start
            if ((this.getCoordinates() != startPosition) && ((distance[0] == 2) || (distance[0] == -2)))
                continue;

            //Wont allow to start 2 step move if another piece blocks it
            if((distance[0] == 2) && (boardMatrix[x + 1][y] != null))
                continue;
            if((distance[0] == -2) && (boardMatrix[x - 1][y] != null))
                continue;

            validDestinations.add(new Pair<Integer, Integer>(x + distance[0], y + distance[1]));
        }
        return validDestinations;
    }
}