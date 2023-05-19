package edu.ntnu.g60.views.StartMenu;

import java.io.FileNotFoundException;
import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import edu.ntnu.g60.views.ViewValues;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class NewGamePane extends StackPane{

    private static StartMenuController controller;
    public static ChoiceBox storyChoice;
    
    public NewGamePane() throws FileNotFoundException{
        NewGamePane.controller = new StartMenuController();
        getChildren().addAll(getNewGameObjects());
    }

    private static Group getNewGameObjects() throws FileNotFoundException{
        Button startButton = ViewObjects.newButton("Start", 614-193, 375-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::startGameAction);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::goToOpeningPaneAction);
        storyChoice = ViewObjects.newChoiceBox(controller.getStories(), 614-193, 269-71, "launch_choicebox");
        ImageView background = ViewObjects.newImage(ViewValues.MENU_BACKGROUND_FOLDERNAME, ViewValues.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,ViewValues.BACKGROUND_WIDTH ,ViewValues.BACKGROUND_HEIGHT);
        return new Group(background, startButton, backButton, storyChoice);
    }

    public static String getStoryChoice(){
        return (String) storyChoice.getValue();
    }
}
