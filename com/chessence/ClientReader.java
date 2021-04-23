package com.chessence;

import com.chessence.gui.pages.CreateRoomPanel;
import com.chessence.gui.pages.ParentPanel;
import com.chessence.gui.pages.components.Board;
import com.chessence.gui.pages.components.ChatBox;
import com.chessence.gui.pages.components.Tile;
import com.chessence.gui.pages.createRoomPanelComponents.bodyComponents.PlayersPanel;
import com.chessence.gui.pages.createRoomPanelComponents.bodyComponents.SpectatorsPanel;
import javafx.util.Pair;

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
                if (receivedObject instanceof Message) {
                    System.out.println("\nReceived a message at client!");
                    //=======================================================================================================
                    //if Message is of type chat:
                    if (((Message) receivedObject).getTypeOfMessage().contains("chat")) {
                        ChatBox.addNewMessage(((Message) receivedObject).getMessage(), true);
                        //System.out.println("\n" + ((Message) receivedObject).getMessage());
                    }

                    //=======================================================================================================
                    //if a new player has joined our lobby:
                    else if (((Message) receivedObject).getTypeOfMessage().contains("newPlayerJoinedLobby")) {
                        System.out.println("New player joined the lobby!");
                        if (((Message) receivedObject).getSecondaryMessage().contains("player")) {
                            //------------------------------------------------------------------------
                            //if player:
                            if (CreateRoomPanel.PLAYERS[0] == "-") {
                                CreateRoomPanel.PLAYERS[0] = ((Message) receivedObject).getMessage();
                                PlayersPanel.updatePlayerNames();
                            } else if (CreateRoomPanel.PLAYERS[1] == "-") {
                                CreateRoomPanel.PLAYERS[1] = ((Message) receivedObject).getMessage();
                                PlayersPanel.updatePlayerNames();
                            } else {
                                System.out.println("ERROR - PLAYERS FULL! CANT ADD NEW PLAYER!");
                            }
                            //------------------------------------------------------------------------
                        } else if (((Message) receivedObject).getSecondaryMessage().contains("spectator")) {
                            //------------------------------------------------------------------------
                            //if spectator:
                            for (int i = 0; i < 4; i++) {
                                if (CreateRoomPanel.SPECTATORS[i] == "-") {
                                    CreateRoomPanel.SPECTATORS[i] = ((Message) receivedObject).getMessage();
                                    break;
                                }
                            }
                            SpectatorsPanel.updateSpecatators();
                            //------------------------------------------------------------------------
                        }
                    }

                    //=======================================================================================================
                    //when a player changes his status:
                    else if (((Message) receivedObject).getTypeOfMessage().contains("anotherPlayerChangedStatus")) {
                        System.out.println("\nplayer has changed status! - " + ((Message) receivedObject).getMessage());
                        String movedPlayersName = ((Message) receivedObject).getMessage();
                        if (((Message) receivedObject).getSecondaryMessage().contains("wasPlayer")) {
                            //if the player to change the status was in the PLAYERS section:
                            for (int i = 0; i < 2; i++) {
                                if (CreateRoomPanel.PLAYERS[i].contains(movedPlayersName)) {
                                    CreateRoomPanel.PLAYERS[i] = "-";
                                    for (int j = 0; j < 4; j++) {
                                        if (CreateRoomPanel.SPECTATORS[j] == "-") {
                                            CreateRoomPanel.SPECTATORS[j] = movedPlayersName;
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        else if(((Message) receivedObject).getSecondaryMessage().contains("wasSpectator")){
                            for (int i = 0; i < 4; i++) {
                                if (CreateRoomPanel.SPECTATORS[i].contains(movedPlayersName)) {
                                    CreateRoomPanel.SPECTATORS[i] = "-";
                                    for (int j = 0; j < 2; j++) {
                                        if (CreateRoomPanel.PLAYERS[j] == "-") {
                                            CreateRoomPanel.PLAYERS[j] = movedPlayersName;
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        PlayersPanel.updatePlayerNames();
                        SpectatorsPanel.updateSpecatators();
                    }
                    //=======================================================================================================
                    //quitting this while loop and hence finishing the thread itself.
                    else if(((Message) receivedObject).getTypeOfMessage().contains("playerLeftLobby")){
                        break;
                    }

                    else if(((Message) receivedObject).getTypeOfMessage().contains("gameStarted")){
                        ParentPanel.cardLayout.show(ParentPanel.container, "GameScreen");
                    }
                } else if (receivedObject instanceof Move) {
                    var move = (Move) receivedObject;

                    //updating on our board:

                    System.out.println("\nMoving on our board! ");
                    //update the boardMatrix with the move instruction:
                    Board.boardMatrix = Board.boardMatrix[move.getFrom()[0]][move.getFrom()[1]].move(new Pair<>(move.getTo()[0],  move.getTo()[1]), Board.boardMatrix);

                    //set all the variables to null has the piece is no more in the current tile:
                    Tile.highlightedCoordinates = null;
                    Tile.currentSelected = null;

                    //Board.tileMatrix[move.getTo()[0]][move.getTo()[1]].updateImage(Board.boardMatrix[move.getTo()[0]][move.getTo()[1]].getImagePath());
                    //Board.updateBoard();

                    //repainting the "from" tile:
                    Board.tileMatrix[move.getFrom()[0]][move.getFrom()[1]].validate();
                    Board.tileMatrix[move.getFrom()[0]][move.getFrom()[1]].repaint();

                    //Board.tileMatrix[move.getTo()[0]][move.getTo()[1]].tileUpdate(toTile.isWhite, toTile.tileCoordinates, toTile.len, Board.boardMatrix, Board.tileMatrix);

                    Board.tileMatrix[move.getTo()[0]][move.getTo()[1]].updateImage();
                    Board.tileMatrix[move.getTo()[0]][move.getTo()[1]].validate();
                    Board.tileMatrix[move.getTo()[0]][move.getTo()[1]].repaint();
                    //
                    //-----------------------

                    System.out.println("\nMove operation: [" + move.getFrom()[0] + ", " + move.getFrom()[1] + "] -> [" + move.getTo()[0] + ", " + move.getTo()[1] + "]");
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
    }
}
