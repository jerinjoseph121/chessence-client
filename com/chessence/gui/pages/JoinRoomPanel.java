package com.chessence.gui.pages;

import com.chessence.ClientReader;
import com.chessence.Message;
import com.chessence.gui.pages.components.HorizontalLine;
import com.chessence.gui.pages.components.HorizontalSpace;
import com.chessence.gui.pages.components.RoundedButton;
import com.chessence.gui.pages.components.TextField;
import com.chessence.gui.pages.createRoomPanelComponents.bodyComponents.PlayersPanel;
import com.chessence.gui.pages.createRoomPanelComponents.bodyComponents.SpectatorsPanel;
import com.sun.javafx.scene.traversal.ParentTraversalEngine;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class JoinRoomPanel extends ParentPanel implements ActionListener {
    public JButton button = new JButton();
    private final JButton exitButton = new RoundedButton("Back", new Color(0x5F3136), new Color(0x59252b), 20);
    private final JButton joinRoomButton = new RoundedButton("Join Room", new Color(0xEE9946), new Color(0xbd6e22), 30);
    private JTextField roomIdField;
    public Socket clientSocket;
    public ObjectOutputStream objectOutputStream;
    public ObjectInputStream objectInputStream;

    public JoinRoomPanel(JFrame frame, CardLayout cardLayout, Socket clientSocket, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        super(frame, cardLayout);
        this.clientSocket = clientSocket;
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;

        //getting current frame size:
        Rectangle r = frame.getBounds();
        int heightOfFrame = r.height;
        int widthOfFrame = r.width;

        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setOpaque(false);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        bottomPanel.setOpaque(false);

        //---------------------Design this panel here---------------------//

        //adding a small initial space in the top (above the heading)
        topPanel.add(new HorizontalSpace(widthOfFrame, 7));

        //Adding heading (label):
        JLabel heading = new JLabel("Join a Room");
        heading.setFont(getFont("Roboto-Medium", getResponsiveFontSize(68)));
        heading.setForeground(new Color(0x895158));
        topPanel.add(heading);
        topPanel.add(new HorizontalSpace(widthOfFrame, 0));

        //adding a horizontal line:
        topPanel.add(new HorizontalLine((int) (0.90 * widthOfFrame), 5, new Color(0x895158)));
        topPanel.add(new HorizontalSpace(widthOfFrame, (int) (heightOfFrame * 0.1)));

        topPanel.add(new HorizontalSpace(widthOfFrame, 10)); //newLine

        //making the label for text field:
        JLabel usernameLabel = new JLabel("Room ID: ");
        usernameLabel.setFont(getFont("Roboto-Medium", getResponsiveFontSize(40)));
        usernameLabel.setForeground(new Color(0xFFDEEE));
        topPanel.add(usernameLabel);
        //making the textfield:
        roomIdField = new TextField(400, 50, "TEST123");
        roomIdField.setFont(getFont("Roboto-Medium", getResponsiveFontSize(32)));
        topPanel.add(roomIdField);
        topPanel.add(new HorizontalSpace(widthOfFrame, (int) (0.074074 * heightOfFrame)));

        topPanel.add(new HorizontalSpace(widthOfFrame, 10)); //newLine
        int buttonWidth = (int) (widthOfFrame * ((float) 700 / (float) 1920));
        int buttonHeight = (int) (heightOfFrame * ((float) 100 / (float) 1080));
        int buttonFontSize = getResponsiveFontSize(40);
        joinRoomButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        joinRoomButton.setFont(getFont("Rambla-Bold", buttonFontSize));
        joinRoomButton.setForeground(new Color(0x321F28));
        joinRoomButton.addActionListener(this);
        topPanel.add(joinRoomButton);

        //making the exit button:
        exitButton.setPreferredSize(new Dimension(200, 50));
        exitButton.addActionListener(this);
        exitButton.setForeground(new Color(0xF9A450));
        exitButton.setFont(getFont("Rambla-Bold", getResponsiveFontSize(26)));
        Border current = exitButton.getBorder();
        Border empty = new EmptyBorder(50, 50, 50, 50);
        exitButton.setBorder(new CompoundBorder(empty, current));
        JPanel exitButtonPanel = new JPanel();
        exitButtonPanel.setOpaque(false);
        exitButtonPanel.setPreferredSize(new Dimension(300, 70));
        exitButtonPanel.add(exitButton, BorderLayout.CENTER);
        bottomPanel.add(exitButtonPanel, BorderLayout.LINE_END);

        this.add(topPanel);
        this.add(bottomPanel, BorderLayout.SOUTH);
        //-----------------------End of designing------------------------//
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            frame.dispose();
        }

        if (e.getSource() == joinRoomButton) {
            cardLayout.show(container, "LoadingScreen");
            String roomId = roomIdField.getText();

            //socket code to work before joining the room:
            Message roomIdMessage = new Message(roomId, "lobbyInfo", false);
            roomIdMessage.setSecondaryMessage(ParentPanel.username);
            try {
                objectOutputStream.writeObject(roomIdMessage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                Message response = null;
                try {
                    response = (Message) objectInputStream.readObject();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if (response.getMessage().contains("_error")) {
                    System.out.println("\nERROR: Enter a valid room ID!");
                    cardLayout.show(container, "JoinRoom");
                } else if (response.getMessage().contains("_success")) {

                    var splitNames = response.getSecondaryMessage().split("_#SPECTATORS#_");
                    var playerNames = splitNames[0].split(",");
                    CreateRoomPanel.clearPlayers();
                    CreateRoomPanel.clearSpecatators();
                    for (int i = 0; i < playerNames.length; i++) {
                        CreateRoomPanel.PLAYERS[i] = playerNames[i];
                    }
                    PlayersPanel.updatePlayerNames();

                    if (splitNames.length == 2) {
                        var spectatorNames = splitNames[1].split(",");
                        for (int i = 0; i < spectatorNames.length; i++) {
                            CreateRoomPanel.SPECTATORS[i] = spectatorNames[i];
                        }
                        SpectatorsPanel.updateSpecatators();
                    }
                    PlayersPanel.addPlayer();
                    //PlayersPanel.fixJoinSpectatorsButtonPosition();
                    ParentPanel.currentRoomID = roomId;
                    Thread readingThread = new ClientReader(clientSocket, objectInputStream);
                    readingThread.start();
                    cardLayout.show(container, "CreateRoom");
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == exitButton) {
            cardLayout.show(container, "MainMenu");
        }
    }
}
