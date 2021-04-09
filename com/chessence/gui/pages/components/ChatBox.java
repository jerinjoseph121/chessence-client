package com.chessence.gui.pages.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.*;

public class ChatBox extends JPanel {
    private final Dimension size;

    private JPanel box = new JPanel();
    private JScrollPane scrollPane = null;
    private JPanel mainChatWindow = new JPanel();
    private JTextPane textBox = new JTextPane();

    private int W;
    private int H;

    private ArrayList<ChatMessage> MESSAGES = new ArrayList<>();
    private int totalHeighOfWindow = 0;
    private String textBoxInput = "";

    public ChatBox(int W, int H){

        this.W = W;
        this.H = H;

        MESSAGES.add(new ChatMessage("This is the NP Project", true));
        MESSAGES.add(new ChatMessage("Wohoooooooooo awesomee okay bye", false));
        MESSAGES.add(new ChatMessage("Jingala hurr hurrrr", true));
        MESSAGES.add(new ChatMessage("also java sucks", false));
        MESSAGES.add(new ChatMessage("Wasdasdwesomee okay bye", false));
        MESSAGES.add(new ChatMessage("Jingaaasdasdhurr hurrrr", true));
        MESSAGES.add(new ChatMessage("also asdasdcks", false));
        MESSAGES.add(new ChatMessage("Hello wazzuuppppp CO2 IS BADllo wazzuuppppp CO2 IS BADllo wazzuuppppp CO2 IS BADHello wazzuuppppp CO2 IS BADllo wazzuuppppp CO2 IS BADllo wazzuuppppp CO2 IS BADHello wazzuuppppp CO2 IS BADllo wazzuuppppp CO2 IS BADllo wazzuuppppp CO2 IS BAD", true));

        FlowLayout layout = (FlowLayout)super.getLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setAlignment(FlowLayout.LEADING);
        this.size = new Dimension(W, H);
        super.setPreferredSize(size);
        super.setOpaque(false);
        setBackground(new Color(0xC88275));

        //------------------------- LABEL---------------------------
        JLabel label = new JLabel("C h a t");
        label.setFont(new Font("Colonna MT",Font.BOLD,25));
        label.setForeground(new Color(0x321F28));
        super.add(label);
        super.add(new HorizontalLine((int) (W*0.95),6, new Color(0x321F28)));

        //------------------------MESSAGES--------------------------

        scrollPane = new JScrollPane(mainChatWindow,
                                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, W, H);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(0xC88275));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        scrollPane.setPreferredSize(new Dimension((int) (W*0.95), (int) (3.5*H/5)));

        mainChatWindow.setLayout(new FlowLayout());
        mainChatWindow.setOpaque(false);

        for(ChatMessage item: MESSAGES){
            displayNewMessage(item.getMessage(), item.isOpponent());
        }

        mainChatWindow.setPreferredSize(new Dimension((int)(W*0.92), totalHeighOfWindow));
        //JScrollBar vertical = scrollPane.getVerticalScrollBar();
        scrollPane.getViewport().setViewPosition(new Point(0, totalHeighOfWindow));
        //vertical.setValue(totalHeighOfWindow+100);
        super.add(scrollPane);

        //------------------------TEXT BOX--------------------------

        Border roundedBorder = new LineBorder(new Color(0x734046), 3, true); // the third parameter - true, says it's round
        textBox.setBorder(roundedBorder);
        textBox.setEditable(true);
        textBox.setPreferredSize(new Dimension((int) (W*0.95),H/10));

        textBox.setBackground(new Color(0xD79E92));
        textBox.setBounds(0,H- textBox.getHeight(), textBox.getWidth(), textBox.getHeight());
        box.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        box.setOpaque(false);
        box.setPreferredSize(new Dimension(W, (int) (H/8.5)));
        box.add(textBox);
        super.add(box,BorderLayout.SOUTH);


        textBox.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10){
                    if(textBox.getText().trim()!="")
                    {
                        addNewMessage(textBox.getText().trim(), false);
                        //also send to the other client//
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                /*if(e.getKeyChar()== ' ' && textBoxInput == "")
                {
                    return;
                }*/
                //textBoxInput = textBoxInput + e.getKeyChar();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }
        });
    }

    private void addNewMessage(String text, boolean isOpponent)
    {
        textBox.setText("");
        MESSAGES.add(new ChatMessage(text, isOpponent));
        textBoxInput = "";
        displayNewMessage(text, isOpponent);
    }

    private void displayNewMessage(String text, boolean isOpponent){
        int textWidth = getTextWidth(text);
        int numberOfLines = (int)Math.ceil(textWidth/(W*0.95));

        JPanel message = new JPanel();
        message.setLayout(new FlowLayout(isOpponent ? FlowLayout.LEFT: FlowLayout.RIGHT,10,10));
        message.setOpaque(false);
        totalHeighOfWindow += 60*numberOfLines + 10;
        message.setPreferredSize(new Dimension((int) (W*0.80),50*numberOfLines + 10));
        message.add(new TextBubble(text, isOpponent, W));
        mainChatWindow.add(message);
        mainChatWindow.add(new HorizontalSpace((int)W, 0));
        mainChatWindow.setPreferredSize(new Dimension((int)(W*0.92), totalHeighOfWindow));
        scrollPane.getViewport().setViewPosition(new Point(0, totalHeighOfWindow));
        if(!mainChatWindow.isValid())
            mainChatWindow.validate();
        //scrollPane.revalidate();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getSize().width - 1 ,
                getSize().height - 1 , 20, 20);

    }

    private int getTextWidth(String text){
        //calculating the number of lines required for this particular message:
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        int textWidth = (int)(font.getStringBounds(text, frc).getWidth());
        return textWidth;
    }
}

class ChatMessage {
    private String message;
    private boolean isOpponent;

    ChatMessage(String message, boolean isOpponent) {
        this.message = message;
        this.isOpponent = isOpponent;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOpponent() {
        return isOpponent;
    }
}
