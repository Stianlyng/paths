package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static Scene scene() throws IOException, ClassNotFoundException {
       
        Story story = StoryParser.parse("haunted_house");
        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new HealthGoal(4));
        Game game = new Game(new Player("Alice"), story, goals);

        Button save1Button = ApplicationObjects.newButton("", 514-193, 278-71, "launch_button");
        Button save2Button = ApplicationObjects.newButton("", 514-193, 345-71, "launch_button");
        Button save3Button = ApplicationObjects.newButton("", 514-193, 412-71, "launch_button");
        if(SaveRegister.saveExists(1)){
            Story.setCurrentSave(SaveRegister.getSave(1));
            save1Button.setText(SaveRegister.getSave(1).getSaveName());
            save1Button.setOnAction(e -> {
                try {
                    LvlScene.scene(game, SaveRegister.getSave(1).getPassage());
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            });
        } else{
            save1Button.setText("Empty");
        }
        
        
        if(SaveRegister.saveExists(2)){
            Story.setCurrentSave(SaveRegister.getSave(2));
            save2Button.setText(SaveRegister.getSave(2).getSaveName());
            save2Button.setOnAction(e -> {
                try {
                    LvlScene.scene(game, SaveRegister.getSave(2).getPassage());
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            });
        } else{
            save2Button.setText("Empty");
        }
        
        if(SaveRegister.saveExists(3)){
            Story.setCurrentSave(SaveRegister.getSave(3));
            save3Button.setText(SaveRegister.getSave(3).getSaveName());
            save3Button.setOnAction(e -> {
                try {
                    LvlScene.scene(game, SaveRegister.getSave(3).getPassage());
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            });
        } else{
            save3Button.setText("Empty");
        }

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "back_button");
        backButton.setOnAction(e -> {
            try {
                stage.setScene(OpeningScene.scene());
            } catch (IOException e1) {
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
