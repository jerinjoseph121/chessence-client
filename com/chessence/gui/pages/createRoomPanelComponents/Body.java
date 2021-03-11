package com.chessence.gui.pages.createRoomPanelComponents;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

import com.chessence.gui.pages.CreateRoomPanel;
import com.chessence.gui.pages.createRoomPanelComponents.bodyComponents.*;

import java.awt.*;

public class Body extends JPanel {

    String ROOM_ID = "ABC123";

    static String BROWN = "#A05344";
    static String CREAM_ORANGE = "#E79E4F";
    static String DARK_CREAM = "#bd6e22";
    static String RED = "#841522";

    //Initializing the three panels in the body component
    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;

    PlayersPanel playersPanel;
    SpectatorsPanel spectatorsPanel;


    public Body(){

        ////////////////////////////// TOP PANEL//////////////////////////////
        topPanel = new JPanel();

        ////////////////////////////// TOP LEFT PANEL//////////////////////////////
        JPanel topLeftPanel = new JPanel();

        //adding room id label
        JLabel roomIDLabel;
        roomIDLabel = new JLabel("Room ID:");
        roomIDLabel.setForeground(Color.decode(BROWN));
        roomIDLabel.setFont(new Font("Roboto", Font.PLAIN, 30));

        LineBorder border = new LineBorder(Color.decode(BROWN), 3, true);
        Border margin = new EmptyBorder(3, 12, 3, 12);

        JLabel roomIDValueLabel;
        roomIDValueLabel = new JLabel(ROOM_ID);
        roomIDValueLabel.setForeground(Color.decode(CREAM_ORANGE));
        roomIDValueLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
        roomIDValueLabel.setBorder(new CompoundBorder(border, margin));

        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 10));
        topLeftPanel.setOpaque(false);
        topLeftPanel.add(roomIDLabel);
        topLeftPanel.add(roomIDValueLabel);

        ////////////////////////////// TOP RIGHT PANEL//////////////////////////////
        JPanel topRightPanel = new JPanel();

        //adding private room switch
        JLabel privateRoomLabel;
        privateRoomLabel = new JLabel("Private Room:");
        privateRoomLabel.setForeground(Color.decode(BROWN));
        privateRoomLabel.setFont(new Font("Roboto", Font.PLAIN, 30));

        OnOffSwitch privateSwitch = new OnOffSwitch(CreateRoomPanel.isPrivate);

        topRightPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 25, 10));
        topRightPanel.setOpaque(false);
        topRightPanel.add(privateRoomLabel);
        topRightPanel.add(privateSwitch);



        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.setOpaque(false);
        topPanel.add(topLeftPanel);
        topPanel.add(topRightPanel);
        ///////////////////////////////////////////////////////////////////////////////

        ////////////////////////////// CENTER PANEL//////////////////////////////
        centerPanel = new JPanel();

        ////////////////////////////// LEFT CENTER PANEL//////////////////////////////
        JPanel leftCenterPanel = new JPanel();
        leftCenterPanel.setBackground(Color.BLACK);

        //adding player panel component
        playersPanel = new PlayersPanel();
        leftCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 75));
        leftCenterPanel.add(playersPanel);


        leftCenterPanel.setOpaque(false);

        ////////////////////////////// MIDDLE CENTER PANEL//////////////////////////////
        JPanel middleCenterPanel = new JPanel();

        //adding center image into panel
        ImageIcon centerImg = new ImageIcon("com\\chessence\\gui\\images\\ChessCenterImage.png");
        JLabel imgHolder = new JLabel(centerImg);

        middleCenterPanel.setBackground(Color.WHITE);
        middleCenterPanel.setOpaque(false);
        middleCenterPanel.setLayout(new GridLayout(1,1));
        middleCenterPanel.add(imgHolder);
        middleCenterPanel.setVisible(true);

        ////////////////////////////// RIGHT CENTER PANEL//////////////////////////////
        JPanel rightCenterPanel = new JPanel();
        rightCenterPanel.setBackground(Color.BLACK);

        //adding spectator panel component
        spectatorsPanel = new SpectatorsPanel();

        rightCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 75));
        rightCenterPanel.add(spectatorsPanel);
        rightCenterPanel.setOpaque(false);


        centerPanel.setLayout(new GridLayout(1, 3));
        centerPanel.setBackground(Color.green);
        centerPanel.setOpaque(false);
        centerPanel.add(leftCenterPanel);
        centerPanel.add(middleCenterPanel);
        centerPanel.add(rightCenterPanel);
        centerPanel.setVisible(true);
        ///////////////////////////////////////////////////////////////////////////////

        ////////////////////////////// BOTTOM PANEL//////////////////////////////
        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(WIDTH, 60));
        bottomPanel.setBackground(Color.blue);

        //adding start game button
        Font buttonFont = new Font("Roboto", Font.BOLD, 20);
        RoundedButton startGameButton = new RoundedButton("Start Game",buttonFont, Color.decode(RED), Color.decode(CREAM_ORANGE),
                Color.decode(DARK_CREAM), 10, new Dimension(200, 50));
        startGameButton.setFocusable(false);

        bottomPanel.setOpaque(false);
        bottomPanel.add(startGameButton);

        /////////////////////////////////////////////////////////////////////////

        this.setBackground(Color.red);

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setOpaque(false);
        this.setVisible(true);
    }

}