package com.chessence.gui.pages.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FunctionalButtons extends JPanel {
    private final RoundedButton mute = new RoundedButton("",new Color(0xC88275), new Color(187,121,109,90), 100);
    private final RoundedButton deafen = new RoundedButton("",new Color(0xC88275), new Color(0x8A3D2E), 100);
    public boolean muted = false;
    public boolean deafened = false;

    public FunctionalButtons(int W, int H) {

        Dimension button_size = new Dimension((int) (W * 0.2), (int) (W * 0.2));

        ImageIcon mute_icon = new ImageIcon(this.getClass().getResource("../../images/mute.png"));
        ImageIcon strike = new ImageIcon(this.getClass().getResource("../../images/strike.png"));
//        mute_icon = new ImageIcon(mute_icon.getImage().getScaledInstance((int)(button_size.height*0.5),(int)(button_size.height*0.7),java.awt.Image.SCALE_SMOOTH));

        mute.setIcon(mute_icon);
        Border emptyBorder = BorderFactory.createEmptyBorder(4,4,4,4);
        mute.setBorder(emptyBorder);
//        mute.setBorderPainted(false);
//        mute.setIconTextGap(0);
//        mute.setText("Mute");
//        mute.setVerticalTextPosition(SwingConstants.BOTTOM);
//        mute.setHorizontalTextPosition(SwingConstants.CENTER);
//        mute.setForeground(new Color(0xFCB36C));
        ImageIcon deafen_icon = new ImageIcon(this.getClass().getResource("../../images/deafen.png"));
        deafen.setIcon(deafen_icon);


        mute.setPreferredSize(button_size);
        deafen.setPreferredSize(button_size);
        super.setPreferredSize(new Dimension(W, H/6));
        super.setOpaque(false);
        super.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
        super.add(mute);
        super.add(deafen);

        //adding event listeners
        mute.addActionListener(e -> {
            mute.setIcon(muted ? mute_icon : strike);
            muted = !muted;
        });

        deafen.addActionListener(e -> {
            deafen.setIcon(deafened ? deafen_icon : strike);
            deafened = !deafened;
        });
    }
}
