package edu.ntnu.g60.controllers;

import edu.ntnu.g60.views.App;
import edu.ntnu.g60.views.MainMenu;

/**
 * Controller for the InitialScene
 */
public class SelectPlayerController {

    public void handleContinueButton(String playerName, int WIDTH, int HEIGHT) {
        MainMenu menuScene = new MainMenu(playerName, WIDTH, HEIGHT);
        App.changeRootPane(menuScene.getLayout());
    }
}