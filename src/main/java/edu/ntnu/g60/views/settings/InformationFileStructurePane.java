package edu.ntnu.g60.views.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import edu.ntnu.g60.views.ViewValues;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class InformationFileStructurePane extends StackPane{
    
    private static StartMenuController controller;


    public InformationFileStructurePane() throws FileNotFoundException, IOException {
        InformationFileStructurePane.controller = new StartMenuController();
        getChildren().addAll(getInformationObjects());
    }

    private static Group getInformationObjects() throws FileNotFoundException {
        Text informationTitle = ViewObjects.newText("File format", 45, false, 415, 50);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::backCustomAction);
        Text informationText = ViewObjects.newText(
            "Haunted House\n" +
            "::Beginnings \n" +
            "You are in a small, dimly lit room. There is a door in front of you.\n" +
            "[Try to open the door](Another room)\n" +
            "\n" + 
            "::Another room\n" +
            "The door opens to another room. You see a desk with a large, dusty book.\n" +
            "[Open the book](The book of spells)\n" +
            "[Go back](Beginnings)\n" +
            "...", 22, false, 170, 100);
        return new Group(background, backButton, informationTitle, informationText);
    }
}
