package com.chessence.gui.pages;

import com.chessence.gui.pages.components.HorizontalLine;
import com.chessence.gui.pages.components.HorizontalSpace;

import com.chessence.gui.pages.createRoomPanelComponents.Body;
import com.chessence.gui.pages.createRoomPanelComponents.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateRoomPanel extends ParentPanel implements ActionListener {

    //--------------------- Common Data for Create Room Components -------------------------//
    //Data value and structure will be changed later
    public static char Player_Status = 'P';
    public static boolean isPrivate = true;

    public static String[] PLAYERS = {"Beeta Samad", "-"};
    public static String[] SPECTATORS = {"Ritika_Singh", "Namrata_Prasad", "-", "-"};

    //--------------------------------------------------------------------------------------//

    static String PINK_MAROON = "#734046";
    static String DARK_PINK_MAROON = "#583035";
    static String CREAM_ORANGE = "#E79E4F";

    static Font leaveRoomButtonFont;

    private final RoundedButton leaveRoomButton;

    public CreateRoomPanel(JFrame frame, CardLayout cardLayout){
        super(frame, cardLayout);

        //getting current frame size:
        Rectangle r = frame.getBounds();
        int heightOfFrame = r.height;
        int widthOfFrame = r.width;

        //---------------------Design this panel here---------------------//

        //adding a small initial space in the top (above the heading)
        this.add(new HorizontalSpace(widthOfFrame, 7));

        ///////////// HEADING /////////////

        //initializing title panel component:
        JPanel titlePanel = new JPanel();

        JLabel heading = new JLabel("Create a Room");
        heading.setFont(getFont("Roboto-Medium", getResponsiveFontSize(68)));
        heading.setForeground(new Color(0x895158));
        titlePanel.add(heading);
        titlePanel.add(new HorizontalSpace(widthOfFrame, 0));

        //adding a horizontal line:
        titlePanel.add(new HorizontalLine((int) (0.90 * widthOfFrame), 5, new Color(0x895158)));
        titlePanel.add(new HorizontalSpace(widthOfFrame, (int) (heightOfFrame * 0.1)));
        titlePanel.setOpaque(false);

        ///////////// BODY /////////////

        //initializing body panel component
        Body bodyPanel = new Body();
        bodyPanel.setOpaque(false);

        ///////////// FOOTER /////////////

        //initializing footer panel component
        JPanel footerPanel = new JPanel();
        leaveRoomButtonFont = getFont("Roboto-Medium", getResponsiveFontSize(40));

        leaveRoomButton = new RoundedButton("Leave Room", leaveRoomButtonFont, Color.decode(CREAM_ORANGE),
                Color.decode(PINK_MAROON), Color.decode(DARK_PINK_MAROON), 10, new Dimension(175, 40));

        footerPanel.setPreferredSize(new Dimension(widthOfFrame, (int)(heightOfFrame * 0.1)));
        footerPanel.add(leaveRoomButton);

        leaveRoomButton.addActionListener(this);

        footerPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 20, 10));
        footerPanel.setBackground(Color.blue);
        footerPanel.setOpaque(false);

        this.setLayout(new BorderLayout());

        //adding all three components(title, body, footer) into the create room panel
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);

        //-----------------------End of designing------------------------//
    }

    @Override
    public void actionPerformed(ActionEvent e){
        //returning to main menu
        if(e.getSource() == leaveRoomButton){
            cardLayout.show(container, "MainMenu");
        }
    }
}
