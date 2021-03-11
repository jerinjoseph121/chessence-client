package com.chessence.gui.pages.createRoomPanelComponents;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RoundedButton extends JButton {

    private final Color backgroundColor;
    private final Color disabledBackgroundColor;
    int cornerRadius;

    public RoundedButton(String label, Font font, Color fontColor, Color backgroundColor, Color hoverColor, int cornerRadius, Dimension size) {
        super(label);

        this.setText(label);
        this.disabledBackgroundColor = Color.decode("#777777");
        this.backgroundColor = backgroundColor;
        this.cornerRadius = cornerRadius;
        this.setFocusable(false);
        this.setFont(font);
        this.setBackground(backgroundColor);
        this.setForeground(fontColor);
        this.setBorderPainted(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(size);
        setContentAreaFilled(false);

        RoundedButton current = this;
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                current.setBackground(hoverColor);
                repaint();
                revalidate();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                current.setBackground(backgroundColor);
                repaint();
                revalidate();
            }
        });
    }

    // Paint the round background and label.
    protected void paintComponent(Graphics g) {

        //Changing the background of button when its not enabled
        if(!this.isEnabled()){
            this.setBackground(disabledBackgroundColor);
        }

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