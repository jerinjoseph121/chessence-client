package com.chessence.gui.pages;

import com.chessence.gui.pages.components.HorizontalLine;
import com.chessence.gui.pages.components.HorizontalSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreenPanel extends ParentPanel implements ActionListener {
    public JButton button = new JButton();


    public GameScreenPanel(JFrame frame, CardLayout cardLayout){
        super(frame, cardLayout);

        //getting current frame size:
        Rectangle r = frame.getBounds();
        int heightOfFrame = r.height;
        int widthOfFrame = r.width;

        //---------------------Design this panel here---------------------//

        //adding a small initial space in the top (above the heading)
        this.add(new HorizontalSpace(widthOfFrame, 7));

        //Adding heading (label):
        JLabel heading = new JLabel("GAME SCREEN");
        heading.setFont(getFont("Roboto-Medium", getHeadingFontSize()));
        heading.setForeground(new Color(0x895158));
        this.add(heading);
        this.add(new HorizontalSpace(widthOfFrame, 0));

        //adding a horizontal line:
        this.add(new HorizontalLine((int) (0.90 * widthOfFrame), 5, new Color(0x895158)));
        this.add(new HorizontalSpace(widthOfFrame, (int) (heightOfFrame * 0.1)));

        button.setBounds(500, 500, 100,50);
        button.addActionListener(this);
        button.setText("EXIT");
        this.add(button);

        //-----------------------End of designing------------------------//
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button){
            frame.dispose();
        }
    }
}
