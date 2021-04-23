package com.chessence;

import java.io.*;
import java.net.*;
import com.chessence.gui.GuiMain;

public class Main {

    public static void main(String[] args) throws IOException {

        //making a socket:
        InetAddress ip = InetAddress.getByName("localhost");
        Socket s = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        // establish the connection with server port 5056

        try {
            s = new Socket(ip, 7989);
            System.out.println("\nConnected to the server! ");
            // obtaining input and out streams
            objectOutputStream = new ObjectOutputStream(s.getOutputStream());
            objectInputStream = new ObjectInputStream(s.getInputStream());

       /* Thread writingThread = new ClientWriter(s, objectOutputStream);
        Thread readingThread = new ClientReader(s, objectInputStream);

        writingThread.start();
        readingThread.start();*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        GuiMain guiComponent = new GuiMain(s, objectOutputStream, objectInputStream);
        guiComponent.startUp();
    }
}
