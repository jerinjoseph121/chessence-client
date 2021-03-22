package com.chessence.gui.pages.components;

import java.awt.*;
import javafx.util.Pair;
import javax.swing.*;

public class Tile /*extends Piece*/ extends JPanel{
    private  Color tileColor;
//    private Piece piece;
    private  Pair<Character, Integer> tileCoord;
    private int len;

    public Tile(Boolean isWhite, Pair<Character, Integer> coord, int len){
        this.tileCoord=coord;
        this.tileColor =  new Color(isWhite? 0xFFFFFF : 0x36454F);
        super.setBackground(Color.BLACK);
        this.len = len/8;
        super.setPreferredSize(new Dimension(this.len, this.len));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here
        g.setColor(this.tileColor);
        g.fill3DRect(0, 0, len, len,true);
    }


}
