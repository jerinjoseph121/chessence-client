package com.chessence.gui.pages;

import com.chessence.gui.pages.components.*;
import com.sun.scenario.effect.Color4f;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreenPanel extends ParentPanel implements ActionListener {
    public JButton button = new JButton();


    public GameScreenPanel(JFrame frame, CardLayout cardLayout){
        super(frame, cardLayout);
        this.setLayout(new GridLayout(1,2));
        //getting current frame size:
        Rectangle r = frame.getBounds();
        int heightOfFrame = r.height;
        int widthOfFrame = r.width;
        int width_chess_panel = (int)(widthOfFrame*0.8);
        int width_chat_panel = widthOfFrame - width_chess_panel;
        JPanel chess_panel = new JPanel();
        chess_panel.setPreferredSize(new Dimension(heightOfFrame,width_chess_panel));
        JPanel chat_panel = new JPanel();
        chat_panel.setPreferredSize(new Dimension(heightOfFrame,width_chat_panel-1));
        chat_panel.setBackground(new Color(0,0,255));
        this.add(chess_panel);
        this.add(chat_panel);
//        chess_panel.setBackground(new Color(0,0,0,0));
//        main_panel.setBackground(new Color(0x100101));
        //---------------------Design this panel here---------------------//

        //adding a small initial space in the top (above the heading)
        chess_panel.add(new HorizontalSpace(widthOfFrame, 7));

        //Adding heading (label):
        JLabel heading = new JLabel("Player 1");
        heading.setFont(getFont("Roboto-Medium", getResponsiveFontSize(68)));
        heading.setForeground(new Color(0xE79E4F));
        chess_panel.add(heading,BorderLayout.PAGE_START);
        chess_panel.add(new HorizontalSpace(widthOfFrame, 0));

        //adding a horizontal line:

        chess_panel.add(new Board(widthOfFrame,heightOfFrame));
        JLabel player2 = new JLabel("Player 2");
        player2.setFont(getFont("Roboto-Medium", getResponsiveFontSize(68)));
        player2.setForeground(new Color(0xE79E4F));
        chess_panel.add(player2,BorderLayout.PAGE_END);
//        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        chat_panel.add(new ChatBox(widthOfFrame/3,heightOfFrame/2));

        button.setBounds(500, 500, 100,50);
        button.addActionListener(this);
        button.setText("EXIT");
        chat_panel.add(button);


//        this.add(new HorizontalSpace(widthOfFrame, 0));




        //-----------------------End of designing------------------------//
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0x100101));
//        System.out.println("W:" + getSize().width + ", H:" + getSize().height);
        g2d.fillRect(0, 0, getSize().width, getSize().height);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button){
            frame.dispose();
        }
    }
}
