package com.chessence.gui.pages.components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.chessence.gui.pages.gameMechanics.AbstractPiece;
import javafx.util.Pair;

import javax.swing.*;

public class Tile extends JPanel {

    private JLabel imageLabel = new JLabel();

    private Color tileColor;
    public static boolean isUpdated = false;
    public Pair<Integer, Integer> tileCoordinates;
    private boolean isHighlighted, isWhite;
    private AbstractPiece boardMatrix[][] = null;
    public static Pair<Integer, Integer> currentSelected = null;
    public static ArrayList<Pair<Integer, Integer>> highlightedCoordinates = null;
    private Tile tileMatrix[][] = null;
    private AbstractPiece piece;
    private String imagePath;
    private int len;

    public Tile(Boolean isWhite, Pair<Integer, Integer> tileCoordinates, int len, AbstractPiece boardMatrix[][], Tile tileMatrix[][]) {
        tileUpdate(isWhite, tileCoordinates, len, boardMatrix, tileMatrix);
    }

    public void tileUpdate(Boolean isWhite, Pair<Integer, Integer> tileCoordinates, int len, AbstractPiece boardMatrix[][], Tile tileMatrix[][]){
        this.isHighlighted = highlightedCoordinates != null && highlightedCoordinates.contains(this.tileCoordinates);
        this.tileMatrix = tileMatrix;
        this.boardMatrix = boardMatrix;
        this.isWhite = isWhite;
        this.piece = boardMatrix[tileCoordinates.getKey()][tileCoordinates.getValue()];
        this.imagePath = piece != null ? piece.getImagePath() : null;
        this.setLayout(new BorderLayout());

        this.tileCoordinates = tileCoordinates;
        this.tileColor = new Color(isHighlighted ? 0x61ABF0 : (isWhite ? 0xFFFFFF : 0x36454F));
        super.setBackground(Color.BLACK);
        this.len = len / 8;
        super.setPreferredSize(new Dimension(this.len, this.len));

        if (imagePath != null) {
            //loading the chess piece:
            JLabel imageLabel = new JLabel();
            imageLabel.setLayout(new BorderLayout());
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            ImageIcon ii = new ImageIcon(this.getClass().getResource(imagePath));
            imageLabel.setIcon(ii);
            this.add(imageLabel, BorderLayout.CENTER);
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickAction();
            }
        });
    }

    private void validateTiles(ArrayList<Pair<Integer, Integer>> highlightedCoordinates)
    {
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                if((highlightedCoordinates != null && highlightedCoordinates.contains(tileMatrix[i][j].tileCoordinates)) || (tileMatrix[i][j].tileCoordinates==currentSelected)){
                    tileMatrix[i][j].validate();
                    tileMatrix[i][j].repaint();
                }

            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        //System.out.println("Validating "+this.tileCoordinates);
        this.piece = boardMatrix[this.tileCoordinates.getKey()][this.tileCoordinates.getValue()];
        //this.val
        if(!isValid())
            this.validate();

        if (piece != null) {
            //updating the required tiles:
            if(!isUpdated)
            {
                isUpdated = true;
                validateTiles(highlightedCoordinates);
            }
            //Board.revalidate();
            //Board.repaint();
            //loading the chess piece:
            Component[] componentList = this.getComponents();
            //Loop through the components
            for (Component c : componentList) {
                //Find the components you want to remove
                if (c instanceof JLabel) {
                    //Remove it
                    this.remove(c);
                }
            }

            imagePath = piece.getImagePath();
            imageLabel.setLayout(new BorderLayout());
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            ImageIcon ii = new ImageIcon(this.getClass().getResource(imagePath));
            imageLabel.setIcon(ii);
            this.add(imageLabel, BorderLayout.CENTER);

        } else {
            //this.validate();
            Component[] componentList = this.getComponents();
            //Loop through the components
            for (Component c : componentList) {
                //Find the components you want to remove
                if (c instanceof JLabel) {
                    //Remove it
                    this.remove(c);
                }
            }
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickAction();
            }
        });

        super.paintComponent(g);
        isHighlighted = highlightedCoordinates != null && highlightedCoordinates.contains(this.tileCoordinates);
        var color = new Color(isHighlighted ? 0x61ABF0 : (isWhite ? 0xFFFFFF : 0x36454F));
        color = this.tileCoordinates == currentSelected ? new Color((isWhite ? 0x999999 : 0x26333b)) : color;
        this.tileColor = color;
        this.setBackground(color);
        g.setColor(color);
        g.fill3DRect(0, 0, len, len, true);
    }

    private void mouseClickAction() {
        isUpdated = false;
        var prevSelected = currentSelected;
        var prevHighlightedCoordinates = highlightedCoordinates!=null ? (ArrayList<Pair<Integer, Integer>>)highlightedCoordinates.clone() : null;
        //System.out.println("Previous highligted: " + prevHighlightedCoordinates);
        if (highlightedCoordinates != null && highlightedCoordinates.contains(tileCoordinates)) {
            boardMatrix[currentSelected.getKey()][currentSelected.getValue()].move(tileCoordinates, boardMatrix);
            highlightedCoordinates = null;
            currentSelected = null;
            piece = null;
        } else if (highlightedCoordinates != null && !highlightedCoordinates.contains(tileCoordinates)) {
            highlightedCoordinates = null;
        }
        currentSelected = tileCoordinates;
        highlightedCoordinates = null;
        validateTiles(prevHighlightedCoordinates);
        if(prevSelected!=null && !isUpdated) {
            tileMatrix[prevSelected.getKey()][prevSelected.getValue()].validate();
            tileMatrix[prevSelected.getKey()][prevSelected.getValue()].repaint();
        }

        if (piece != null) {
            highlightedCoordinates = piece.getValidDestinations(boardMatrix);
        }
    }
}
