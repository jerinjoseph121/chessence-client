package com.chessence;

import java.io.Serializable;

public class Message implements Serializable {
    private String message = "";
    private String secondaryMessage = "";
    private String typeOfMessage = "";
    private boolean isNewLobbyRequest = false;
    private static final long serialVersionUID = 7989L;


    public Message(String message){
        this.message = message;
        this.typeOfMessage = "chat";
    }

    public Message(String message, String typeOfMessage){
        this.message = message;
        this.typeOfMessage = typeOfMessage; // -> "chat" or "lobbyInfo"
    }

    public Message(String message, String typeOfMessage, boolean isNewLobbyRequest){
        this.message = message;
        this.typeOfMessage = typeOfMessage; // -> "chat" or "lobbyInfo"
        this.isNewLobbyRequest = isNewLobbyRequest;
    }

    public String getMessage() {
        return message;
    }


    public String getTypeOfMessage() {
        return typeOfMessage;
    }

    public boolean isNewLobbyRequest() {
        return isNewLobbyRequest;
    }

    public void setSecondaryMessage(String secondaryMessage) {
        this.secondaryMessage = secondaryMessage;
    }

    public String getSecondaryMessage() {
        return secondaryMessage;
    }
}