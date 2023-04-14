package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class OpeningScene {
    static Stage stage = ApplicationFront.getStage();

    public static Scene scene() throws FileNotFoundException, IOException{
        Button continueButton = ApplicationObjects.newButton("Continue", 514-193, 314-71, "launch_button");
        continueButton.setOnAction(e -> {
            try {
                stage.setScene(ContinueScene.scene());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        
        Button newGameButton = ApplicationObjects.newButton("New game", 514-193, 396-71, "launch_button");
        newGameButton.setOnAction(e -> {
            try {
                stage.setScene(NewGameScene.scene());
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });

        ImageView background = ApplicationObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        Group root = new Group(background, continueButton, newGameButton);
        root.getStylesheets().add("StyleSheet.css"); 
        Scene scene = new Scene(root, 800, 600, Color.WHITE); 
        return scene;

    }
}
