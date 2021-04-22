package com.chessence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReader extends Thread {

    ObjectInputStream objectInputStream;
    Socket clientSocket;

    public ClientReader(Socket clientSocket, ObjectInputStream objectInputStream) {
        this.clientSocket = clientSocket;
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void run() {
        System.out.println("reading thread activated!");
        while (true) {
            try {
                Object receivedObject = objectInputStream.readObject();
                //System.out.println("\nReceived a message!");
                if (receivedObject instanceof Message) {
                    System.out.println("\n" + ((Message) receivedObject).getMessage());
                } else if (receivedObject instanceof Move) {
                    var move = (Move) receivedObject;
                    System.out.println("\nMove operation: " + move.getFrom()[0] + ", " + move.getFrom()[1] + "] -> [" + move.getTo()[0] + ", " + move.getTo()[1] + "]");
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
            //System.out.println(received);
        }
        try {
            this.objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
