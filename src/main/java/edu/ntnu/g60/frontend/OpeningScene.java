package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class OpeningScene {
    
    public static Scene scene() throws FileNotFoundException, IOException {
        Button continueButton = ApplicationObjects.newButton("Continue", 514-193, 314-71, "launch_button");
        continueButton.setOnAction(e -> {
            continueAction();
        });
        
        Button newGameButton = ApplicationObjects.newButton("New game", 514-193, 396-71, "launch_button");
        newGameButton.setOnAction(e -> {
            newGameAction();
        });

        ImageView background = ApplicationObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        Group root = new Group(background, continueButton, newGameButton);
        root.getStylesheets().add("StyleSheet.css"); 
        return new Scene(root, 800, 600, Color.WHITE); 
    }

    public static void continueAction(){
        try {
            GameApp.switchToScene(ContinueScene.scene());
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void newGameAction(){
        try {
            GameApp.switchToScene(NewGameScene.scene());
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }
    }
}