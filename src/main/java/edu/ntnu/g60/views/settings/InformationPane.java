package edu.ntnu.g60.views.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class InformationPane extends StackPane{
    
    private static StartMenuController controller;


    public InformationPane() throws FileNotFoundException, IOException {
        InformationPane.controller = new StartMenuController();
        getChildren().addAll(getInformationObjects());
    }

    private static Group getInformationObjects() throws FileNotFoundException{
        Text informationText = ViewObjects.newText("Information", 45, false, 415, 50);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, "back_button", "back_hover", controller::backAction);
        return new Group(background, backButton, informationText);
    }
}
