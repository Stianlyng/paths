package edu.ntnu.g60.views.StartMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.IntStream;
import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.frontend.ApplicationObjects;
import edu.ntnu.g60.frontend.GameApp;
import edu.ntnu.g60.frontend.LvlSwitchAnimation;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;



public class ContinuePane extends StackPane{

    private static StartMenuController controller;

    public ContinuePane() throws IOException, ClassNotFoundException {
        ContinuePane.controller = new StartMenuController();
        getChildren().addAll(getContinueSceneObjects());
    }

    private static Group getContinueSceneObjects() throws FileNotFoundException{
        Button save1Button = ViewObjects.newBlankButton("", 514-193, 278-71, "launch_button");
        Button save2Button = ViewObjects.newBlankButton("", 514-193, 345-71, "launch_button");
        Button save3Button = ViewObjects.newBlankButton("", 514-193, 412-71, "launch_button");
        
        //TODO: move to controller
        IntStream.rangeClosed(1, 3).forEach(buttonNumber -> {
            try {
                if (SaveRegister.saveExists(buttonNumber)) {
                    Story.setCurrentSave(SaveRegister.getSave(buttonNumber));
                    Button saveButton = buttonNumber == 1 ? save1Button : buttonNumber == 2 ? save2Button : save3Button;
                    saveButton.setText(SaveRegister.getSave(buttonNumber).getSaveName());
                    
                    saveButton.setOnAction(e -> {
                        try {
                            LvlSwitchAnimation.animation(GameApp.getGame(), SaveRegister.getSave(buttonNumber).getPassage());
                        } catch (IOException | ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    });
                } else {
                    Button saveButton = buttonNumber == 1 ? save1Button : buttonNumber == 2 ? save2Button : save3Button;
                    saveButton.setText("Empty");
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "back_button", controller::backAction);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        return new Group(save1Button, save2Button, save3Button, backButton, background);
    }



}
