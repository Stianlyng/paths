package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class DeathScene {
    
    public static Scene scene() throws FileNotFoundException{
        Text deathText = ApplicationObjects.newText("GAME OVER", 40, false, 508-193, 349-71);

        ImageView background = ApplicationObjects.newImage("backgrounds", "Background1.png", 0 ,0 ,1643 ,1006);
        Group root = new Group(background, deathText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

}
