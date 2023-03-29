package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

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
        Button startButton = ApplicationObjects.newButton("Start", 514-193, 370-71, "launch_button");
        startButton.setOnAction(e -> {
            //add overwrite om det er for mange saves
            //lag ny save file
            try {
                LvlScene.scene(0);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } //hent lvl fra ny save file
        });    
            //TODO: add samme som over bare til riktig scene
        

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "back_button");
        backButton.setOnAction(e -> {
            try {
                stage.setScene(OpeningScene.scene());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        TextField saveNameTextField = ApplicationObjects.newTextField("Savename..", 514-193, 327-71, "text_field");
        ImageView background = ApplicationObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);

        Group root = new Group(background, backButton, startButton, saveNameTextField);
        root.getStylesheets().add("StyleSheet.css"); 
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

}
