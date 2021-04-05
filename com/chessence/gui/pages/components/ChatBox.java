package com.chessence.gui.pages.components;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

import static java.lang.Math.max;

public class ChatBox extends JPanel {
    private Dimension size;
    JPanel box = new JPanel();

    public ChatBox(int W,int H){
        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        this.size = new Dimension(W,H);
        super.setPreferredSize(size);
        super.setOpaque(false);
        setBackground(new Color(0xC88275));

        //------------------------- LABEL---------------------------
        JLabel label = new JLabel("C h a t");
        label.setFont(new Font("Colonna MT",Font.BOLD,25));
        label.setForeground(new Color(0x321F28));
        this.add(label, BorderLayout.PAGE_END);
        this.add(new HorizontalLine((int) (W*0.95),6,new Color(0x321F28)));

        //------------------------MESSAGES--------------------------
        //------------------------TEXT BOX--------------------------
        JTextPane msgbox = new JTextPane();
        Border roundedBorder = new LineBorder(new Color(0x734046), 3, true); // the third parameter - true, says it's round
        msgbox.setBorder(roundedBorder);
        msgbox.setEditable(true);
        msgbox.setPreferredSize(new Dimension((int) (W*0.95),H/10));
//        msgbox.setLayout(new BorderLayout(BorderLayout.SOUTH));
//        msgbox.setText("Write a message...",BorderLayout.SOUTH);

        msgbox.setBackground(new Color(0xD79E92));
        msgbox.setBounds(0,H-msgbox.getHeight(), msgbox.getWidth(), msgbox.getHeight());
        box.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        box.setOpaque(false);
        box.setPreferredSize(new Dimension(W,H/9));
        box.add(msgbox);
        super.add(box,BorderLayout.SOUTH);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        msgbox.paintComponents(g);
        g.setColor(getBackground());

        g.fillRoundRect(0, 0, getSize().width - 1 ,
                getSize().height - 1 , 20, 20);



    }
}
