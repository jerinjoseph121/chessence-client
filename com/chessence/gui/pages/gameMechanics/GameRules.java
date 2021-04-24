package com.chessence.gui.pages.gameMechanics;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class GameRules {

    private static boolean castleWhite;
    private static boolean castleBlack;

    private static boolean checkWhite;
    private static boolean checkBlack;

    private static ArrayList<Pair<Integer, Integer>> whiteMoves;
    private static ArrayList<Pair<Integer, Integer>> blackMoves;

    private static ArrayList<Pair<Integer, Integer>> playableWhiteMoves;
    private static ArrayList<Pair<Integer, Integer>> playableBlackMoves;

    private static AbstractPiece whiteKing;
    private static AbstractPiece blackKing;

    private static AbstractPiece piece;

    public GameRules(AbstractPiece boardMatrix[][]){
        createWhiteMoves(boardMatrix);
        createBlackMoves(boardMatrix);

        this.castleWhite = false;
        this.castleBlack = false;
        this.checkWhite = false;
        this.checkBlack = false;
        gameUpdate(boardMatrix);

    }

    public static void gameUpdate(AbstractPiece boardMatrix[][]){

        createPlayableWhiteMoves(boardMatrix);
        createPlayableBlackMoves(boardMatrix);

        if(playableBlackMoves.contains(whiteKing.getCoordinates())){
            checkWhite = true;
        }
        else
            checkWhite = false;

        if(playableWhiteMoves.contains(blackKing.getCoordinates())){
            checkBlack = true;
        }
        else
            checkBlack = false;

        if(isCheckMate(true)){
            System.out.println("Black Wins");
        }
        else if(isCheckMate(false)){
            System.out.println("White Wins");
        }
    }

    private static void createPlayableWhiteMoves(AbstractPiece boardMatrix[][]){
        Set<Pair<Integer, Integer>> temp = new LinkedHashSet<>();
        ArrayList<Pair<Integer, Integer>> moves;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                if(boardMatrix[i][j] != null){
                    piece = boardMatrix[i][j];

                    if(piece.isWhite()){
                        moves = piece.getValidDestinations(boardMatrix, false);
                        if(piece instanceof King){
                            whiteKing = piece;
                        }
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
        if(playableWhiteMoves != null)
            playableWhiteMoves.clear();
        playableWhiteMoves = new ArrayList<>(temp);
    }

    private static void createPlayableBlackMoves(AbstractPiece boardMatrix[][]){
        Set<Pair<Integer, Integer>> temp = new LinkedHashSet<>();
        ArrayList<Pair<Integer, Integer>> moves;

        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardMatrix[i][j] != null) {
                    piece = boardMatrix[i][j];

                    if (!piece.isWhite()) {
                        moves = piece.getValidDestinations(boardMatrix, false);
                        if (piece instanceof King) {
                            blackKing = piece;
                        }
                        if (piece instanceof Pawn) {
                            // Removes the Straight Pawn Moves as Threat Moves
                            moves.removeIf((move) -> (move.getValue() == piece.getCoordinates().getValue()));
                            // Adds the diagonal moves of Pawn as Threat Moves
                            moves.add(new Pair<Integer, Integer>(piece.getCoordinates().getKey() + 1, piece.getCoordinates().getValue() + 1));
                            moves.add(new Pair<Integer, Integer>(piece.getCoordinates().getKey() + 1, piece.getCoordinates().getValue() - 1));
                        }
                        System.out.println(moves);
                        System.out.println(piece);
                        temp.addAll(moves);
                    }
                }
            }
        }

        if(playableBlackMoves != null)
            playableBlackMoves.clear();
        playableBlackMoves = new ArrayList<>(temp);
    }

    private static void createWhiteMoves(AbstractPiece boardMatrix[][]){
        Set<Pair<Integer, Integer>> temp = new LinkedHashSet<>();
        ArrayList<Pair<Integer, Integer>> moves;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                if(boardMatrix[i][j] != null){
                    piece = boardMatrix[i][j];

                    if(piece.isWhite()){
                        moves = piece.getValidDestinations(boardMatrix, true);
                        if(piece instanceof King){
                            whiteKing = piece;
                        }
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

        if(whiteMoves != null)
            whiteMoves.clear();
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
                        moves = piece.getValidDestinations(boardMatrix, true);
                        if(piece instanceof King){
                            blackKing = piece;
                        }
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

        if(blackMoves != null)
            blackMoves.clear();
        blackMoves = new ArrayList<>(temp);
    }

    public static boolean isCheck(boolean isWhite){
        if(isWhite)
            return checkWhite;
        else
            return checkBlack;
    }

    public static boolean isCheckMate(boolean isWhite){
        if(isWhite){
            if(checkWhite && (playableWhiteMoves.size() == 0))
                return true;
            else
                return false;
        }
        else{
            System.out.println(playableBlackMoves);
            if(checkBlack && (playableBlackMoves.size() == 0))
                return true;
            else
                return false;
        }
    }

    public static void changeCastled(boolean isWhite){
        if(isWhite)
            castleWhite = true;
        else
            castleBlack = true;
    }

    public static boolean isCastled(boolean isWhite){
        if(isWhite)
            return castleWhite;
        else
            return castleBlack;
    }

    public static boolean isSavedFromCheck(AbstractPiece checkPiece, Pair<Integer, Integer> coordinates, AbstractPiece boardMatrix[][]){
        int x = checkPiece.getCoordinates().getKey();
        int y = checkPiece.getCoordinates().getValue();

        AbstractPiece tempPiece;

        // Adding the piece in the candidate position to check if the king will escape check when its in that position
        boardMatrix[x][y] = null;
        tempPiece = boardMatrix[coordinates.getKey()][coordinates.getValue()];
        boardMatrix[coordinates.getKey()][coordinates.getValue()] = checkPiece;

        boolean isSaved;

        // Checking if king will escape check by finding the total moves possible for the opponent and checking if king's position is in it
        if(checkPiece.isWhite()) {
            createBlackMoves(boardMatrix);

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

        // Changing the piece back to its original position
        boardMatrix[x][y] = checkPiece;
        boardMatrix[coordinates.getKey()][coordinates.getValue()] = tempPiece;

        return isSaved;
    }

    public static ArrayList<Pair<Integer, Integer>> getPlayableWhiteMoves(){
        return playableWhiteMoves;
    }

    public static ArrayList<Pair<Integer, Integer>> getPlayableBlackMoves(){
        return playableBlackMoves;
    }

}
