package com.chessence.gui.pages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ParentPanel extends JPanel {

    protected JFrame frame;
    protected CardLayout cardLayout;
    BufferedImage backgroundImage;

    public ParentPanel(JFrame frame, CardLayout cardLayout) {
        this.frame = frame;
        this.cardLayout = cardLayout;
        Path currentRelativePath = Paths.get("");
        try {
            backgroundImage = ImageIO.read(new File(currentRelativePath.toAbsolutePath().toString() +
                    "\\com\\chessence\\gui\\images\\background.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;    //converting graphics -> graphics2D

        //getting current frame size:
        Rectangle r = frame.getBounds();
        int h = r.height;
        int w = r.width;

        //setting background opacity:
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));    //setting opacity to 8%
        g2d.drawImage(backgroundImage, 0, 0, w, h, this);   //draw the background
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));      //setting the opacity back to 100% because this affects everything ;_;
    }

    public Font getFont(String nameOfFont, int sizeOfFont) {
        try {
            Path currentRelativePath = Paths.get("");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(currentRelativePath.toAbsolutePath().toString() +
                    "\\com\\chessence\\gui\\fonts\\" + nameOfFont + ".ttf")).deriveFont((float)sizeOfFont);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font:
            ge.registerFont(customFont);
            return customFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
