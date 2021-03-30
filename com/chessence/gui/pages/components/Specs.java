package com.chessence.gui.pages.components;

import com.chessence.gui.pages.ParentPanel;
import com.chessence.gui.pages.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Specs extends JPanel implements ActionListener {
    private final JButton spectators = new RoundedButton("S P E C T A T O R S ➽",new Color(0xA05344), new Color(0x8A3D2E), 15);
    private Dimension size;
    JLayeredPane spec_panel = new JLayeredPane();

    protected boolean hidden = true;

    public Specs(int W, int H) {

        this.size = new Dimension(W,H/8);
        super.setLayout(new FlowLayout(FlowLayout.RIGHT,25,55));
        super.setPreferredSize(size);

        //--------------------- OVERLAP PANEL FOR LIST OF SPECTATORS--------------------

        spec_panel.setPreferredSize(new Dimension(W,H));
//        this.setLayout(null);

        spec_panel.setOpaque(true);
        spec_panel.setBackground(new Color(255,0,0,100));
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
                        spec_panel.setLocation(spec_panel.getX() - 1, 0);
                        if (spec_panel.getX() - spec_panel.getWidth() == 0) {
                            ((Timer) e.getSource()).stop();
                            System.out.println("Timer stopped");
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

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                slide(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.add(spectators);
        super.setOpaque(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void slide(boolean show) {
//        super.setPreferredSize(new Dimension(size.width,(int)size.height*8));
//        final int w = spec_panel.getWidth();
//        final int h = spec_panel.getHeight();
//        final Point p1 = spec_panel.getLocation();
//        final Point p2 = new Point(0, 0);
//        int step = 1;
//        if(show){
//            while(p1.x>p2.x){
//                p1.x -=step;
//                spec_panel.setLocation(p1);
//            }
//        }else{
//            p2.x=w;
//            while(p1.x<p2.x){
//                p1.x +=step;
//                spec_panel.setLocation(p1);
//            }
//        }
    }


}
