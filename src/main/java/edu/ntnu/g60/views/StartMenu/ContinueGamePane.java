package edu.ntnu.g60.views.StartMenu;

import java.io.IOException;
import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewValues;
import edu.ntnu.g60.views.ViewObjects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * ContinueGamePane represents the pane in with a user can choose to continue a game
 * @author olav sie
 */
public class ContinueGamePane extends StackPane{

    private static StartMenuController controller;

    /**
     * Constructs a new ContinueGamePane object.
     * @throws FileNotFoundException if the file specified is not found.
    */
    public ContinueGamePane() throws IOException, ClassNotFoundException {
        ContinueGamePane.controller = new StartMenuController();
        getChildren().addAll(getContinueSceneObjects());
    }

    /**
     * Adds the objects required for the continue game pane.
     *
     * @return a Group containing the contiue game pane objects
     * @throws IOException if the file specified is not found
     */
    public static Group getContinueSceneObjects() throws IOException{
        Button save1Button = ViewObjects.newBlankButton("", 614-193, 278-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID);
        Button save2Button = ViewObjects.newBlankButton("", 614-193, 345-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID);
        Button save3Button = ViewObjects.newBlankButton("", 614-193, 412-71, ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID);
        Button deleteSaves = ViewObjects.newButton("Delete all saves", 120, 595-71, "delete_button", "delete_hover", controller::deleteAllPlayerSavesAction);
        StartMenuController.populateSaveButtons(save1Button, save2Button, save3Button);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::goToOpeningPaneAction);
        ImageView background = ViewObjects.newImage(ViewValues.MENU_BACKGROUND_FOLDERNAME, ViewValues.MENU_BACKGROUND_IMAGENAME, 0 ,0 ,ViewValues.BACKGROUND_WIDTH ,ViewValues.BACKGROUND_HEIGHT);
        return new Group(background, save1Button, save2Button, save3Button, backButton, deleteSaves);
    }

    /**
     * Adds buttons with empty text on top of buttons in pane. This is so the player cannot access a
     * deleted game file.
     * @param pane The current ContinueGamePane that is showed in the game.
     */
    public static void addDeleteObjects(ContinueGamePane pane){
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
