package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class FirstScene {
    public static Scene scene(String title) throws FileNotFoundException{
        Text lvlText = ApplicationObjects.newText(title, 40, false, 500-193, 349-71);
        
        ImageView background = ApplicationObjects.newImage("backgrounds", "Background1.png", 0 ,0 ,1643 ,1006);

        Group root = new Group(background, lvlText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

}
