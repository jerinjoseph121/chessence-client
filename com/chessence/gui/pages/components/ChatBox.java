package com.chessence.gui.pages.components;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.max;

public class ChatBox extends JPanel {
    private Dimension size;
    public ChatBox(int W,int H){
//        FlowLayout layout = (FlowLayout)super.getLayout();
//        layout.setVgap(0);
//        layout.setHgap(0);
        this.setLayout(new BorderLayout());
        this.size = new Dimension(W,H);
        super.setPreferredSize(size);
        setBackground(new Color(0xC88275));
        JTextPane msgbox = new JTextPane();
        msgbox.setPreferredSize(new Dimension(W,H/10));
        msgbox.setCaretColor(new Color(0xD79E92));
        this.add(msgbox,BorderLayout.SOUTH);
    }
}
