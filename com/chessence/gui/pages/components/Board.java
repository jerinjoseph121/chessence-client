package com.chessence.gui.pages.components;

import com.chessence.gui.pages.gameMechanics.AbstractPiece;
import com.chessence.gui.pages.gameMechanics.King;
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
                King king = new King(new Pair<Integer, Integer>(i, j), "black");
                boardMatrix[i][j] = king;
            }
        }

        for(int i=6; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                King king = new King(new Pair<Integer, Integer>(i, j), "white");
                boardMatrix[i][j] = king;
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
