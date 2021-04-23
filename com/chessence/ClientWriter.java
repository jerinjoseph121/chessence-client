package com.chessence;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriter extends Thread {
    ObjectOutputStream objectOutputStream;
    Socket clientSocket;

    public ClientWriter(Socket clientSocket, ObjectOutputStream objectOutputStream) {
        this.clientSocket = clientSocket;
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public void run() {
        System.out.println("writing thread activated!");
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                //System.out.print("\n>>");
                String m = input.nextLine();
                Message message = new Message(" : " + m);
                try {
                    objectOutputStream.writeObject(message);
                    //System.out.println("\nSent to server!");
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            this.objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        input.close();
    }
}