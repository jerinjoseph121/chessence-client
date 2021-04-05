package com.chessence.gui.pages.components;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.max;

public class ChatBox extends JPanel {
    private Dimension size;

    public ChatBox(int W,int H){
        this.setLayout(new BorderLayout(10,10));
        this.size = new Dimension(W,H);
        super.setPreferredSize(size);
        super.setOpaque(false);
        setBackground(new Color(0xC88275));
        JTextPane msgbox = new JTextPane();
        msgbox.setPreferredSize(new Dimension(W,H/10));
        msgbox.setCaretColor(new Color(0xD79E92));
//        super.add(msgbox,BorderLayout.SOUTH);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(getBackground());
        g.fillRoundRect(0, 0, super.getWidth() - 1 ,
                super.getHeight() - 1 , 20, 20);

    }
}
