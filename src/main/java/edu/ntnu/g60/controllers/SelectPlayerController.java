package edu.ntnu.g60.controllers;

import edu.ntnu.g60.views.MainMenu;
import javafx.stage.Stage;

/**
 * Controller for the InitialScene
 */
public class SelectPlayerController {

    public void handleContinueButton(Stage primaryStage, String playerName, int WIDTH, int HEIGHT) {
        MainMenu menuScene = new MainMenu(primaryStage, playerName, WIDTH, HEIGHT);
        primaryStage.setScene(menuScene.getScene());
    }
}