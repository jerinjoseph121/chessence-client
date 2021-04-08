package com.chessence.gui.pages.components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import com.chessence.gui.pages.gameMechanics.AbstractPiece;
import javafx.util.Pair;

import javax.swing.*;

public class Tile extends JPanel {

    private JLabel imageLabel = new JLabel();

    private Color tileColor;
    private Pair<Integer, Integer> tileCoordinates;
    private boolean isHighlighted, isWhite;
    private AbstractPiece boardMatrix[][] = null;
    public static Pair<Integer, Integer> currentSelected = null;
    public static ArrayList<Pair<Integer, Integer>> highlightedCoordinates = null;
    private AbstractPiece piece;
    private String imagePath;
    private JPanel Board;
    private int len;

    public Tile(Boolean isWhite, Pair<Integer, Integer> tileCoordinates, int len, AbstractPiece boardMatrix[][], JPanel Board) {
        this.isHighlighted = false;
        this.boardMatrix = boardMatrix;
        this.Board = Board;
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

    @Override
    public void paintComponent(Graphics g) {
        this.piece = boardMatrix[this.tileCoordinates.getKey()][this.tileCoordinates.getValue()];
        if (piece != null) {
            revalidate();
            //loading the chess piece:
            imagePath = piece.getImagePath();
            imageLabel.setLayout(new BorderLayout());
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            ImageIcon ii = new ImageIcon(this.getClass().getResource(imagePath));
            imageLabel.setIcon(ii);
            this.add(imageLabel, BorderLayout.CENTER);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mouseClickAction();
                }
            });
        } else {
            Component[] componentList = this.getComponents();
            MouseListener[] mouseListenerList = this.getMouseListeners();
            //Loop through the components
            for (Component c : componentList) {
                //Find the components you want to remove
                if (c instanceof JLabel) {
                    //Remove it
                    this.remove(c);
                }
            }
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mouseClickAction();
                }
            });
        }
        super.paintComponent(g);
        isHighlighted = highlightedCoordinates != null && highlightedCoordinates.contains(this.tileCoordinates);
        var color = new Color(isHighlighted ? 0x61ABF0 : (isWhite ? 0xFFFFFF : 0x36454F));
        color = this.tileCoordinates == currentSelected ? new Color((isWhite ? 0x999999 : 0x141b1f)) : color;
        this.tileColor = color;
        this.setBackground(color);
        g.setColor(color);
        g.fill3DRect(0, 0, len, len, true);
    }

    private void mouseClickAction() {
        if (highlightedCoordinates != null && highlightedCoordinates.contains(tileCoordinates)) {
            boardMatrix[currentSelected.getKey()][currentSelected.getValue()].move(tileCoordinates, boardMatrix);
            highlightedCoordinates = null;
            currentSelected = null;
            piece = null;
        } else if (highlightedCoordinates != null && !highlightedCoordinates.contains(tileCoordinates)) {
            //clicking the non highlighted part...
            highlightedCoordinates = null;
        }
        currentSelected = tileCoordinates;
        if (piece != null) {
            highlightedCoordinates = piece.getValidDestinations(boardMatrix);
        }
        Board.revalidate();
        Board.repaint();
        this.repaint();
    }
}
