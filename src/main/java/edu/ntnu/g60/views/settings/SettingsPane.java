package edu.ntnu.g60.views.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, "back_button", "back_hover", controller::backAction);
        Text volumeText = ViewObjects.newText("Volume:", 25, false, 130, 100);
        

        //TODO: MOVE
        Slider volumeSlider = new Slider(0, 100, 50);
        volumeSlider.setId("slider");
        volumeSlider.setLayoutX(130);
        volumeSlider.setLayoutY(120);
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double newVolume = newValue.doubleValue() / 100.0;
                setApplicationVolume(newVolume);
            }
        });


        return new Group(background, backButton, informationText, volumeSlider, volumeText);
    }

    //move
    private static void setApplicationVolume(double volume) {
        // Set the volume of the application using the JavaFX MediaPlayer or other relevant API
        // Example: mediaPlayer.setVolume(volume);
        System.out.println("Application volume set to " + volume);
    }
}
