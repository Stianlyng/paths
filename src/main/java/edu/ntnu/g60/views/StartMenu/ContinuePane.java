package edu.ntnu.g60.views.StartMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import edu.ntnu.g60.controllers.ControllerValues;
import edu.ntnu.g60.controllers.GameController;
import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.views.ViewValues;
import edu.ntnu.g60.views.ViewObjects;
import edu.ntnu.g60.views.Animations.NextLevelAnimation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    public static Group getContinueSceneObjects() throws FileNotFoundException{
        Button save1Button = ViewObjects.newBlankButton("", 614-193, 278-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID);
        Button save2Button = ViewObjects.newBlankButton("", 614-193, 345-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID);
        Button save3Button = ViewObjects.newBlankButton("", 614-193, 412-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID);
        Button deleteSaves = ViewObjects.newButton("Delete all saves", 120, 595-71, "delete_button", "delete_hover", controller::deleteAction);
        

        Set<String> playerSaves = GameManager.getInstance().getPlayerSaves(GameManager.getInstance().getPlayer().getName());
        String[] saveNames = new String[3];
        int index = 0;
        for (String save : playerSaves) {
            String[] split = save.split("_");
            saveNames[index] = split[2].replace(".ser", "");
            index++;
        }


        //TODO: move to controller
        IntStream.rangeClosed(1, 3).forEach(buttonNumber -> {
            if (playerSaves.size() == buttonNumber) {
                Button saveButton = buttonNumber == 1 ? save1Button : buttonNumber == 2 ? save2Button : save3Button;
                saveButton.setText(saveNames[buttonNumber]);
                
                saveButton.setOnAction(e -> {
                    try {
                        ControllerValues.setGameFile(saveNames[buttonNumber]);
                        GameManager.getInstance().loadGameFromFile(saveNames[buttonNumber]);
                        GameManager.getInstance().setGame(GameManager.getInstance().getGame());
                        NextLevelAnimation.animation();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
            } else {
                Button saveButton = buttonNumber == 1 ? save1Button : buttonNumber == 2 ? save2Button : save3Button;
                saveButton.setText("Empty");
            }
        });

        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::backAction);
        ImageView background = ViewObjects.newImage(ViewValues.MENU_BACKGROUND_FOLDERNAME, ViewValues.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,ViewValues.BACKGROUND_WIDTH ,ViewValues.BACKGROUND_HEIGHT);
        return new Group(background, save1Button, save2Button, save3Button, backButton, deleteSaves);
    }

    public static void addDeleteObjects(ContinuePane pane){
        Button save1Button = ViewObjects.newBlankButton("Empty", 614-193, 278-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID);
        Button save2Button = ViewObjects.newBlankButton("Empty", 614-193, 345-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID);
        Button save3Button = ViewObjects.newBlankButton("Empty", 614-193, 412-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID);
        
        StackPane.setAlignment(save1Button, Pos.CENTER);
        StackPane.setMargin(save1Button, new Insets(0, 6, 139, 0)); 
        StackPane.setAlignment(save2Button, Pos.CENTER);
        StackPane.setMargin(save2Button, new Insets(0, 6, 5, 0));
        StackPane.setAlignment(save3Button, Pos.CENTER);
        StackPane.setMargin(save3Button, new Insets(128, 6, 0, 0));
        pane.getChildren().addAll(save1Button, save2Button, save3Button);
    }


}
