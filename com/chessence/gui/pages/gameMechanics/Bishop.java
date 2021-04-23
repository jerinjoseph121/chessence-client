package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;

public class Bishop extends AbstractPiece {

    public Bishop(Pair<Integer, Integer> coordinates, boolean isWhite) {
        super(coordinates, isWhite, "B");    //B -> BISHOP
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece[][] boardMatrix, boolean check) {
        ArrayList<Pair<Integer, Integer>> validDestinations = new ArrayList<>();

        ArrayList<int[]> possibleDistances = new ArrayList<>();

        // Pathway states the direction the piece will move
        int[][] pathways = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

        int x = this.getCoordinates().getKey();
        int y = this.getCoordinates().getValue();

        //Pathway structure helps to create possibleDistances
        for(var path : pathways){
            for(int i=1; i<8; i++){
                if (!((((x + (path[0]*i)) >= 0) && ((x + (path[0]*i)) <= 7)) && (((y + (path[1]*i)) >= 0) && ((y + (path[1]*i)) <= 7))))
                    continue;

                //Prevents movement if another piece blocks it
                if(boardMatrix[x+(path[0]*i)][y+(path[1]*i)] != null){
                    possibleDistances.add(new int[]{path[0]*i, path[1]*i});
                    break;
                }
                possibleDistances.add(new int[]{path[0]*i, path[1]*i});
            }
        }

        for (var distance : possibleDistances) {

            if ((boardMatrix[x + distance[0]][y + distance[1]] != null) && (boardMatrix[x + distance[0]][y + distance[1]].isWhite() == this.isWhite()))
                continue;

            validDestinations.add(new Pair<Integer, Integer>(x + distance[0], y + distance[1]));
        }

        if(GameRules.isCheck(this.isWhite()) && !check)
            validDestinations.removeIf(move -> !GameRules.isSavedFromCheck(this, move, boardMatrix));

        return validDestinations;
    }
}