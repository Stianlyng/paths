package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class DeathScene {
    
    public Scene scene() throws FileNotFoundException{
        Text deathText = ApplicationObjects.newText("YOU DIED", 40, false, 508-193, 349-71);

        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);
        Group root = new Group(background, deathText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

}
