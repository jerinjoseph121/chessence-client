package com.chessence.gui;

import com.chessence.gui.pages.CreateRoomPanel;
import com.chessence.gui.pages.GameScreenPanel;
import com.chessence.gui.pages.JoinRoomPanel;
import com.chessence.gui.pages.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class GuiMain extends JFrame{

    public JButton button = new JButton();
    public JFrame frame = new JFrame();
    public JPanel cards;
    private CardLayout cl = new CardLayout();

    public void startUp(){
        //Setting frame properties:
        frame.setLayout(null);  //setting no specific layout property as of now
        frame.setTitle("Chessence");    //setting title of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //close the frame when exited
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //setting full screen
        frame.setUndecorated(true);
        frame.setResizable(true); //prevent frame to be resized
        frame.setVisible(true); //setting the frame to be visible
        frame.getContentPane().setBackground(new Color(0x734046)); //setting background color

        Container container = frame.getContentPane();   //getting the content area of the frame
        CardLayout cardLayout = new CardLayout();   //setting a cardlayout variable
        container.setLayout(cardLayout);    //setting the layout of the content area as that of CardLayout

        //creating the panels:
        JPanel mainMenuPanel = new MainMenuPanel(frame, cardLayout);
        JPanel joinRoomPanel = new JoinRoomPanel(frame, cardLayout);
        JPanel createRoomPanel = new CreateRoomPanel(frame, cardLayout);
        JPanel gameScreenPanel = new GameScreenPanel(frame, cardLayout);

        //adding the panels to the container:
        container.add(mainMenuPanel, "MainMenu");
        container.add(joinRoomPanel, "JoinRoom");
        container.add(createRoomPanel, "CreateRoom");
        container.add(gameScreenPanel, "GameScreen");

        //starting with the MainMenu panel (change it to any other panel if you want)
        cardLayout.show(container, "MainMenu");
    }
}
