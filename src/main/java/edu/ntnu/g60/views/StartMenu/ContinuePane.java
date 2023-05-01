package edu.ntnu.g60.views.StartMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.IntStream;

import edu.ntnu.g60.controllers.GameController;
import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.views.Values;
import edu.ntnu.g60.views.ViewObjects;
import edu.ntnu.g60.views.Animations.LvlSwitchAnimation;
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
        Button save1Button = ViewObjects.newBlankButton("", 614-193, 278-71, Values.MENU_BUTTON_ID, Values.MENU_BUTTON_HOVER_ID);
        Button save2Button = ViewObjects.newBlankButton("", 614-193, 345-71, Values.MENU_BUTTON_ID, Values.MENU_BUTTON_HOVER_ID);
        Button save3Button = ViewObjects.newBlankButton("", 614-193, 412-71, Values.MENU_BUTTON_ID, Values.MENU_BUTTON_HOVER_ID);
        
        //TODO: move to controller
        IntStream.rangeClosed(1, 3).forEach(buttonNumber -> {
            try {
                if (SaveRegister.saveExists(buttonNumber)) {
                    Story.setCurrentSave(SaveRegister.getSave(buttonNumber));
                    Button saveButton = buttonNumber == 1 ? save1Button : buttonNumber == 2 ? save2Button : save3Button;
                    saveButton.setText(SaveRegister.getSave(buttonNumber).getSaveName());
                    
                    saveButton.setOnAction(e -> {
                        try {
                            GameController.setCurrentGame(GameController.getNewGame());
                            GameController.setCurrentPassage(SaveRegister.getSave(buttonNumber).getPassage());
                            LvlSwitchAnimation.animation();
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

        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, Values.BACK_BUTTON_ID, Values.BACK_BUTTON_HOVER_ID, controller::backAction);
        ImageView background = ViewObjects.newImage(Values.MENU_BACKGROUND_FOLDERNAME, Values.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,Values.BACKGROUND_WIDTH ,Values.BACKGROUND_HEIGHT);
        return new Group(background, save1Button, save2Button, save3Button, backButton);
    }



}
