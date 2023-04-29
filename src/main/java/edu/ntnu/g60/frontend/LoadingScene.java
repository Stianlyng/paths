package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class LoadingScene {
    public static Scene scene() throws FileNotFoundException{
        //make layout object and make it center
        VBox layout = new VBox();         
        HBox rad1 = new HBox();         
        HBox rad2 = new HBox();         
        HBox rad3 = new HBox();         
        layout.setSpacing(265.0);         
        layout.setAlignment(Pos.CENTER);         
        rad1.setSpacing(290.0);         
        rad2.setSpacing(290.0);         
        rad3.setSpacing(290.0);         
        rad1.setAlignment(Pos.CENTER);         
        rad2.setAlignment(Pos.CENTER);         
        rad3.setAlignment(Pos.CENTER);         
        layout.getChildren().addAll(rad1, rad2, rad3);
        Text loadingText = new Text("Continueing");
        loadingText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        Text placeHolder = new Text(" ");
        Text placeHolder2 = new Text(" ");
        rad2.getChildren().addAll(placeHolder, loadingText, placeHolder2);

        ImageView background = ApplicationObjects.newImage("backgrounds", "Background1.png", 0 ,0 ,1643 ,1006);

        Group root = new Group(background, layout);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

}
