package com.chessence.gui.pages;

import com.chessence.gui.pages.components.*;
import com.chessence.gui.pages.components.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends ParentPanel implements ActionListener {

    public JButton button = new JButton();

    private JButton createRoomButton = new RoundedButton("Create a Room", new Color(0xEE9946), new Color(0xbd6e22), 30);
    private JButton joinRoomButton = new RoundedButton("Join a Room", new Color(0xEE9946), new Color(0xbd6e22), 30);
    private JButton playWithRandomsButton = new RoundedButton("Play with Randoms", new Color(0xEE9946), new Color(0xbd6e22), 30);

    public MainMenuPanel(JFrame frame, CardLayout cardLayout) {
        super(frame, cardLayout);

        //getting current frame size:
        Rectangle r = frame.getBounds();
        int heightOfFrame = r.height;
        int widthOfFrame = r.width;

        //adding a small initial space in the top (above the heading)
        this.add(new HorizontalSpace(widthOfFrame, 7));

        //Adding heading (label):
        JLabel heading = new JLabel("Chessence");
        heading.setFont(getFont("Roboto-Medium", getHeadingFontSize()));
        heading.setForeground(new Color(0x895158));
        this.add(heading);
        this.add(new HorizontalSpace(widthOfFrame, 0));

        //adding a horizontal line:
        this.add(new HorizontalLine((int) (0.90 * widthOfFrame), 5, new Color(0x895158)));
        this.add(new HorizontalSpace(widthOfFrame, (int) (heightOfFrame * 0.1)));

        //making the label for text field:
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(getFont("Roboto-Medium", 40));
        usernameLabel.setForeground(new Color(0xFFDEEE));
        this.add(usernameLabel);

        //making the textfield:
        JTextField usernameField = new TextField(400, 50, "Player1");
        usernameField.setFont(getFont("Roboto-Medium", 32));

        //usernameField.setBounds(0, 0, 400, 20);
        this.add(usernameField);
        this.add(new HorizontalSpace(widthOfFrame, 80));

        int buttonWidth = 600;
        int buttonHeight = 100;
        int buttonFontSize = 40;

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

        this.add(createRoomButton);
        this.add(new HorizontalSpace(widthOfFrame, 10)); //newLine

        this.add(joinRoomButton);
        this.add(new HorizontalSpace(widthOfFrame, 10)); //newLine

        this.add(playWithRandomsButton);
        this.add(new HorizontalSpace(widthOfFrame, 10)); //newLine

        this.add(new HorizontalSpace(widthOfFrame, 0));
        button.setBounds(500, 500, 100, 50);
        button.addActionListener(this);
        button.setText("EXIT");
        this.add(button);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            frame.dispose();    //close the frame
        }

        if(e.getSource() == createRoomButton){
            cardLayout.show(container, "CreateRoom");
        }

        if(e.getSource() == joinRoomButton){
            cardLayout.show(container, "JoinRoom");
        }

        if(e.getSource() == playWithRandomsButton){
            cardLayout.show(container, "JoinRoom");
        }
    }

}
