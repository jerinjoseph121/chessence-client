package com.chessence.gui.pages;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadingPanel extends ParentPanel {

    public LoadingPanel(JFrame frame, CardLayout cardLayout) {
        super(frame, cardLayout);
        this.setLayout(new BorderLayout());

        ClassLoader cldr = this.getClass().getClassLoader();

        JLabel imageLabel = new JLabel();
        imageLabel.setLayout(new BorderLayout());
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(
                "../images/loaders/loader1.gif"));
        imageLabel.setIcon(ii);
        this.add(imageLabel, BorderLayout.CENTER);

    }
}
