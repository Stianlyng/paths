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
        Button continueButton = ViewObjects.newButton("Continue", 614-193, 278-71, "launch_button", "launch_hover", controller::continueAction);
        Button newGameButton = ViewObjects.newButton("New game", 614-193, 345-71, "launch_button", "launch_hover", controller::newGameAction);
        Button customButton = ViewObjects.newButton("Custom", 614-193, 412-71, "launch_button", "launch_hover", controller::customAction);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        ImageView information = ViewObjects.newImage("icons", "information.png", 830 ,20 ,35 ,35, controller::informationAction);
        ImageView settings = ViewObjects.newImage("icons", "settings.png", 130 ,20 ,35 ,35, controller::settingsAction);
        return new Group(background, continueButton, newGameButton, customButton, information, settings);
    }
}