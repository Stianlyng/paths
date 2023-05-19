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

public class OpeningPane extends StackPane{
    
    private static StartMenuController controller;
    public static String saveName;

    public OpeningPane() throws FileNotFoundException, IOException {
        OpeningPane.controller = new StartMenuController();
        getChildren().addAll(getStartMenuObjects());
    }

    private static Group getStartMenuObjects() throws FileNotFoundException{
        Button continueButton = ViewObjects.newButton("Continue", 614-193, 278-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::goTocontinueGamePaneAction, "Press here to continue from a previous save");
        Button newGameButton = ViewObjects.newButton("New game", 614-193, 345-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::goToNewGamePaneAction, "Press here to start a new game");
        Button customButton = ViewObjects.newButton("Custom", 614-193, 412-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::goToCustomGamePaneAction, "Press here to import your own custom game");
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::goToSelectPlayerPaneAction);
        ImageView background = ViewObjects.newImage(ViewValues.MENU_BACKGROUND_FOLDERNAME, ViewValues.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,ViewValues.BACKGROUND_WIDTH ,ViewValues.BACKGROUND_HEIGHT);
        ImageView information = ViewObjects.newImage("icons", "information.png", 830 ,20 ,35 ,35, controller::goToInformationPaneAction);
        ImageView settings = ViewObjects.newImage("icons", "settings.png", 130 ,20 ,35 ,35, controller::goToSettingsPaneAction);
        return new Group(background, continueButton, newGameButton, customButton, information, settings, backButton);
    }
}