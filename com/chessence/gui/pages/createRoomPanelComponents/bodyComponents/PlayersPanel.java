package com.chessence.gui.pages.createRoomPanelComponents.bodyComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.chessence.gui.pages.ParentPanel;
import com.chessence.gui.pages.CreateRoomPanel;
import com.chessence.gui.pages.createRoomPanelComponents.RoundedButton;

public class PlayersPanel extends JPanel implements ActionListener {

    static String CREAM_ORANGE = "#E79E4F";
    static String PINK_MAROON = "#734046";
    static String RED = "#841522";
    static String DARK_RED = "#710E1A";

    int WIDTH;
    int HEIGHT;
    int cornerRadius = 15;
    Color backgroundColor = Color.decode(CREAM_ORANGE);

    private static final RoundedButton playerJoinSpectatorButton = new RoundedButton("Join Spectator", new Font("Roboto", Font.PLAIN, 15),
            Color.decode(CREAM_ORANGE), Color.decode(RED), Color.decode(DARK_RED),
            10, new Dimension(200, 40));

    private static JPanel playerOneInfo;
    private static JLabel playerOneName;

    private static JPanel playerTwoInfo;
    private static JLabel playerTwoName;



    public PlayersPanel(){

        //updating player list value based on player status
        if(CreateRoomPanel.Player_Status == 'P'){
            if(CreateRoomPanel.PLAYERS[0].equals("-")){
                CreateRoomPanel.PLAYERS[0] = ParentPanel.username;
            }
            else{
            CreateRoomPanel.PLAYERS[1] = ParentPanel.username;
            }
        }

        this.setPreferredSize(new Dimension(300, 350));

        playerJoinSpectatorButton.setPreferredSize(new Dimension(200, 40));

        JPanel Players = new JPanel(new GridLayout(2, 1));

        //////////////////////////PLAYER 1/////////////////////////////
        JPanel Player1 = new JPanel();

        Player1.setLayout(new BorderLayout());

        //adding player1 title and info panel
        JLabel playerOneTitle = new JLabel("Player 1");
        playerOneTitle.setPreferredSize(new Dimension(400, 50));
        playerOneTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
        playerOneTitle.setForeground(Color.decode(PINK_MAROON));
        playerOneTitle.setHorizontalAlignment(JLabel.CENTER);

        playerOneInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 12));
        playerOneInfo.setBackground(Color.decode(PINK_MAROON));

        playerOneName = new JLabel(CreateRoomPanel.PLAYERS[0]);
        playerOneName.setPreferredSize(new Dimension(300, 40));
        playerOneName.setHorizontalAlignment(JLabel.CENTER);
        playerOneName.setForeground(Color.decode(CREAM_ORANGE));
        playerOneName.setBackground(Color.blue);
        playerOneName.setFont(new Font("Roboto", Font.PLAIN, 30));

        playerOneInfo.add(playerOneName);

        //adding JoinSpectator button if player1 is the user
        if(CreateRoomPanel.PLAYERS[0].equals(ParentPanel.username)) {
            playerOneInfo.add(playerJoinSpectatorButton);
        }

        Player1.add(playerOneTitle, BorderLayout.NORTH);
        Player1.add(playerOneInfo);
        Player1.setOpaque(false);

        //////////////////////////PLAYER 2/////////////////////////////
        JPanel Player2 = new JPanel();

        Player2.setLayout(new BorderLayout());

        //adding player2 title and info panel
        JLabel playerTwoTitle = new JLabel("Player 2");
        playerTwoTitle.setPreferredSize(new Dimension(400, 50));
        playerTwoTitle.setFont(new Font("Roboto", Font.PLAIN, 30));
        playerTwoTitle.setForeground(Color.decode(PINK_MAROON));
        playerTwoTitle.setHorizontalAlignment(JLabel.CENTER);
        playerTwoTitle.setOpaque(false);

        playerTwoInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 12));
        playerTwoInfo.setBackground(Color.decode(PINK_MAROON));

        playerTwoName = new JLabel(CreateRoomPanel.PLAYERS[1]);
        playerTwoName.setPreferredSize(new Dimension(300, 40));
        playerTwoName.setHorizontalAlignment(JLabel.CENTER);
        playerTwoName.setForeground(Color.decode(CREAM_ORANGE));
        playerTwoName.setBackground(Color.blue);
        playerTwoName.setFont(new Font("Roboto", Font.PLAIN, 30));

        playerTwoInfo.add(playerTwoName);

        //adding JoinSpectator button if player1 is the user
        if(CreateRoomPanel.PLAYERS[1].equals(CreateRoomPanel.username)){
            playerTwoInfo.add(playerJoinSpectatorButton);
        }

        Player2.add(playerTwoTitle, BorderLayout.NORTH);
        Player2.add(playerTwoInfo);
        Player2.setOpaque(false);
        Player2.setBackground(Color.green);

        Players.add(Player1);
        Players.add(Player2);
        Players.setOpaque(false);

        playerJoinSpectatorButton.addActionListener(this);

        JPanel margin = new JPanel();
        margin.setPreferredSize(new Dimension(500, 20));
        margin.setOpaque(false);

        this.setLayout(new BorderLayout());
        this.add(Players, BorderLayout.CENTER);
        this.add(margin, BorderLayout.SOUTH);
        this.setOpaque(false);

    }

    //method to initialize player to the panel (called in the main menu panel)
    public static void initializePlayer(){
        CreateRoomPanel.PLAYERS[1] = ParentPanel.username;
        playerTwoName.setText(ParentPanel.username);
    }

    //method to add a player into the panel
    public static void addPlayer(){
        CreateRoomPanel.Player_Status = 'P';

        if(CreateRoomPanel.PLAYERS[0].equals("-")){
            CreateRoomPanel.PLAYERS[0] = ParentPanel.username;
            playerOneName.setText(CreateRoomPanel.PLAYERS[0]);
            playerOneInfo.add(playerJoinSpectatorButton);
        }
        else if(CreateRoomPanel.PLAYERS[1].equals("-")){
            CreateRoomPanel.PLAYERS[1] = ParentPanel.username;
            playerTwoName.setText(CreateRoomPanel.PLAYERS[1]);
            playerTwoInfo.add(playerJoinSpectatorButton);
        }
    }

    //method to remove player from the panel
    public static void removePlayer(){
        CreateRoomPanel.Player_Status = 'S';

        if(CreateRoomPanel.PLAYERS[0].equals(ParentPanel.username)){
            CreateRoomPanel.PLAYERS[0] = "-";
            playerOneName.setText(CreateRoomPanel.PLAYERS[0]);
            playerOneInfo.remove(playerJoinSpectatorButton);
        }
        else if(CreateRoomPanel.PLAYERS[1].equals(ParentPanel.username)){
            CreateRoomPanel.PLAYERS[1] = "-";
            playerTwoName.setText(CreateRoomPanel.PLAYERS[1]);
            playerTwoInfo.remove(playerJoinSpectatorButton);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == playerJoinSpectatorButton){

            //removes player and add spectator panel when join spectator button is clicked
            removePlayer();
            SpectatorsPanel.addSpectator();

            repaint();
            revalidate();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        //adding curves to the corners of the panel
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        WIDTH= getWidth();
        HEIGHT = getHeight();
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded panel with borders.
        g2D.setColor(backgroundColor);

        g2D.fillRoundRect(0, 0, WIDTH-1, HEIGHT-1, arcs.width, arcs.height); //paint background
        g2D.setColor(backgroundColor);
        g2D.drawRoundRect(0, 0, WIDTH-1, HEIGHT-1, arcs.width, arcs.height); //paint border

        repaint();
        revalidate();
    }

}
