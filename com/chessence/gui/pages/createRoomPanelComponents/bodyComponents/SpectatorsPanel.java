package com.chessence.gui.pages.createRoomPanelComponents.bodyComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.chessence.gui.pages.CreateRoomPanel;
import com.chessence.gui.pages.ParentPanel;
import com.chessence.gui.pages.createRoomPanelComponents.RoundedButton;

public class SpectatorsPanel extends JPanel implements ActionListener {

    static String CREAM_ORANGE = "#E79E4F";
    static String BROWN = "#A05344";
    static String PINK_MAROON = "#734046";
    static String RED = "#841522";
    static String DARK_RED = "#710E1A";

    int WIDTH;
    int HEIGHT;
    int cornerRadius = 15;
    Color backgroundColor = Color.decode(CREAM_ORANGE);

    private static JLabel spectator1;
    private static JLabel spectator2;
    private static JLabel spectator3;
    private static JLabel spectator4;

    private static final RoundedButton joinPlayerButton = new RoundedButton("Become a Player", new Font("Roboto", Font.PLAIN, 20),
            Color.decode(CREAM_ORANGE), Color.decode(RED), Color.decode(DARK_RED),
            10, new Dimension(200, 40));

    public SpectatorsPanel(){
        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(300, 350));

        //updating spectator list based on player status
        if(CreateRoomPanel.Player_Status == 'S'){
            if(CreateRoomPanel.SPECTATORS[0].equals("-"))
                CreateRoomPanel.SPECTATORS[0] = ParentPanel.username;
            else if(CreateRoomPanel.SPECTATORS[1].equals("-"))
                CreateRoomPanel.SPECTATORS[1] = ParentPanel.username;

            else if(CreateRoomPanel.SPECTATORS[2].equals("-"))
                CreateRoomPanel.SPECTATORS[2] = ParentPanel.username;

            else if(CreateRoomPanel.SPECTATORS[3].equals("-"))
                CreateRoomPanel.SPECTATORS[3] = ParentPanel.username;

            joinPlayerButton.setEnabled(true);
        }
        else if(CreateRoomPanel.Player_Status == 'P')
            joinPlayerButton.setEnabled(false);

        ////////////////////////// TITLE //////////////////////////////////
        JPanel spectatorTitlePanel = new JPanel();
        spectatorTitlePanel.setPreferredSize(new Dimension(500, 50));

        JLabel spectatorTitleLabel = new JLabel("Spectator(s)");
        spectatorTitleLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
        spectatorTitleLabel.setForeground(Color.decode(PINK_MAROON));
        spectatorTitlePanel.add(spectatorTitleLabel);
        spectatorTitlePanel.setOpaque(false);

        ////////////////////////// SPECTATORS /////////////////////////////
        JPanel spectators = new JPanel(new GridLayout(4, 1));

        //adding all spectators into the spectator panel
        Font spectatorsFont = new Font("Roboto", Font.PLAIN, 25);

        spectator1 = new JLabel(CreateRoomPanel.SPECTATORS[0]);
        spectator1.setHorizontalAlignment(JLabel.CENTER);
        spectator1.setForeground(Color.decode(CREAM_ORANGE));
        spectator1.setBackground(Color.decode(PINK_MAROON));
        spectator1.setFont(spectatorsFont);
        spectator1.setOpaque(true);

        spectator2 = new JLabel(CreateRoomPanel.SPECTATORS[1]);
        spectator2.setHorizontalAlignment(JLabel.CENTER);
        spectator2.setForeground(Color.decode(CREAM_ORANGE));
        spectator2.setBackground(Color.decode(BROWN));
        spectator2.setFont(spectatorsFont);
        spectator2.setOpaque(true);

        spectator3 = new JLabel(CreateRoomPanel.SPECTATORS[2]);
        spectator3.setHorizontalAlignment(JLabel.CENTER);
        spectator3.setForeground(Color.decode(CREAM_ORANGE));
        spectator3.setBackground(Color.decode(PINK_MAROON));
        spectator3.setFont(spectatorsFont);
        spectator3.setOpaque(true);

