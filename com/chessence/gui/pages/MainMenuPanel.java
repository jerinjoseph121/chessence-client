package com.chessence.gui.pages;

import com.chessence.ClientReader;
import com.chessence.ClientWriter;
import com.chessence.Message;
import com.chessence.gui.pages.components.*;
import com.chessence.gui.pages.components.TextField;
import com.chessence.gui.pages.createRoomPanelComponents.bodyComponents.PlayersPanel;
import com.chessence.gui.pages.createRoomPanelComponents.bodyComponents.SpectatorsPanel;

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
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainMenuPanel extends ParentPanel implements ActionListener {

    public JButton exitButton = new RoundedButton("Exit", new Color(0x5F3136), new Color(0x59252b), 20);

    private final JButton createRoomButton = new RoundedButton("Create a Room", new Color(0xEE9946), new Color(0xbd6e22), 30);
    private final JButton joinRoomButton = new RoundedButton("Join a Room", new Color(0xEE9946), new Color(0xbd6e22), 30);
    private final JButton playWithRandomsButton = new RoundedButton("Play with Randoms", new Color(0xEE9946), new Color(0xbd6e22), 30);
    public static Socket clientSocket;
    public static ObjectOutputStream objectOutputStream;
    public static ObjectInputStream objectInputStream;

    JTextField usernameField;

    public MainMenuPanel(JFrame frame, CardLayout cardLayout, Socket clientSocket, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        super(frame, cardLayout);
        MainMenuPanel.clientSocket = clientSocket;
        MainMenuPanel.objectOutputStream = objectOutputStream;
        MainMenuPanel.objectInputStream = objectInputStream;

        this.setLayout(new BorderLayout());
        //getting current frame size:
        Rectangle r = frame.getBounds();
        int heightOfFrame = r.height;
        int widthOfFrame = r.width;

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);

        //topPanel.setLayout(new FlowLayout());

        //adding a small initial space in the top (above the heading)
        topPanel.add(new HorizontalSpace(widthOfFrame, 7));

        //Adding heading (label):
        JLabel heading = new JLabel("Chessence");
        heading.setFont(getFont("Roboto-Medium", getResponsiveFontSize(68)));
        heading.setForeground(new Color(0x895158));
        topPanel.add(heading);
        topPanel.add(new HorizontalSpace(widthOfFrame, 0));

        //adding a horizontal line:
        topPanel.add(new HorizontalLine((int) (0.90 * widthOfFrame), 5, new Color(0x895158)));
        topPanel.add(new HorizontalSpace(widthOfFrame, (int) (heightOfFrame * 0.074074)));

        //making the label for text field:
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(getFont("Roboto-Medium", getResponsiveFontSize(40)));
        usernameLabel.setForeground(new Color(0xFFDEEE));
        topPanel.add(usernameLabel);

        //making the textfield:
        usernameField = new TextField(400, 50, username);
        usernameField.setFont(getFont("Roboto-Medium", getResponsiveFontSize(32)));

        topPanel.add(usernameField);
        topPanel.add(new HorizontalSpace(widthOfFrame, (int) (0.074074 * heightOfFrame)));

        int buttonWidth = (int) (widthOfFrame * ((float) 600 / (float) 1920));
        int buttonHeight = (int) (heightOfFrame * ((float) 100 / (float) 1080));
        int buttonFontSize = getResponsiveFontSize(40);
        int buttonGap = 10;

        //create room button:
        createRoomButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        createRoomButton.setFont(getFont("Rambla-Bold", buttonFontSize));
        createRoomButton.setForeground(new Color(0x321F28));
        createRoomButton.addActionListener(this);

        //join room button:
        joinRoomButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        joinRoomButton.setFont(getFont("Rambla-Bold", buttonFontSize));
        joinRoomButton.setForeground(new Color(0x321F28));
        joinRoomButton.addActionListener(this);

        //join random button:
        playWithRandomsButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        playWithRandomsButton.setFont(getFont("Rambla-Bold", buttonFontSize));
        playWithRandomsButton.setForeground(new Color(0x321F28));
        playWithRandomsButton.addActionListener(this);

        topPanel.add(createRoomButton);
        topPanel.add(new HorizontalSpace(widthOfFrame, buttonGap)); //newLine

        topPanel.add(joinRoomButton);
        topPanel.add(new HorizontalSpace(widthOfFrame, buttonGap)); //newLine

        topPanel.add(playWithRandomsButton);
        topPanel.add(new HorizontalSpace(widthOfFrame, 0)); //newLine

        topPanel.add(new HorizontalSpace(widthOfFrame, 0));

        //creating a bottom panel that will be put to the bottom-most part of the frame (to put the exit button)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BorderLayout());
        this.add(topPanel);

        JPanel exitButtonPanel = new JPanel();
        exitButtonPanel.setOpaque(false);
        exitButtonPanel.setPreferredSize(new Dimension(300, 70));
        exitButtonPanel.add(exitButton, BorderLayout.CENTER);
        bottomPanel.add(exitButtonPanel, BorderLayout.LINE_END);

        //this.setLayout(new BorderLayout());
        exitButton.setPreferredSize(new Dimension(200, 50));
        exitButton.addActionListener(this);
        exitButton.setForeground(new Color(0xF9A450));
        exitButton.setFont(getFont("Rambla-Bold", getResponsiveFontSize(28)));
        Border current = exitButton.getBorder();
        Border empty = new EmptyBorder(50, 50, 50, 50);
        exitButton.setBorder(new CompoundBorder(empty, current));
        //bottomPanel.add(exitButton, BorderLayout.LINE_END);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    //update player function
    public void updatePlayer() {
        if (CreateRoomPanel.Player_Status == 'P') {
            PlayersPanel.initializePlayer();
        } else {
            SpectatorsPanel.initializeSpectator();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //System.out.println("PLAYERS: " + CreateRoomPanel.PLAYERS[0] + ", " + CreateRoomPanel.PLAYERS[1]);
        //CreateRoomPanel.PLAYERS[0] = "-";
        //PlayersPanel.updatePlayerNames();
        //updating the name of the player
        username = usernameField.getText().trim().replaceAll(",", "").replaceAll(" ", "_");
        updatePlayer();

        if (e.getSource() == exitButton) {
            try {
                this.objectInputStream.close();
                this.objectOutputStream.close();
                this.clientSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            frame.dispose();    //close the frame
        }

        if (e.getSource() == createRoomButton) {
            cardLayout.show(container, "LoadingScreen");
            //create room request:
            try {
                var newLobbyMessage = new Message("", "lobbyInfo", true);
                newLobbyMessage.setSecondaryMessage(ParentPanel.username);
                this.objectOutputStream.writeObject(newLobbyMessage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            while (true) {
                try {
                    Object response = null;
                    try {
                        response = objectInputStream.readObject();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    Message createLobbyResponse = (Message) response;
                    System.out.println("\nYour new room's ID is: " + createLobbyResponse.getMessage());
                    currentRoomID = createLobbyResponse.getMessage();
                    break;
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    continue;
                }
            }
            //Thread writingThread = new ClientWriter(clientSocket, objectOutputStream);
            CreateRoomPanel.clearSpecatators();
            CreateRoomPanel.clearPlayers();
            updatePlayer();
            Thread readingThread = new ClientReader(clientSocket, objectInputStream);
            readingThread.start();
            //writingThread.start();
            cardLayout.show(container, "CreateRoom");
        }

        if (e.getSource() == joinRoomButton) {
            cardLayout.show(container, "JoinRoom");
        }

        if (e.getSource() == playWithRandomsButton) {
            cardLayout.show(container, "LoadingScreen");
        }
    }

}
