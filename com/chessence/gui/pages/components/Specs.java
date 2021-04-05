package com.chessence.gui.pages.components;

import com.chessence.gui.pages.CreateRoomPanel;

import javax.swing.*;
import java.awt.*;

public class Specs extends JPanel {
    private final JButton spectators = new RoundedButton("S P E C T A T O R S ➽",new Color(0xA05344), new Color(0x8A3D2E), 15);
    private final Dimension size;
    JLayeredPane spec_panel = new JLayeredPane();

    protected boolean hidden = false;

    public Specs(int W, int H) {

        this.size = new Dimension(W,H/10);
        super.setLayout(new FlowLayout(FlowLayout.RIGHT,25,20));
        super.setPreferredSize(size);

        //--------------------- OVERLAP PANEL FOR LIST OF SPECTATORS--------------------

        spec_panel.setPreferredSize(new Dimension(W,H));

        spec_panel.setOpaque(true);
        spec_panel.setBackground(new Color(255,0,0,100));
        spec_panel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,0));
        spec_panel.add(new HorizontalSpace(size.width,size.height/2));
        RoundedButton back = new RoundedButton("⟸ B A C K ",new Color(0xA05344), new Color(0x8A3D2E), 15);
        spec_panel.add(back,FlowLayout.RIGHT);
        back.setPreferredSize(new Dimension(size.width/2,size.height/3));
        back.setForeground(new Color(0xFFAE56));
        back.addActionListener(e -> slide(false));
        spec_panel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,0));
        spec_panel.add(new HorizontalSpace(size.width,size.height*2));
        for (String item :CreateRoomPanel.SPECTATORS){

            RoundedButton new_spec = new RoundedButton(item,new Color(0xA05344), new Color(0x8A3D2E), 0);
            new_spec.setPreferredSize(new Dimension((int)(size.width*0.8),size.height/2));
            new_spec.setForeground(new Color(0xfcc9be));
            spec_panel.add(new_spec,FlowLayout.TRAILING);

        }
        spec_panel.add(new HorizontalSpace(size.width,size.height*3));
        this.add(spec_panel);
        spec_panel.setVisible(false);

        //--------------------- SPECTATOR'S BUTTON----------------------
        spectators.setPreferredSize(new Dimension(size.width/2,size.height/2));
        spectators.setFont(new Font("Geneva", Font.PLAIN,15));
        spectators.setForeground(new Color(0xFFAE56));
        spectators.addActionListener(e -> {
            spec_panel.setVisible(true);
            slide(true);
            new Timer(1, e1 -> {
                spec_panel.setLocation(spec_panel.getWidth(),0);
                if(spec_panel.getX()==spec_panel.getWidth()){
                    ((Timer) e1.getSource()).stop();
                    System.out.println("dONE");
                    hidden = true;
                }
            }).start();

        });

        this.add(spectators);
        super.setOpaque(false);
    }


    private void slide(boolean show) {

        super.setPreferredSize((show? new Dimension(size.width, size.height *10) : size));
        new Timer(1, e -> {
            if(show){
                spec_panel.setLocation(spec_panel.getX() - 50, 0);
                if (hidden && spec_panel.getX() <= 0) {
                    spec_panel.setLocation(0, 0);
                    ((Timer) e.getSource()).stop();
                    hidden = false;
                }
            }else{
                spec_panel.setLocation(spec_panel.getX() + 50, 0);
                if (spec_panel.getX() >= spec_panel.getWidth()) {
                    ((Timer) e.getSource()).stop();
                    spec_panel.setVisible(false);
                }
            }
        }).start();
    }

}
