package edu.ntnu.g60.views.StartMenu;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

public class SelectPlayerPane extends StackPane{
    
    private static StartMenuController controller;
    public static ChoiceBox playerChoice;
    public static String playerName;
    static TextField playerNameTextField;
    
    public SelectPlayerPane() throws FileNotFoundException{
        SelectPlayerPane.controller = new StartMenuController();
        getChildren().addAll(SelectPlayerObjects());
    }

    private static Group SelectPlayerObjects() throws FileNotFoundException{
        Button startButton = ViewObjects.newButton("Create new player", 614-193, 375-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::createPlayerAction);
        //flytt players value til controller
        List<String> availablePlayers = new ArrayList<>(GameManager.getAvailablePlayers());

        String[] players = availablePlayers.toArray(new String[availablePlayers.size()]);

        playerNameTextField = ViewObjects.newTextField("Playername..", 614-193, 327-71, "text_field");
        playerChoice = ViewObjects.newChoiceBox(players, 614-193, 269-71, "launch_choicebox");
        Button chooseButton = ViewObjects.newButton("Choose player", 414-193, 325-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::playerChoiceAction);
        ImageView background = ViewObjects.newImage(ViewValues.MENU_BACKGROUND_FOLDERNAME, ViewValues.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,ViewValues.BACKGROUND_WIDTH ,ViewValues.BACKGROUND_HEIGHT);
        return new Group(background, startButton, playerChoice, playerNameTextField, chooseButton);
    }

    public static void updatePlayerName(){
        playerName = playerNameTextField.getText();
    }

    public static String getPlayerChoice(){
        return (String) playerChoice.getValue();
    }
}
