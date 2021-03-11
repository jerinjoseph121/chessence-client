package com.chessence.gui.pages.createRoomPanelComponents;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;


public class OnOffSwitch extends JPanel implements MouseListener{

    static String CREAM_ORANGE = "#E79E4F";
    static String PINK_MAROON = "#734046";
    int HEIGHT;
    int WIDTH;

    boolean isOn;

    public void paint(Graphics g){

        //creating the main sliding component
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        WIDTH = getSize().width;
        HEIGHT = getSize().height;
        g2D.setPaint(Color.decode(PINK_MAROON));
        g2D.fillArc(5, 7, WIDTH/2, HEIGHT-12, 90, 180);
        g2D.fillRect(WIDTH/4, 7, WIDTH/2, HEIGHT-12);
        g2D.fillArc((WIDTH/2) - 5, 7, WIDTH/2, HEIGHT-12, 90, -180);

        //changing graphics based on the value of isOn
        if(isOn){
            g2D.setPaint(Color.decode(CREAM_ORANGE));
            g2D.fillOval(8, 8, WIDTH/2, HEIGHT - 15);
            g2D.setPaint(Color.decode(PINK_MAROON));
            g2D.setFont(new Font("Roboto", Font.PLAIN, 15));
            g2D.drawString("ON", (WIDTH/5) - 2, HEIGHT/2 + 6);
        }
        else{
            g2D.setPaint(Color.decode(CREAM_ORANGE));
            g2D.fillOval(WIDTH/2 - 7, 8, WIDTH/2, HEIGHT - 15);
            g2D.setPaint(Color.decode(PINK_MAROON));
            g2D.setFont(new Font("Roboto", Font.PLAIN, 15));
            g2D.drawString("OFF", (WIDTH/2) - 3, HEIGHT/2 + 6);
        }
    }

    public OnOffSwitch(boolean isOn){

        this.isOn = isOn;
        this.setBackground(Color.BLACK);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(80, 50));
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        isOn = !isOn;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}

}
