package com.chessence.gui.pages.components;

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
    private AbstractPiece boardMatrix[][] = new AbstractPiece[8][8];
    private ArrayList<Pair<Integer, Integer>> highlightedPositions = null;

    public Board(int W, int H) {
        //Initializing the board with all the pieces----------
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<8; j++)
            {
                if(i==0){
                    if(j==0 || j==7){
                        Rook rook = new Rook(new Pair<Integer, Integer>(i, j), "black");
                        boardMatrix[i][j] = rook;
                    }
                    else if(j==1 || j==6){
                        Knight knight = new Knight(new Pair<Integer, Integer>(i, j), "black");
                        boardMatrix[i][j] = knight;
                    }
                    else if(j==2 || j==5){
                        Bishop bishop = new Bishop(new Pair<Integer, Integer>(i, j), "black");
                        boardMatrix[i][j] = bishop;
                    }
                    else if(j==3){
                        Queen queen = new Queen(new Pair<Integer, Integer>(i, j), "black");
                        boardMatrix[i][j] = queen;
                    }
                    else if(j==4){
                        King king = new King(new Pair<Integer, Integer>(i, j), "black");
                        boardMatrix[i][j] = king;
                    }
                }
                else{
                    Pawn pawn = new Pawn(new Pair<Integer, Integer>(i, j), "black");
                    boardMatrix[i][j] = pawn;
                }

            }
        }

        for(int i=6; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                if(i==7){
                    if(j==0 || j==7){
                        Rook rook = new Rook(new Pair<Integer, Integer>(i, j), "white");
                        boardMatrix[i][j] = rook;
                    }
                    else if(j==1 || j==6){
                        Knight knight = new Knight(new Pair<Integer, Integer>(i, j), "white");
                        boardMatrix[i][j] = knight;
                    }
                    else if(j==2 || j==5){
                        Bishop bishop = new Bishop(new Pair<Integer, Integer>(i, j), "white");
                        boardMatrix[i][j] = bishop;
                    }
                    else if(j==3){
                        Queen queen = new Queen(new Pair<Integer, Integer>(i, j), "white");
                        boardMatrix[i][j] = queen;
                    }
                    else if(j==4){
                        King king = new King(new Pair<Integer, Integer>(i, j), "white");
                        boardMatrix[i][j] = king;
                    }
                }
                else{
                    Pawn pawn = new Pawn(new Pair<Integer, Integer>(i, j), "white");
                    boardMatrix[i][j] = pawn;
                }
            }
        }

        //----------------------------------------------------
        FlowLayout layout = (FlowLayout) super.getLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        this.len = (int) (min(W, H) * 0.8);
        this.size = new Dimension(len, len);
        super.setPreferredSize(size);
        super.setOpaque(false);

    }

    @Override
    protected void paintComponent(final Graphics g) {
        boolean white = true;
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                this.add(new Tile(white, new Pair<Integer, Integer>(x, y), len, boardMatrix, this));
                white = !white;
            }
            white = !white;
        }
    }

}
