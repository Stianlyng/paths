package edu.ntnu.g60.Frontend;

import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class FirstScene {
    public static Scene scene(int lvl) throws FileNotFoundException{
        Text lvlText = ApplicationObjects.newText("LVL " + lvl, 40, false, 541-193, 349-71);
        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, lvlText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

}
