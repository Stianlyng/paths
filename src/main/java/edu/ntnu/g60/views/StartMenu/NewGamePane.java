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
    
    public NewGamePane() throws FileNotFoundException{
        NewGamePane.controller = new StartMenuController();
        getChildren().addAll(getNewGameObjects());
    }

    private static Group getNewGameObjects() throws FileNotFoundException{
        Button startButton = ViewObjects.newButton("Start", 514-193, 370-71, "launch_button", controller::startAction);
        Button backButton = ViewObjects.newButton("Back", 903-193, 595-71, "back_button", controller::backAction);
        ImageView background = ViewObjects.newImage("backgrounds", "Background2.jpg", 0 ,0 ,1643 ,1006);
        TextField saveNameTextField = ViewObjects.newTextField("Savename..", 514-193, 327-71, "text_field");
        saveName = saveNameTextField.getText();
        return new Group(startButton, backButton, saveNameTextField, background);
    }
}
