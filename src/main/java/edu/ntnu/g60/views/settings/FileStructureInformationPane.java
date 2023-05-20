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

/**
 * FileStructureInformationPane represents a pane for a file structure information pane.
 * @author olav sie
 */
public class FileStructureInformationPane extends StackPane{
    
    private static StartMenuController controller;

    /**
     * Constructs a new InformationFileStructurePane object.
     * @throws FileNotFoundException if the file specified is not found.
    */
    public FileStructureInformationPane() throws FileNotFoundException, IOException {
        FileStructureInformationPane.controller = new StartMenuController();
        getChildren().addAll(getInformationObjects());
    }

    /**
     * Adds the objects required for the InformationFileStructurePane.
     *
     * @return a Group containing the informationFileStructurePane objects
     * @throws FileNotFoundException if the file specified is not found
     */
    private static Group getInformationObjects() throws FileNotFoundException {
        Text informationTitle = ViewObjects.newText("File format", 45, false, 415, 50);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::goToCustomGamePaneAction);
        Text informationText = ViewObjects.newText(
            "Haunted House\n" +
            "::Beginnings \n" +
            "You are in a small, dimly lit room. There is a door in front of you.\n" +
            "[Try to open the door](Another room)\n" +
            "[Leave](game over)\n" +
            "\n" + 
            "::Another room\n" +
            "The door opens to another room. You see a desk with a large, dusty book.\n" +
            "[Open the book](The book of spells)\n" +
            "[Go back](Beginnings)\n" +
            "...\n\n\n" +
            "PS: Every passage must have two links", 22, false, 170, 100);
        return new Group(background, backButton, informationTitle, informationText);
    }
}
