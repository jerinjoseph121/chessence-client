package com.chessence.gui.pages.components;

import javax.swing.*;
import java.awt.*;

public class HorizontalLine extends JPanel
{
    private int width;
    private int thickness;
    private Color color;

    public HorizontalLine(int width, int thickness, Color color)
    {
        super();

        this.width = width;
        this.thickness = thickness;
        this.color = color;

        this.setLayout(new FlowLayout());
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(width, thickness));
    }

    @Override public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        //Get the current size of this component
        Dimension d = this.getSize();
        //draw in black
        g2d.setColor(color);
        //draw a centered horizontal line
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawLine(0,0, width,0);
    }
}
