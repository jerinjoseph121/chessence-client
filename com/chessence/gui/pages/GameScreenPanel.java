package com.chessence.gui.pages;

import com.chessence.gui.pages.components.*;
import com.sun.scenario.effect.Color4f;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameScreenPanel extends ParentPanel implements ActionListener {
    public JButton EXIT = new RoundedButton((CreateRoomPanel.Player_Status == 'P'?"Forfeit Match" : "Leave Lobby"),new Color(0xE79E4F), new Color(0xB8742A), 15);

    public GameScreenPanel(JFrame frame, CardLayout cardLayout){
        super(frame, cardLayout);
        frame.setLocationRelativeTo(null);
        //Getting frame dimensions:
        Rectangle r = frame.getBounds();
        int heightOfFrame = r.height;
        int widthOfFrame = r.width;

        //Setting the layout of the main entire panel as borderLayout
        this.setLayout(new BorderLayout());

        //Determining the width of the left and right panel:
        int width_chess_panel = (int)(widthOfFrame*0.65);
        int width_chat_panel = widthOfFrame - width_chess_panel;

        //splitting the whole screen into a leftPanel and a rightPanel:
        JPanel chess_panel = new JPanel();
        chess_panel.setPreferredSize(new Dimension(width_chess_panel, heightOfFrame));
        chess_panel.setOpaque(false);
        JPanel chat_panel = new JPanel();
        chat_panel.setPreferredSize(new Dimension((widthOfFrame - width_chess_panel), heightOfFrame));
        chat_panel.setOpaque(false);


        //---------------------Design this panel here---------------------//

        //============DESIGNING THE CHESS_PANEL============

        //adding a small initial space in the top (above the heading)
        chess_panel.add(new HorizontalSpace(widthOfFrame, 7));

        //Heading (PLAYER 1):
        JLabel player1 = new JLabel("Player 1");
        player1.setFont(getFont("Roboto-Medium", getResponsiveFontSize(68)));
        player1.setForeground(new Color(0xE79E4F));
        chess_panel.add(player1, BorderLayout.PAGE_START);

        //adding horizontal space of 0 so next component goes to next line:
        chess_panel.add(new HorizontalSpace(widthOfFrame, 0));

        //adding the chess board:
        chess_panel.add(new Board(width_chess_panel, heightOfFrame), BorderLayout.CENTER);

        //adding horizontal space of 0 so next component goes to next line:
        chess_panel.add(new HorizontalSpace(widthOfFrame, 0));

        //Heading (PLAYER 2):
        JLabel player2 = new JLabel("Player 2");
        player2.setFont(getFont("Roboto-Medium", getResponsiveFontSize(68)));
        player2.setForeground(new Color(0xE79E4F));
        chess_panel.add(player2, BorderLayout.PAGE_END);

        //============DESIGNING THE CHAT PANEL============

        //setting layout of this panel as FlowLayout.CENTER
        chat_panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //SPECTATORS PANEL:
        chat_panel.add(new Specs(width_chat_panel,heightOfFrame));

        //adding horizontal space of 0 so next component goes to next line:
        chat_panel.add(new HorizontalSpace(widthOfFrame, 0));

        //adding the chat box:
        chat_panel.add(new ChatBox((int)(width_chat_panel*0.9),(int)(heightOfFrame/1.9)),FlowLayout.CENTER);

        //adding horizontal space of 0 so next component goes to next line:
        chat_panel.add(new HorizontalSpace((int)(widthOfFrame), 0));

        //---------------- MUTE AND DEAFEN BUTTONS----------------------
        chat_panel.add(new FunctionalButtons(width_chat_panel,heightOfFrame));

        //Making the "EXIT" button:
        EXIT.addActionListener(this);
        EXIT.setForeground(new Color(0x841522));
        EXIT.setFont(getFont("Rambla-Bold",25));
        EXIT.setPreferredSize(new Dimension((int)(width_chat_panel*0.9),heightOfFrame/15));
        chat_panel.add(EXIT);

        //ADDING THE LEFT AND RIGHT PANEL TO THE MAIN PANEL:
        this.add(chess_panel, BorderLayout.WEST);
        this.add(chat_panel, BorderLayout.EAST);

        //-----------------------End of designing------------------------//
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Paint p = new GradientPaint(getWidth()*0.2f, getHeight()*0.1f, new Color(21, 3, 8, 255),
                0.0f, getHeight(), new Color(208, 108, 120, 255), false);
        g2d.setPaint(p);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==EXIT){
            frame.dispose();
        }
    }
}
