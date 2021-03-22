package com.chessence.gui.pages.components;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class Board extends JPanel {
    private Dimension size;
    public Board(int W, int H){
        FlowLayout layout = (FlowLayout)super.getLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        int len = (int)(min(W, H)/1.3);
        this.size = new Dimension(len,len);
        super.setPreferredSize(size);
        setBackground(Color.BLACK);
        boolean white = true;
        for(char x = 'a';x<='h';x++){
            for(int y=1;y<=8;y++){
                this.add(new Tile(white,new Pair<>(x,y),len));
                white = !white;
            }
            white=!white;
        }
    }
}
