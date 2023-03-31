package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import edu.ntnu.g60.*;
import edu.ntnu.g60.goals.Goal;
import edu.ntnu.g60.goals.HealthGoal;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ContinueScene {
    static Stage stage = ApplicationFront.getStage();
    public static Scene scene() throws IOException {
        //TODO: set name of buttons to variabel names based on savefile names
        
        
        Story story = StoryParser.parse("haunted_house");
        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new HealthGoal(4));

        Game game = new Game(new Player("Alice"), story, goals); //les fra fil

        Button save1Button = ApplicationObjects.newButton("Save 1", 514-193, 278-71, "launch_button");
        save1Button.setOnAction(e -> {
            try {
                LvlScene.scene(game, game.go(SaveParser.getSaveFile(1)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        Button save2Button = ApplicationObjects.newButton("Save 2", 514-193, 345-71, "launch_button");
        save2Button.setOnAction(e -> {
            try {
                LvlScene.scene(game, game.go(SaveParser.getSaveFile(2)));
            } catch (IOException e1) {
                e1.printStackTrace();
            } //hent lvl fra save2
        });
        Button save3Button = ApplicationObjects.newButton("Save 3", 514-193, 412-71, "launch_button");
        save3Button.setOnAction(e -> {
            try {
                LvlScene.scene(game, game.go(SaveParser.getSaveFile(3)));
            } catch (IOException e1) {
                e1.printStackTrace();
            } //hent lvl fra save3
        });

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "back_button");
        backButton.setOnAction(e -> {
            try {
                stage.setScene(OpeningScene.scene());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        ImageView background = ApplicationObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);

        Group root = new Group(background, save1Button, save2Button, save3Button, backButton);
        root.getStylesheets().add("StyleSheet.css"); 
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;

    }
}
