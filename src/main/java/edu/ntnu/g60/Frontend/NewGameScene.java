package edu.ntnu.g60.Frontend;

import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class NewGameScene {
    static Stage stage = ApplicationFront.getStage();

    public static Scene scene() throws FileNotFoundException{
        Button startButton = ApplicationObjects.newButton("Start", 514-193, 370-71, "black", "#32a8a2", 158, 51, 24);
        startButton.setOnAction(e -> {
            //add overwrite om det er for mange saves
            //lag ny save file
            LvlScene.scene(0); //hent lvl fra ny save file
        });    
            //TODO: add samme som over bare til riktig scene
        

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "black", "#32a8a2", 74, 35, 15);
        backButton.setOnAction(e -> {
            try {
                stage.setScene(OpeningScene.scene());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        TextField saveNameTextField = ApplicationObjects.newTextField("Savename..", 514-193, 327-71, "black", "#32a8a2", 158, 27, 13);
        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, backButton, startButton, saveNameTextField);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

}
