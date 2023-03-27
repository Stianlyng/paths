package edu.ntnu.g60.Frontend;

import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class OpeningScene {
    static Stage stage = ApplicationFront.getStage();

    public static Scene scene() throws FileNotFoundException{
        Button continueButton = ApplicationObjects.newButton("Continue", 514-193, 314-71, "black", "#32a8a2", 158, 51, 24);
        continueButton.setOnAction(e -> {
            try {
                stage.setScene(ContinueScene.scene());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        
        Button newGameButton = ApplicationObjects.newButton("New game", 514-193, 396-71, "black", "#32a8a2", 158, 51, 24);
        newGameButton.setOnAction(e -> {
            try {
                stage.setScene(NewGameScene.scene());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);
        Group root = new Group(background, continueButton, newGameButton);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;

    }
}
