package edu.ntnu.g60.views.StartMenu;


import java.io.FileNotFoundException;
import java.util.List;

import edu.ntnu.g60.controllers.GameController;
import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.models.game.GameManager;
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
    public static String saveName;
    static TextField saveNameTextField;
    public static ChoiceBox storyChoice;
    
    public NewGamePane() throws FileNotFoundException{
        NewGamePane.controller = new StartMenuController();
        getChildren().addAll(getNewGameObjects());
    }

    //move lsit to controller
    private static Group getNewGameObjects() throws FileNotFoundException{
        Button startButton = ViewObjects.newButton("Start", 614-193, 375-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::startAction);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::backAction);
        List<String> availableStories = GameManager.getPlayerSaves(GameController.getGameManager().getPlayer().getName());
        String[] stories = availableStories.toArray(new String[0]);
        storyChoice = ViewObjects.newChoiceBox(stories, 614-193, 269-71, "launch_choicebox");
        ImageView background = ViewObjects.newImage(ViewValues.MENU_BACKGROUND_FOLDERNAME, ViewValues.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,ViewValues.BACKGROUND_WIDTH ,ViewValues.BACKGROUND_HEIGHT);
        saveNameTextField = ViewObjects.newTextField("Savename..", 614-193, 327-71, "text_field");
        return new Group(background, startButton, backButton, saveNameTextField, storyChoice);
    }

    public static void updateSaveName(){
        saveName = saveNameTextField.getText();
    }

    public static String getStoryChoice(){
        return (String) storyChoice.getValue();
    }
}
