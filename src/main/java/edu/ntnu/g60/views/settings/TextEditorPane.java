package edu.ntnu.g60.views.settings;

import java.io.FileNotFoundException;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import edu.ntnu.g60.views.ViewValues;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * TextEditorPane represents a pane for editing text in the game
 * @author olav sie
 */
public class TextEditorPane extends StackPane{
    static TextArea textArea;
    private static StartMenuController controller;

    /**
     * Constructs a new TextEditorPane object.
     * @throws FileNotFoundException if the file specified is not found.
    */
    public TextEditorPane() throws FileNotFoundException{
        TextEditorPane.controller = new StartMenuController();
        getChildren().addAll(getTextEditorObjects());
    }

    /**
     * Adds the objects required for the text editor pane pane.
     *
     * @return a Group containing the text editor pane objects
     * @throws FileNotFoundException if the file specified is not found
     */
    private Group getTextEditorObjects() throws FileNotFoundException {
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, ViewValues.BACK_BUTTON_ID, ViewValues.BACK_BUTTON_HOVER_ID, controller::goToSettingsPaneAction);

        Button openButton = ViewObjects.newButton("Open", 600, 200,  ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::openFileInEditorAction);
        Button saveButton = ViewObjects.newButton("Save", 600, 275,  ViewValues.MENU_BUTTON_ID, ViewValues.MENU_BUTTON_HOVER_ID, controller::saveFileInEditorAction);

        textArea = new TextArea();
        textArea.setPrefSize(400, 400);
        textArea.setLayoutX(120);
        textArea.setLayoutY(100);

        return new Group(backButton, openButton, saveButton, textArea);
    }

    /**
     * returns the value of the text area in the text editor
     * 
     * @return value of the text area in the text editor
     */
    public static TextArea getTextArea(){
        return textArea;
    }
}
