package edu.ntnu.g60.views.StartMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class OpeningPane extends StackPane{
    
    private static StartMenuController controller;
    public static String saveName;

    public OpeningPane() throws FileNotFoundException, IOException {
        OpeningPane.controller = new StartMenuController();
        getChildren().addAll(getStartMenuObjects());
    }

    private static Group getStartMenuObjects() throws FileNotFoundException{
        Button continueButton = ViewObjects.newButton("Continue", 514-193, 314-71, "launch_button", controller::continueAction);
        Button newGameButton = ViewObjects.newButton("New game", 514-193, 396-71, "launch_button", controller::newGameAction);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        return new Group(continueButton, newGameButton, background);
    }

}