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
import javafx.scene.text.Text;

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
        Text informationTitle = ViewObjects.newText("Select or create a player", 45, false, 285, 50);
        Button startButton = ViewObjects.newButton("Create", 614-193, 475-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::createPlayerAction);
        playerNameTextField = ViewObjects.newTextField("Playername..", 614-193, 427-71, "text_field");
        playerChoice = ViewObjects.newChoiceBox(controller.getPlayerNames(), 614-193, 187-71, "launch_choicebox");
        Button chooseButton = ViewObjects.newButton("Continue", 614-193, 243-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::ChoosePlayerAction);
        ImageView background = ViewObjects.newImage(ViewValues.MENU_BACKGROUND_FOLDERNAME, ViewValues.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,ViewValues.BACKGROUND_WIDTH ,ViewValues.BACKGROUND_HEIGHT);
        return new Group(background, startButton, playerChoice, playerNameTextField, chooseButton, informationTitle);
    }

    public static void updatePlayerName(){
        playerName = playerNameTextField.getText();
    }

    public static String getPlayerChoice(){
        return (String) playerChoice.getValue();
    }
}
