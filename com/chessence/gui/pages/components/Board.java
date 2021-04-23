package com.chessence.gui.pages.components;

import com.chessence.gui.pages.gameMechanics.GameRules;
import com.chessence.gui.pages.gameMechanics.AbstractPiece;
import com.chessence.gui.pages.gameMechanics.Pawn;
import com.chessence.gui.pages.gameMechanics.King;
import com.chessence.gui.pages.gameMechanics.Queen;
import com.chessence.gui.pages.gameMechanics.Knight;
import com.chessence.gui.pages.gameMechanics.Bishop;
import com.chessence.gui.pages.gameMechanics.Rook;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class Board extends JPanel {
    private Dimension size;
    private int len;
    private static AbstractPiece boardMatrix[][] = new AbstractPiece[8][8];
    private static Tile tileMatrix[][] = new Tile[8][8];
    private boolean isPlayerWhite;

    public Board(int W, int H, boolean isPlayerWhite) {

        //Initializing the current player's color:
        this.isPlayerWhite = isPlayerWhite;
        Tile.isPlayerWhite = isPlayerWhite;

        //Initializing the board with all the pieces----------
        //for all the black pieces:
        //jerin was a racist so he put the black pieces first
        // P.S: beeta is racist for thinking that putting black piece first is racist
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    if (j == 0 || j == 7) {
                        Rook rook = new Rook(new Pair<Integer, Integer>(i, j), false);
                        boardMatrix[i][j] = rook;
                    } else if (j == 1 || j == 6) {
                        Knight knight = new Knight(new Pair<Integer, Integer>(i, j), false);
                        boardMatrix[i][j] = knight;
                    } else if (j == 2 || j == 5) {
                        Bishop bishop = new Bishop(new Pair<Integer, Integer>(i, j), false);
                        boardMatrix[i][j] = bishop;
                    } else if (j == 3) {
                        Queen queen = new Queen(new Pair<Integer, Integer>(i, j), false);
                        boardMatrix[i][j] = queen;
                    } else if (j == 4) {
                        King king = new King(new Pair<Integer, Integer>(i, j), false);
                        boardMatrix[i][j] = king;
                    }
                } else {
                    Pawn pawn = new Pawn(new Pair<Integer, Integer>(i, j), false);
                    boardMatrix[i][j] = pawn;
                }

            }
        }

        //for all the white pieces:
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 7) {
                    if (j == 0 || j == 7) {
                        Rook rook = new Rook(new Pair<Integer, Integer>(i, j), true);
                        boardMatrix[i][j] = rook;
                    } else if (j == 1 || j == 6) {
                        Knight knight = new Knight(new Pair<Integer, Integer>(i, j), true);
                        boardMatrix[i][j] = knight;
                    } else if (j == 2 || j == 5) {
                        Bishop bishop = new Bishop(new Pair<Integer, Integer>(i, j), true);
                        boardMatrix[i][j] = bishop;
                    } else if (j == 3) {
                        Queen queen = new Queen(new Pair<Integer, Integer>(i, j), true);
                        boardMatrix[i][j] = queen;
                    } else if (j == 4) {
                        King king = new King(new Pair<Integer, Integer>(i, j), true);
                        boardMatrix[i][j] = king;
                    }
                } else {
                    Pawn pawn = new Pawn(new Pair<Integer, Integer>(i, j), true);
                    boardMatrix[i][j] = pawn;
                }
            }
        }

        //----------------------------------------------------
        //setting the layout of the board to place all the 64 tiles:
        FlowLayout layout = (FlowLayout) super.getLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        this.len = (int) (min(W, H) * 0.8);
        this.size = new Dimension(len, len);

        //Initializing all the required tiles:
        boolean white = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //declaring and initializing a new Tile object and putting in the tileMatrix:
                tileMatrix[i][j] = new Tile(white, new Pair<Integer, Integer>(i, j), len, boardMatrix, tileMatrix);
                white = !white; //alternatively changing the color of the tile from white and black
            }
            white = !white; //alternatively changing the color of the tile from white and black as a new row is occurred
        }

        GameRules rules = new GameRules(boardMatrix);

        super.setPreferredSize(size);
        super.setOpaque(false);

    }

    @Override
    protected void paintComponent(final Graphics g) {
        boolean white = true;
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                //updating all the tiles as and when the Board is repainted or revalidated or re-rendered:
                //the tiles are kept up to date according to the boardMatrix[][]
                if(Tile.highlightedCoordinates!=null && Tile.highlightedCoordinates.contains(new Pair<>(x, y)))
                    tileMatrix[x][y].tileUpdate(white, new Pair<>(x, y), len, boardMatrix, tileMatrix);
                this.add(tileMatrix[x][y]);

                white = !white;
            }
            white = !white;
        }
    }
}
