package com.chessence.gui.pages.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class TextBubble extends JPanel {
    private int strokeThickness = 5;
    private int padding = strokeThickness / 2;
    private int radius = 15;
    private int arrowSize = 6;
    public boolean sender;
    private String text;
    private int widthOfText;
    private int width;
    private  JTextArea msg = new JTextArea() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            // paint the component at the scale of the page.
            super.paintComponent(g2);
        }
    };
    public TextBubble(String text, boolean sender, int width) {

        this.sender = sender;
        this.text = text;
        this.width = width;
        //this.setPreferredSize();
        super.setLayout(new FlowLayout(FlowLayout.LEADING, sender ? 15 : 5, 5));
        msg.setText(text);
        msg.setSize((int)(width*0.6), 200);
        //msg.setBounds(0, 0, (int)(width*0.8), 200);
        //msg.setPreferredSize(new Dimension((int)(width*0.3), 200));
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);
        msg.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        msg.setForeground(new Color(sender ? 0xE5E5E5 : 0x841522));
        msg.setBackground(new Color(sender ? 0x841522 : 0xE5E5E5));
        msg.setBorder(null);
        super.add(msg);

    }
    @Override
    protected void paintComponent(final Graphics g) {

        widthOfText = g.getFontMetrics().stringWidth(text);
        int numberOfLines = (int)Math.ceil((widthOfText/(width*0.6)));
        this.setPreferredSize(new Dimension((int) (width),50*numberOfLines));

        final Graphics2D graphics2D = (Graphics2D) g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHints(qualityHints);
        graphics2D.setColor(new Color(sender ? 0x841522 : 0xE5E5E5));
        graphics2D.setStroke(new BasicStroke(strokeThickness));
        int x = padding + strokeThickness + arrowSize;
        int width = getWidth() - arrowSize - (strokeThickness * 2);
        int height = getHeight() - strokeThickness;
        graphics2D.fillRect(sender ? x : padding, padding, width, height);
        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(sender? x : padding, padding, width, height, radius, radius);
        Polygon arrow = new Polygon();

        if(sender){
            arrow.addPoint(14, 6);
            arrow.addPoint(arrowSize + 2, 10);
            arrow.addPoint(14, 12);
        }
        else{
            arrow.addPoint(width, 6);
            arrow.addPoint(width + arrowSize, 10);
            arrow.addPoint(width, 12);
        }

        Area area = new Area(rect);
        area.add(new Area(arrow));
        graphics2D.draw(area);
        graphics2D.dispose();
        //this.repaint();
        msg.repaint();
    }
}
