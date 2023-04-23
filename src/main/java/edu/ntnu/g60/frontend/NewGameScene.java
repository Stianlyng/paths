package edu.ntnu.g60.frontend;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import edu.ntnu.g60.*;
import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Player;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.utils.Save;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.utils.fileParser.FileParser;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;



public class NewGameScene {

    public static Scene scene() throws ClassNotFoundException, IOException{
        Button startButton = ApplicationObjects.newButton("Start", 514-193, 370-71, "launch_button");
        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "back_button");
        backButton.setOnAction(e -> {
            try {
                ApplicationFront.switchToScene(OpeningScene.scene());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        TextField saveNameTextField = ApplicationObjects.newTextField("Savename..", 514-193, 327-71, "text_field");
        startButton.setOnAction(e -> {
            if(saveNameTextField.getText() != null && !saveNameTextField.getText().equals("")){
                try {
                    FileParser fileParser = new FileParser("src/main/resources/textFiles/haunted_house.txt");
                    Story story = fileParser.buildStory();

                    List<Goal> goals = new ArrayList<Goal>();
                    goals.add(new HealthGoal(4));
                    Game game = new Game(new Player("Alice"), story, goals);
                    
                    int saveNumber = 1;
                    if (SaveRegister.saveExists(1)) {
                        saveNumber = 2;
                        if (SaveRegister.saveExists(2)) {
                            saveNumber = 3;
                        }
                    }
                    
                    Save save = new Save(game.getStory().getOpeningPassage(), saveNameTextField.getText(), saveNumber);
                    SaveRegister.setSave(save, saveNumber);
                    Story.setCurrentSave(save);
                    
                    try {
                        LvlScene.scene(game, game.begin());
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }       
        }); 

        ImageView background = ApplicationObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        Group root = new Group(background, backButton, startButton, saveNameTextField);
        root.getStylesheets().add("StyleSheet.css"); 
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

}
