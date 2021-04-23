package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class GameRules {

    private static boolean checkWhite;
    private static boolean checkBlack;

//    private static boolean checkMateWhite = false;
//    private static boolean checkMateBlack = false;

    private static ArrayList<Pair<Integer, Integer>> whiteMoves;
    private static ArrayList<Pair<Integer, Integer>> blackMoves;

    private static AbstractPiece whiteKing;
    private static AbstractPiece blackKing;

    private static AbstractPiece piece;



    public GameRules(AbstractPiece boardMatrix[][]){
        System.out.println("Game Rules");
        this.checkWhite = false;
        this.checkBlack = false;
        gameUpdate(boardMatrix);

    }

    public static void gameUpdate(AbstractPiece boardMatrix[][]){
        System.out.println("Update Game Rules");

        createWhiteMoves(boardMatrix);
        createBlackMoves(boardMatrix);

        if(blackMoves.contains(whiteKing.getCoordinates())){
            System.out.println("White is Check");
            checkWhite = true;
        }
        else
            checkWhite = false;

        if(whiteMoves.contains(blackKing.getCoordinates())){
            System.out.println("Black is Check");
            checkBlack = true;
        }
        else
            checkBlack = false;
    }

    private static void createWhiteMoves(AbstractPiece boardMatrix[][]){
        Set<Pair<Integer, Integer>> temp = new LinkedHashSet<>();
        ArrayList<Pair<Integer, Integer>> moves;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                if(boardMatrix[i][j] != null){
                    piece = boardMatrix[i][j];

                    if(piece.isWhite()){
                        System.out.println("White Validation");
                        moves = piece.getValidDestinations(boardMatrix, true);
                        if(piece instanceof King)
                            whiteKing = piece;
                        if(piece instanceof Pawn){
                            // Removes the Straight Pawn Moves as Threat Moves
                            moves.removeIf((move) -> (move.getValue() == piece.getCoordinates().getValue()));
                            // Adds the diagonal moves of Pawn as Threat Moves
                            moves.add(new Pair<Integer, Integer>(piece.getCoordinates().getKey() + 1, piece.getCoordinates().getValue() + 1));
                            moves.add(new Pair<Integer, Integer>(piece.getCoordinates().getKey() + 1, piece.getCoordinates().getValue() - 1));
                        }
                        temp.addAll(moves);
                    }
                }
            }
        }

        whiteMoves = new ArrayList<>(temp);
    }

    private static void createBlackMoves(AbstractPiece boardMatrix[][]){
        Set<Pair<Integer, Integer>> temp = new LinkedHashSet<>();
        ArrayList<Pair<Integer, Integer>> moves;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                if(boardMatrix[i][j] != null){
                    piece = boardMatrix[i][j];

                    if(!piece.isWhite()){
                        System.out.println("Black Validation");
                        moves = piece.getValidDestinations(boardMatrix, true);
                        if(piece instanceof King)
                            blackKing = piece;
                        if(piece instanceof Pawn){
                            // Removes the Straight Pawn Moves as Threat Moves
                            moves.removeIf((move) -> (move.getValue() == piece.getCoordinates().getValue()));
                            // Adds the diagonal moves of Pawn as Threat Moves
                            moves.add(new Pair<Integer, Integer>(piece.getCoordinates().getKey() + 1, piece.getCoordinates().getValue() + 1));
                            moves.add(new Pair<Integer, Integer>(piece.getCoordinates().getKey() + 1, piece.getCoordinates().getValue() - 1));
                        }
                        temp.addAll(moves);
                    }
                }
            }
        }

        blackMoves = new ArrayList<>(temp);
    }

    public static boolean isCheck(boolean isWhite){
        if(isWhite)
            return checkWhite;
        else
            return checkBlack;
    }

    public static boolean isSavedFromCheck(AbstractPiece checkPiece, Pair<Integer, Integer> coordinates, AbstractPiece boardMatrix[][]){
        int x = checkPiece.getCoordinates().getKey();
        int y = checkPiece.getCoordinates().getValue();

        AbstractPiece tempPiece;

        boardMatrix[x][y] = null;
        tempPiece = boardMatrix[coordinates.getKey()][coordinates.getValue()];
        boardMatrix[coordinates.getKey()][coordinates.getValue()] = checkPiece;

        boolean isSaved;

        if(checkPiece.isWhite()) {
//            createBlackMoves(boardMatrix);

            if(blackMoves.contains(whiteKing.getCoordinates())){
                isSaved = false;
            }
            else
                isSaved = true;
        }
        else {
            createWhiteMoves(boardMatrix);

            if(whiteMoves.contains(blackKing.getCoordinates())){
                isSaved = false;
            }
            else
                isSaved = true;
        }

        System.out.println("Saved Check Running:");
        System.out.println("X:" + coordinates.getKey());
        System.out.println("Y:" + coordinates.getValue());

        boardMatrix[x][y] = checkPiece;
        boardMatrix[coordinates.getKey()][coordinates.getValue()] = tempPiece;

        return isSaved;
    }

    public static ArrayList<Pair<Integer, Integer>> getWhiteMoves(){
        return whiteMoves;
    }

    public static ArrayList<Pair<Integer, Integer>> getBlackMoves(){
        return blackMoves;
    }

}
