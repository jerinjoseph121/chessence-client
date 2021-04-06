package com.chessence.gui.pages.components;

import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import static java.lang.Math.max;

public class ChatBox extends JPanel {
    private final Dimension size;
    private JPanel box = new JPanel();
    public Map<String,Boolean> MESSAGES = new LinkedHashMap<String,Boolean>();

    public ChatBox(int W,int H){

        MESSAGES.put("Hello wazzuuppppp CO2 IS BAD",true);
        MESSAGES.put("This is the NP Project",true);
        MESSAGES.put("Wohoooooooooo awesomee okay bye",false);
        MESSAGES.put("Jingala hurr hurrrr",true);
        MESSAGES.put("also java sucks",false);


        FlowLayout layout = (FlowLayout)super.getLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setAlignment(FlowLayout.LEADING);
        this.size = new Dimension(W,H);
        super.setPreferredSize(size);
        super.setOpaque(false);
        setBackground(new Color(0xC88275));

        //------------------------- LABEL---------------------------
        JLabel label = new JLabel("C h a t");
        label.setFont(new Font("Colonna MT",Font.BOLD,25));
        label.setForeground(new Color(0x321F28));
        super.add(label);
        super.add(new HorizontalLine((int) (W*0.95),6,new Color(0x321F28)));

        //------------------------MESSAGES--------------------------
        JPanel mainchatwindow = new JPanel();
        JScrollPane scroll = new JScrollPane(mainchatwindow);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainchatwindow.setPreferredSize(new Dimension(W, (int) (3.5*H/5)));
        mainchatwindow.setLayout(new FlowLayout());
        mainchatwindow.setOpaque(false);
        for(HashMap.Entry<String,Boolean> item : MESSAGES.entrySet()){
            JPanel messages = new JPanel();
            messages.setLayout(new FlowLayout(item.getValue() ? FlowLayout.LEFT: FlowLayout.RIGHT,10,10));
            messages.setOpaque(false);
            messages.setPreferredSize(new Dimension((int) (W*0.95),60));
            messages.add(new TextBubble(item.getKey(),item.getValue(),W));
            mainchatwindow.add(messages,BorderLayout.AFTER_LINE_ENDS);
        }
        super.add(mainchatwindow);
        //------------------------TEXT BOX--------------------------
        JTextPane msgbox = new JTextPane();
        Border roundedBorder = new LineBorder(new Color(0x734046), 3, true); // the third parameter - true, says it's round
        msgbox.setBorder(roundedBorder);
        msgbox.setEditable(true);
        msgbox.setPreferredSize(new Dimension((int) (W*0.95),H/10));

        msgbox.setBackground(new Color(0xD79E92));
        msgbox.setBounds(0,H-msgbox.getHeight(), msgbox.getWidth(), msgbox.getHeight());
        box.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        box.setOpaque(false);
        box.setPreferredSize(new Dimension(W, (int) (H/8.5)));
        box.add(msgbox);
        super.add(box,BorderLayout.SOUTH);
//        RoundedButton send = new RoundedButton()


        msgbox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10){
                    JPanel newmsg = new JPanel();
                    newmsg.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
                    newmsg.setOpaque(false);
                    newmsg.setPreferredSize(new Dimension((int) (W*0.95),55));
                    newmsg.add(new TextBubble(msgbox.getText(),false,W));
                    msgbox.setText("");
                    mainchatwindow.add(newmsg);
                    System.out.println(msgbox.getText());
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getSize().width - 1 ,
                getSize().height - 1 , 20, 20);

    }
}
