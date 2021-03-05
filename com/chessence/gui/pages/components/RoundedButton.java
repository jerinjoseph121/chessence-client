package com.chessence.gui.pages.components;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RoundedButton extends JButton {

    private final Color backgroundColor;
    int cornerRadius;

    public RoundedButton(String label, Color backgroundColor, Color hoverColor, int cornerRadius) {
        super(label);

        this.backgroundColor = backgroundColor;
        this.cornerRadius = cornerRadius;
        this.setFocusable(false);
        this.setBackground(backgroundColor);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width,
                size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);

        RoundedButton current = this;
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                current.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                current.setBackground(backgroundColor);
            }
        });
    }

    // Paint the round background and label.
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            // You might want to make the highlight color
            // a property of the RoundButton class.
            g.setColor(backgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width - 1,
                getSize().height - 1, cornerRadius, cornerRadius);

        // This call will paint the label and the
        // focus rectangle.
        super.paintComponent(g);
    }

    // Paint the border of the button using a simple stroke.
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getSize().width - 1,
                getSize().height - 1, cornerRadius, cornerRadius);
    }

    // Hit detection.
    Shape shape;

    public boolean contains(int x, int y) {
    // If the button has changed size,
        // make a new shape object.
        if (shape == null ||
                !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0,
                    getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}