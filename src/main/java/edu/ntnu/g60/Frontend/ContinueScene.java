package edu.ntnu.g60.Frontend;

import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ContinueScene {
    static Stage stage = ApplicationFront.getStage();
    public static Scene scene() throws FileNotFoundException {
        //TODO: set name of buttons to variabel names based on savefile names
        Button save1Button = ApplicationObjects.newButton("Save 1", 514-193, 278-71, "black", "#32a8a2", 158, 51, 24);
        save1Button.setOnAction(e -> {
            LvlScene.scene(0); //hent lvl fra save1
        });
        Button save2Button = ApplicationObjects.newButton("Save 2", 514-193, 345-71, "black", "#32a8a2", 158, 51, 24);
        save2Button.setOnAction(e -> {
            LvlScene.scene(0); //hent lvl fra save2
        });
        Button save3Button = ApplicationObjects.newButton("Save 3", 514-193, 412-71, "black", "#32a8a2", 158, 51, 24);
        save3Button.setOnAction(e -> {
            LvlScene.scene(0); //hent lvl fra save3
        });

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "black", "#32a8a2", 74, 35, 15);
        backButton.setOnAction(e -> {
            try {
                stage.setScene(OpeningScene.scene());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, save1Button, save2Button, save3Button, backButton);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;

    }
}
