package edu.ntnu.g60.views.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewValues;
import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class SettingsPane extends StackPane{
    private static StartMenuController controller;
    
    public SettingsPane() throws FileNotFoundException, IOException {
        SettingsPane.controller = new StartMenuController();
        getChildren().addAll(getSettingObjects());
    }

    private static Group getSettingObjects() throws FileNotFoundException{
        Text informationText = ViewObjects.newText("Settings", 45, false, 415, 50);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::goToOpeningPaneAction);
        Text volumeText = ViewObjects.newText("Volume:", 25, false, 130, 100);
        Slider volumeSlider = ViewObjects.newSlider(130, 120);
        return new Group(background, backButton, informationText, volumeSlider, volumeText);
    }
}