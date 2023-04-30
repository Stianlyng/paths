package edu.ntnu.g60.views.StartMenu;


import java.io.FileNotFoundException;
import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class NewGamePane extends StackPane{

    private static StartMenuController controller;
    public static String saveName;
    static TextField saveNameTextField;
    
    public NewGamePane() throws FileNotFoundException{
        NewGamePane.controller = new StartMenuController();
        getChildren().addAll(getNewGameObjects());
    }


    private static Group getNewGameObjects() throws FileNotFoundException{
        Button startButton = ViewObjects.newButton("Start", 614-193, 375-71, "launch_button", "launch_hover", controller::startAction);
        Button backButton = ViewObjects.newButton("Back", 953-193, 595-71, "back_button", "back_hover", controller::backAction);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        saveNameTextField = ViewObjects.newTextField("Savename..", 614-193, 327-71, "text_field");
        return new Group(background, startButton, backButton, saveNameTextField);
    }

    public static void updateSaveName(){
        saveName = saveNameTextField.getText();
    }
}
