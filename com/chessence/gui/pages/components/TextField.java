package com.chessence.gui.pages.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class TextField extends JTextField {
    private Shape shape;

    public TextField(int width, int height, String initialValue) {
        super(initialValue);
        setOpaque(false); // As suggested by @AVD in comment.
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(0x150308));
        this.setForeground(new Color(0xE79E4F));
        this.setMargin(new Insets(2, 10, 2, 10));
        this.setHorizontalAlignment(JTextField.CENTER);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        //g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
        }
        return shape.contains(x, y);
    }
}
