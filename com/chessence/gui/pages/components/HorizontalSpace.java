package com.chessence.gui.pages.components;

import javax.swing.*;
import java.awt.*;

public class HorizontalSpace extends JPanel
{
    private int width = 0;
    private int height = 0;

    public HorizontalSpace(int width, int height)
    {
        super();

        this.width = width;
        this.height = height;

        this.setLayout(new FlowLayout());
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(width, height));
    }
}
