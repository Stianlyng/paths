package edu.ntnu.g60.views.StartMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewValues;
import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * CustomGamePane represents the pane in with a user can choose to import a custom game
 */
public class CustomGamePane extends StackPane{
    private static StartMenuController controller;

    /**
     * Constructs a new CustomGamePane object.
     * @throws FileNotFoundException if the file specified is not found.
    */
    public CustomGamePane() throws IOException, ClassNotFoundException {
        CustomGamePane.controller = new StartMenuController();
        getChildren().addAll(getCustomGameObjects());
    }
    
    /**
     * Adds the objects required for the custom game pane.
     *
     * @return a Group containing the custom game pane objects
     * @throws IOException if the file specified is not found
     */
    private static Group getCustomGameObjects() throws FileNotFoundException{
        Button startButton = ViewObjects.newButton("Start", 614-193, 385-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::goToNewGamePaneAction);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::goToOpeningPaneAction);
        Button importButton = ViewObjects.newButton("Import", 614-193, 327-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::importNewFileAction);
        ImageView background = ViewObjects.newImage(ViewValues.MENU_BACKGROUND_FOLDERNAME, ViewValues.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,ViewValues.BACKGROUND_WIDTH ,ViewValues.BACKGROUND_HEIGHT);
        ImageView information = ViewObjects.newImage("icons", "information.png", 830 ,20 ,35 ,35, controller::goToInformationFileStructurePaneAction);
        return new Group(background, startButton, backButton, importButton, information);
    }

}
