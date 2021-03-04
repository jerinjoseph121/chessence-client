package com.chessence.gui.pages;

import com.chessence.gui.pages.components.HorizontalLine;
import com.chessence.gui.pages.components.HorizontalSpace;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends ParentPanel implements ActionListener {

    public JButton button = new JButton();

    public MainMenuPanel(JFrame frame, CardLayout cardLayout){
        super(frame, cardLayout);

        //getting current frame size:
        Rectangle r = frame.getBounds();
        int heightOfFrame = r.height;
        int widthOfFrame = r.width;

        //adding a small initial space in the top (above the heading)
        this.add(new HorizontalSpace(widthOfFrame, 7));

        //Adding heading (label):
        JLabel label = new JLabel();
        label.setText("Chessence");
        label.setFont(getFont("Roboto-Medium", 68));
        label.setForeground(new Color(0x895158));
        this.add(label);
        this.add(new HorizontalSpace(widthOfFrame, 0));

        //adding a horizontal line:
        this.add(new HorizontalLine((int)(0.85*widthOfFrame), 5, new Color(0x895158)));
        this.add(new HorizontalSpace(widthOfFrame, 0));

        button.setBounds(500, 500, 100,50);
        button.addActionListener(this);
        button.setText("EXIT");
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("Button clicked!");
            frame.dispose();    //close the frame
        }
    }

}