        spectator4 = new JLabel(CreateRoomPanel.SPECTATORS[3]);
        spectator4.setHorizontalAlignment(JLabel.CENTER);
        spectator4.setForeground(Color.decode(CREAM_ORANGE));
        spectator4.setBackground(Color.decode(BROWN));
        spectator4.setFont(spectatorsFont);
        spectator4.setOpaque(true);

        spectators.add(spectator1);
        spectators.add(spectator2);
        spectators.add(spectator3);
        spectators.add(spectator4);
        spectators.setOpaque(false);

        ///////////////////////// JOIN_PLAYER_BUTTON //////////////////////
        JPanel joinPlayerPanel = new JPanel();

        //adding the join player button
        joinPlayerPanel.add(joinPlayerButton);
        joinPlayerPanel.setOpaque(false);

        joinPlayerButton.addActionListener(this);

        this.setLayout(new BorderLayout());
        this.add(spectatorTitlePanel, BorderLayout.NORTH);
        this.add(spectators, BorderLayout.CENTER);
        this.add(joinPlayerPanel, BorderLayout.SOUTH);


        this.setOpaque(false);
    }

    //method to initialize spectator to the panel (called in the main menu panel)
    public static void initializeSpectator(){
        CreateRoomPanel.SPECTATORS[2] = ParentPanel.username;
        spectator3.setText(ParentPanel.username);
    }

    //method to add a spectator into the panel
    public static void addSpectator(){
        CreateRoomPanel.Player_Status = 'S';

        if(CreateRoomPanel.SPECTATORS[0].equals("-")){
            CreateRoomPanel.SPECTATORS[0] = ParentPanel.username;
            spectator1.setText(CreateRoomPanel.SPECTATORS[0]);
        }
        else if(CreateRoomPanel.SPECTATORS[1].equals("-")){
            CreateRoomPanel.SPECTATORS[1] = ParentPanel.username;
            spectator2.setText(CreateRoomPanel.SPECTATORS[1]);
        }
        else if(CreateRoomPanel.SPECTATORS[2].equals("-")){
            CreateRoomPanel.SPECTATORS[2] = ParentPanel.username;
            spectator3.setText(CreateRoomPanel.SPECTATORS[2]);
        }
        else if(CreateRoomPanel.SPECTATORS[3].equals("-")){
            CreateRoomPanel.SPECTATORS[3] = ParentPanel.username;
            spectator4.setText(CreateRoomPanel.SPECTATORS[3]);
        }

        joinPlayerButton.setBackground(Color.decode(RED));
        joinPlayerButton.setEnabled(true);
    }

    //method to remove a spectator from the panel
    public static void removeSpectator(){
        CreateRoomPanel.Player_Status = 'P';

        if(CreateRoomPanel.SPECTATORS[0].equals(ParentPanel.username)){
            CreateRoomPanel.SPECTATORS[0] = "-";
            spectator1.setText(CreateRoomPanel.SPECTATORS[0]);
        }
        else if(CreateRoomPanel.SPECTATORS[1].equals(ParentPanel.username)){
            CreateRoomPanel.SPECTATORS[1] = "-";
            spectator2.setText(CreateRoomPanel.SPECTATORS[1]);
        }
        else if(CreateRoomPanel.SPECTATORS[2].equals(ParentPanel.username)){
            CreateRoomPanel.SPECTATORS[2] = "-";
            spectator3.setText(CreateRoomPanel.SPECTATORS[2]);
        }
        else if(CreateRoomPanel.SPECTATORS[3].equals(ParentPanel.username)){
            CreateRoomPanel.SPECTATORS[3] = "-";
            spectator4.setText(CreateRoomPanel.SPECTATORS[3]);
        }

        joinPlayerButton.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == joinPlayerButton){

            //removes spectator and add to player panel when join spectator button is clicked
            removeSpectator();
            PlayersPanel.addPlayer();

            repaint();
            revalidate();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //adding curves to the corners of the panel
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
    }
}
