package edu.ntnu.g60.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.utils.Save;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.Animations.LvlSwitchAnimation;
import edu.ntnu.g60.views.StartMenu.ContinuePane;
import edu.ntnu.g60.views.StartMenu.CustomGamePane;
import edu.ntnu.g60.views.StartMenu.NewGamePane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import edu.ntnu.g60.views.settings.InformationPane;
import edu.ntnu.g60.views.settings.SettingsPane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class StartMenuController {
    

    public void continueAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new ContinuePane());
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public void newGameAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new NewGamePane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void customAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new CustomGamePane());
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void informationAction(MouseEvent event){
        try {
            GameApp.changeRootPane(new InformationPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void settingsAction(MouseEvent event){
        try {
            GameApp.changeRootPane(new SettingsPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void importFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import TXT File");
    
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
    
        File file = fileChooser.showOpenDialog(null);
    
        if (file != null) {
            String filePath = file.getAbsolutePath();
            // Add the file to PATH
            // For example, you can use the following code:
            // System.setProperty("PATH", System.getProperty("PATH") + File.pathSeparator + filePath);
            //add melding om att det er i orden
        }
    }

    public void backAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void startAction(ActionEvent event){
        NewGamePane.updateSaveName();
        if(NewGamePane.saveName != null && !NewGamePane.saveName.equals("")){
            try {
                Game game = GameController.getNewGame();
                
                int saveNumber = 1;
                if (SaveRegister.saveExists(1)) {
                    saveNumber = 2;
                    if (SaveRegister.saveExists(2)) {
                        saveNumber = 3;
                    }
                }
                
                Save save = new Save(game.getStory().getOpeningPassage(), NewGamePane.saveName, saveNumber);
                SaveRegister.setSave(save, saveNumber);
                Story.setCurrentSave(save);
                
                try {
                    GameController.setCurrentGame(game);
                    GameController.setCurrentPassage(game.begin());
                    LvlSwitchAnimation.animation();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        } 
    }
}
