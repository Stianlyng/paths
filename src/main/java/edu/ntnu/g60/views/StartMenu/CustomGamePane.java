package edu.ntnu.g60.views.StartMenu;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CustomGamePane extends StackPane{
    private static StartMenuController controller;

    public CustomGamePane() throws IOException, ClassNotFoundException {
        CustomGamePane.controller = new StartMenuController();
        getChildren().addAll(getCustomGameObjects());
    }
    
    private static Group getCustomGameObjects() throws FileNotFoundException{
        Button startButton = ViewObjects.newButton("Start", 614-193, 385-71, "launch_button", "launch_hover", controller::startAction);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, "back_button", "back_hover", controller::backAction);
        Button importButton = ViewObjects.newButton("Import", 614-193, 327-71, "launch_button", "launch_hover", controller::importFile);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        return new Group(background, startButton, backButton, importButton);
    }
}
