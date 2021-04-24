package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;

public class King extends AbstractPiece {

    private AbstractPiece threatPiece;

    public King(Pair<Integer, Integer> coordinates, boolean isWhite) {
        super(coordinates, isWhite, "K");    //K -> KING
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getValidDestinations(AbstractPiece[][] boardMatrix, boolean check) {
        ArrayList<Pair<Integer, Integer>> validDestinations = new ArrayList<>();
        int[][] possibleDistances = {{1, 0}, {0, 1}, {1, 1}, {-1, 0},
                {0, -1}, {-1, -1}, {1, -1}, {-1, 1}, {0, 2}, {0, -2}};

        //Storing the threat moves in this array
        ArrayList<Pair<Integer, Integer>> threatDestinations = new ArrayList<>();
        if(this.isWhite())
            threatDestinations = GameRules.getPlayableBlackMoves();
        else
            threatDestinations = GameRules.getPlayableWhiteMoves();

        for (var distance : possibleDistances) {

            int x = this.getCoordinates().getKey();
            int y = this.getCoordinates().getValue();

            if (!((((x + distance[0]) >= 0) && ((x + distance[0]) <= 7)) && (((y + distance[1]) >= 0) && ((y + distance[1]) <= 7))))
                continue;

            if ((boardMatrix[x + distance[0]][y + distance[1]] != null) && (boardMatrix[x + distance[0]][y + distance[1]].isWhite() == this.isWhite()))
                continue;

            if (threatDestinations.contains(new Pair<Integer, Integer>(x + distance[0], y + distance[1])))
                continue;

            // Castling Criteria (Short)
            if((distance[1] == 2)){
                if (this.getDidMove())
                    continue;

                if (GameRules.isCheck(isWhite()))
                    continue;

                if (!((boardMatrix[x][7] instanceof Rook) && (!(boardMatrix[x][7].getDidMove()))))
                    continue;

                if (boardMatrix[x][5] != null)
                    continue;

                if (this.isWhite()){
                    if (GameRules.getPlayableBlackMoves().contains(new Pair<Integer, Integer>(x, 5)))
                        continue;
                    if (GameRules.getPlayableBlackMoves().contains(new Pair<Integer, Integer>(x, 6)))
                        continue;
                }
                else{
                    if (GameRules.getPlayableWhiteMoves().contains(new Pair<Integer, Integer>(x,  5)))
                        continue;
                    if (GameRules.getPlayableWhiteMoves().contains(new Pair<Integer, Integer>(x, 6)))
                        continue;
                }
            }

            // Castling Criteria (Long)
            if((distance[1] == -2)){
                if (this.getDidMove())
                    continue;

                if (GameRules.isCheck(isWhite()))
                    continue;

                if (!((boardMatrix[x][0] instanceof Rook) && (!(boardMatrix[x][0].getDidMove()))))
                    continue;

                if (boardMatrix[x][3] != null || boardMatrix[x][1] != null)
                    continue;

                if (this.isWhite()){
                    if (GameRules.getPlayableBlackMoves().contains(new Pair<Integer, Integer>(x, 3)))
                        continue;
                    if (GameRules.getPlayableBlackMoves().contains(new Pair<Integer, Integer>(x, 2)))
                        continue;
                }
                else{
                    if (GameRules.getPlayableWhiteMoves().contains(new Pair<Integer, Integer>(x, 3)))
                        continue;
                    if (GameRules.getPlayableWhiteMoves().contains(new Pair<Integer, Integer>(x, 2)))
                        continue;
                }
            }

            validDestinations.add(new Pair<Integer, Integer>(x + distance[0], y + distance[1]));
        }

//        if(!check)
//            validDestinations.removeIf(move -> !GameRules.isSavedFromCheck(this, move, boardMatrix));

        return validDestinations;
    }
}
