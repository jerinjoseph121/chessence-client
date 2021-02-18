package com.chessence;

import com.chessence.gui.GuiMain;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello!");
        var guiComponent = new GuiMain();
        guiComponent.startUp();
    }
}
