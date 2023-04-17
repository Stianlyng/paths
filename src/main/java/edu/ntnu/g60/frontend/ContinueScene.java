package edu.ntnu.g60.frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import edu.ntnu.g60.*;
import edu.ntnu.g60.goals.Goal;
import edu.ntnu.g60.goals.HealthGoal;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;



public class ContinueScene {
    public static Scene scene() throws IOException, ClassNotFoundException {
       
        Story story = StoryParser.parse("haunted_house");
        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new HealthGoal(4));
        Game game = new Game(new Player("Alice"), story, goals);

        Button save1Button = ApplicationObjects.newButton("", 514-193, 278-71, "launch_button");
        Button save2Button = ApplicationObjects.newButton("", 514-193, 345-71, "launch_button");
        Button save3Button = ApplicationObjects.newButton("", 514-193, 412-71, "launch_button");
        
        IntStream.rangeClosed(1, 3).forEach(i -> {
            try {
                if (SaveRegister.saveExists(i)) {
                    Story.setCurrentSave(SaveRegister.getSave(i));
                    Button saveButton = i == 1 ? save1Button : i == 2 ? save2Button : save3Button;
                    saveButton.setText(SaveRegister.getSave(i).getSaveName());
                    saveButton.setOnAction(e -> {
                        try {
                            LvlScene.scene(game, SaveRegister.getSave(i).getPassage());
                        } catch (IOException | ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    });
                } else {
                    Button saveButton = i == 1 ? save1Button : i == 2 ? save2Button : save3Button;
                    saveButton.setText("Empty");
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "back_button");
        backButton.setOnAction(e -> {
            try {
                ApplicationFront.switchToScene(OpeningScene.scene());
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
