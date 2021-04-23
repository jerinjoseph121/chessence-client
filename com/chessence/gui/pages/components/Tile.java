package com.chessence.gui.pages.components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.chessence.gui.pages.gameMechanics.AbstractPiece;
import javafx.util.Pair;

import javax.swing.*;

public class Tile extends JPanel {

    //Initializing ALL the required variables:
    private JLabel imageLabel = new JLabel();
    public static boolean isUpdated = false;
    public Pair<Integer, Integer> tileCoordinates;
    private boolean isHighlighted, isWhite;
    private AbstractPiece boardMatrix[][] = null;
    public static Pair<Integer, Integer> currentSelected = null;
    public static boolean isCurrentTurn = true;
    public static ArrayList<Pair<Integer, Integer>> highlightedCoordinates = null;
    public static boolean isPlayerWhite = false;
    private Tile tileMatrix[][] = null;
    private AbstractPiece piece;
    private String imagePath;
    private int len;

    public Tile(Boolean isWhite, Pair<Integer, Integer> tileCoordinates, int len, AbstractPiece boardMatrix[][], Tile tileMatrix[][]) {
        tileUpdate(isWhite, tileCoordinates, len, boardMatrix, tileMatrix);
    }

    public void tileUpdate(Boolean isWhite, Pair<Integer, Integer> tileCoordinates, int len, AbstractPiece boardMatrix[][], Tile tileMatrix[][]) {

        //this function is like a constructor which initializes all the data members of this object to the values specified in the arguments

        //initializing all the data members:
        this.isHighlighted = highlightedCoordinates != null && highlightedCoordinates.contains(this.tileCoordinates);
        this.tileMatrix = tileMatrix;
        this.boardMatrix = boardMatrix;
        this.isWhite = isWhite;
        this.piece = boardMatrix[tileCoordinates.getKey()][tileCoordinates.getValue()];
        this.imagePath = piece != null ? piece.getImagePath() : null;
        this.setLayout(new BorderLayout());
        this.tileCoordinates = tileCoordinates;

        super.setBackground(Color.BLACK); //setting the background of this tile
        this.len = len / 8; //setting the length of the tile
        super.setPreferredSize(new Dimension(this.len, this.len));  //setting the dimensions of this tile

        //loading the chess piece image:
        if (imagePath != null) {
            JLabel imageLabel = new JLabel();
            imageLabel.setLayout(new BorderLayout());
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            ImageIcon ii = new ImageIcon(this.getClass().getResource(imagePath));
            imageLabel.setIcon(ii);
            this.add(imageLabel, BorderLayout.CENTER);  //adding the chess piece image in the center of the tile
        }

        //adding a mouselistener function that is called whenever this particular tile is clicked:
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickAction();
            }
        });
    }

    //the validateTiles function validates only those tiles mentioned in the arguments
    private void validateTiles(ArrayList<Pair<Integer, Integer>> highlightedCoordinates) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((highlightedCoordinates != null && highlightedCoordinates.contains(tileMatrix[i][j].tileCoordinates)) || (tileMatrix[i][j].tileCoordinates == currentSelected)) {
                    tileMatrix[i][j].validate();
                    tileMatrix[i][j].repaint();
                }

            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        //updating this.piece which represents the AbstractPiece object (or in simple words, the chess piece object) present on this tile:
        this.piece = boardMatrix[this.tileCoordinates.getKey()][this.tileCoordinates.getValue()];

        //if this tile is not validated, validate it:
        if (!isValid())
            this.validate();

        //if the current tile has a piece, then update all the highlighted tiles also
        //HIGHLIGHTED TILES are those tiles that turn blue to suggest you all the tiles that you can move your piece to.
        if (piece != null) {
            if (!isUpdated) {
                //isUpdated is a boolean variable that is false only when a mouse clicks on any tile, and
                //is immediately set to false after validating the required tiles so as to not do multiple unnecessary revalidations
                //yea.. java is weird.
                isUpdated = true;
                validateTiles(highlightedCoordinates);
            }

            //Removing the outdated image on the current tile:
            Component[] componentList = this.getComponents();
            for (Component c : componentList) {
                if (c instanceof JLabel) {
                    //the image is of the JLabel instance:
                    this.remove(c);
                }
            }

            //Adding the image of the new piece that's on the current tile:
            imagePath = piece.getImagePath();
            imageLabel.setLayout(new BorderLayout());
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            ImageIcon ii = new ImageIcon(this.getClass().getResource(imagePath));
            imageLabel.setIcon(ii);
            this.add(imageLabel, BorderLayout.CENTER);

        } else {
            //Only remove and DON'T ADD a new image - if a piece has moved away from this tile:
            Component[] componentList = this.getComponents();
            for (Component c : componentList) {
                if (c instanceof JLabel) {
                    this.remove(c);
                }
            }
        }

        //Updating the mouse listener function cuz the pieces have changed:
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickAction();
            }
        });

        super.paintComponent(g);

        //setting the appropriate color of the tile:
        isHighlighted = highlightedCoordinates != null && highlightedCoordinates.contains(this.tileCoordinates);
        var color = new Color(isHighlighted ? 0x61ABF0 : (isWhite ? 0xFFFFFF : 0x36454F));
        color = this.tileCoordinates == currentSelected ? new Color((isWhite ? 0x999999 : 0x26333b)) : color;
        this.setBackground(color);
        g.setColor(color);
        g.fill3DRect(0, 0, len, len, true);
    }

    private void mouseClickAction() {
        //check if it is current player's turn:
        if (isCurrentTurn) {
            //immediately set isUpdated to false to stop other tiles form unnecessarily updating:
            isUpdated = false;

            //store the previously selected tile coordinates, and the coordinates of the previously highlighted tiles:
            var prevSelected = currentSelected;
            var prevHighlightedCoordinates = highlightedCoordinates != null ? (ArrayList<Pair<Integer, Integer>>) highlightedCoordinates.clone() : null;

            //if a piece has been moved:
            if (highlightedCoordinates != null && highlightedCoordinates.contains(tileCoordinates)) {
                //set current player's turn to false after moving:
                //Tile.isCurrentTurn = false;

                //update the boardMatrix with the move instruction:
                boardMatrix[currentSelected.getKey()][currentSelected.getValue()].move(tileCoordinates, boardMatrix);

                //set all the variables to null has the piece is no more in the current tile:
                highlightedCoordinates = null;
                currentSelected = null;
                piece = null;
            }
            //if the tiles were highlighted to suggest all the destinations of a piece, and if the user clicks on any tile OTHER THAN the highlighted tiles:
            else if (highlightedCoordinates != null && !highlightedCoordinates.contains(tileCoordinates)) {
                highlightedCoordinates = null;
            }

            //set the currentSelected coordinates to the current tile coordinates:
            currentSelected = tileCoordinates;

            //reset the highlighted coordinates to null:
            highlightedCoordinates = null;

            //validate all the previously highlighted tiles so they're now not highlighted anymore cuz we set all the highlighted tiles to null:
            validateTiles(prevHighlightedCoordinates);

            //validate the previously selected tile:
            if (prevSelected != null && !isUpdated) {
                tileMatrix[prevSelected.getKey()][prevSelected.getValue()].validate();
                tileMatrix[prevSelected.getKey()][prevSelected.getValue()].repaint();
            }

            //if the current tile has a piece, and if it is clicked, update the highlightedCoordinates variable to show the possible destinations of the current tile:
            if (piece != null && piece.isWhite() == Tile.isPlayerWhite) {
                highlightedCoordinates = piece.getValidDestinations(boardMatrix);
            }
        }
    }
}
