package com.chessence.gui.pages.components;

import com.chessence.gui.pages.ParentPanel;
import com.chessence.gui.pages.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Specs extends JPanel {
    private final JButton spectators = new RoundedButton("S P E C T A T O R S ➽",new Color(0xA05344), new Color(0x8A3D2E), 15);
    private Dimension size;
    JLayeredPane spec_panel = new JLayeredPane();

    protected boolean hidden = false;

    public Specs(int W, int H) {

        this.size = new Dimension(W,H/8);
        super.setLayout(new FlowLayout(FlowLayout.RIGHT,25,55));
        super.setPreferredSize(size);

        //--------------------- OVERLAP PANEL FOR LIST OF SPECTATORS--------------------

        spec_panel.setPreferredSize(new Dimension(W,H));

        spec_panel.setOpaque(true);
        spec_panel.setBackground(new Color(255,0,0,100));
        spec_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        RoundedButton x = new RoundedButton("B A C K ",new Color(0xA05344), new Color(0x8A3D2E), 15);
        spec_panel.add(x);
//        x.setBounds(0,0,W/2,50);
        this.add(spec_panel);
        spec_panel.setVisible(false);

        //--------------------- SPECTATOR'S BUTTON----------------------
        spectators.setPreferredSize(new Dimension(size.width/2,size.height/3));
        spectators.setFont(new Font("Geneva", Font.PLAIN,15));
        spectators.setForeground(new Color(0xFFAE56));
        spectators.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                spec_panel.setVisible(true);
                ((JButton) e.getSource()).setEnabled(false);
                new Timer(1, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spec_panel.setLocation(spec_panel.getWidth(),0);
                        if(spec_panel.getX()==spec_panel.getWidth()){
                            ((Timer) e.getSource()).stop();
                            System.out.println("dONE");
                            hidden = true;
                        }
                    }
                }).start();

            }
        });


        spectators.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                slide(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.add(spectators);
        super.setOpaque(false);
    }


    private void slide(boolean show) {
        super.setPreferredSize(new Dimension(size.width,(int)size.height*8));
        new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spec_panel.setLocation(spec_panel.getX() - 50, 0);

                System.out.println(spec_panel.getX());
                System.out.println(spec_panel.getWidth());
                if (hidden && spec_panel.getX() <= 0) {
                    spec_panel.setLocation(0, 0);
                    ((Timer) e.getSource()).stop();
                    System.out.println("Timer 2 stopped");
                }
            }
        }).start();
    }


}
