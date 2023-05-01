package edu.ntnu.g60.views.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.Values;
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

    private static Group getInformationObjects() throws FileNotFoundException {
        Text informationTitle = ViewObjects.newText("Information", 45, false, 415, 50);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, Values.BACK_BUTTON_ID, Values.BACK_BUTTON_HOVER_ID, controller::backAction);
        Text informationText = ViewObjects.newText(
            "   Welcome to the exciting world of the passage-based game PATHS.\n" +
            " This game was created by Olav and Stian as part of our school project. \n" +
            "In this game, you will embark on a thrilling adventure through a world \n" +
            " filled with danger and excitement. Along the way, you will encounter \n" +
            "            epic fight scenes that will test your skills and courage.\n" + 
            " With a unique gameplay style that lets you choose your own path and \n" +
            " make your own decisions, you will be fully immersed in this amazing \n" +
            " gaming experience. So gear up and get ready to enter a world like no \n" +
            "           other as you journey through this passage-based game.\n", 22, false, 170, 100);
        Text projectLink = ViewObjects.newText("*View Repository*", 22, true, 200, 595-71, controller::linkAction);
        return new Group(background, backButton, informationTitle, informationText, projectLink);
    }
}