package edu.ntnu.g60.frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import edu.ntnu.g60.*;
import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Player;
import edu.ntnu.g60.models.PlayerBuilder;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.utils.fileParser.FileParser;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;



public class ContinueScene {
    public static Scene scene() throws IOException, ClassNotFoundException {
       
        Button save1Button = ApplicationObjects.newButton("", 514-193, 278-71, "launch_button");
        Button save2Button = ApplicationObjects.newButton("", 514-193, 345-71, "launch_button");
        Button save3Button = ApplicationObjects.newButton("", 514-193, 412-71, "launch_button");
        
        IntStream.rangeClosed(1, 3).forEach(buttonNumber -> {
            try {
                if (SaveRegister.saveExists(buttonNumber)) {
                    Story.setCurrentSave(SaveRegister.getSave(buttonNumber));
                    Button saveButton = buttonNumber == 1 ? save1Button : buttonNumber == 2 ? save2Button : save3Button;
                    saveButton.setText(SaveRegister.getSave(buttonNumber).getSaveName());
                    saveButton.setOnAction(e -> {
                        saveAction(buttonNumber);
                    });
                } else {
                    Button saveButton = buttonNumber == 1 ? save1Button : buttonNumber == 2 ? save2Button : save3Button;
                    saveButton.setText("Empty");
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "back_button");
        backButton.setOnAction(e -> {
            backAction();
        });

        ImageView background = ApplicationObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        Group root = new Group(background, save1Button, save2Button, save3Button, backButton);
        root.getStylesheets().add("StyleSheet.css"); 
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public static void saveAction(int buttonNumber){
        try {
            LvlSwitchAnimation.animation(GameApp.getGame(), SaveRegister.getSave(buttonNumber).getPassage());
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void backAction(){
        try {
            GameApp.switchToScene(OpeningScene.scene());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
