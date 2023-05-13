package edu.ntnu.g60.views.StartMenu;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewValues;
import edu.ntnu.g60.views.ViewObjects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CustomGamePane extends StackPane{
    private static StartMenuController controller;

    public CustomGamePane() throws IOException, ClassNotFoundException {
        CustomGamePane.controller = new StartMenuController();
        getChildren().addAll(getCustomGameObjects());
    }
    
    private static Group getCustomGameObjects() throws FileNotFoundException{
        Button startButton = ViewObjects.newButton("Start", 614-193, 385-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::startCustomAction);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::backAction);
        Button importButton = ViewObjects.newButton("Import", 614-193, 327-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::importFile);
        ImageView background = ViewObjects.newImage(ViewValues.MENU_BACKGROUND_FOLDERNAME, ViewValues.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,ViewValues.BACKGROUND_WIDTH ,ViewValues.BACKGROUND_HEIGHT);
        ImageView information = ViewObjects.newImage("icons", "information.png", 830 ,20 ,35 ,35, controller::informationFileStructureAction);
        return new Group(background, startButton, backButton, importButton, information);
    }

    public static void addImportSucessfullText(CustomGamePane pane) throws FileNotFoundException{
        Text importedText = ViewObjects.newText("Story Imported", 25, false, 0, 0);
        StackPane.setAlignment(importedText, Pos.TOP_CENTER);
        StackPane.setMargin(importedText, new Insets(50, 50, 50, 50)); 
        pane.getChildren().addAll(importedText);
    }
}
