package edu.ntnu.g60.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.Save;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.views.DialogBoxes;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.Animations.NextLevelAnimation;
import edu.ntnu.g60.views.StartMenu.ContinuePane;
import edu.ntnu.g60.views.StartMenu.CustomGamePane;
import edu.ntnu.g60.views.StartMenu.NewGamePane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import edu.ntnu.g60.views.settings.InformationPane;
import edu.ntnu.g60.views.settings.ProjectPane;
import edu.ntnu.g60.views.settings.SettingsPane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;


public class StartMenuController {

    public static CustomGamePane currentCustomGamePane;
    public static ContinuePane currentContinuePane;

    public static ContinuePane getCurreContinuePane(){
        return currentContinuePane;
    }

    public static void setCurrentContinuePane(ContinuePane pane){
        currentContinuePane = pane;
    }

    public static CustomGamePane getCurrentCustomGamePane(){
        return currentCustomGamePane;
    }

    public static void setCurrentCustomGamePane(CustomGamePane pane){
        currentCustomGamePane = pane;
    }

    public void continueAction(ActionEvent event){
        try {
            ContinuePane pane = new ContinuePane();
            setCurrentContinuePane(pane);
            GameApp.changeRootPane(pane);
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public void linkAction(MouseEvent event){
        try {
            GameApp.changeRootPane(new ProjectPane());
        } catch (IOException e1) {
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
            CustomGamePane customGamePane = new CustomGamePane();
            setCurrentCustomGamePane(customGamePane);
            GameApp.changeRootPane(customGamePane);
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
        String filePath = file.getAbsolutePath();
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);

        if (file != null && filePath.toLowerCase().endsWith(".txt")) {
            File destDir = new File("src/main/resources/stories");
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            Path destPath = Paths.get(destDir.getAbsolutePath(), file.getName());
            try {
                Files.copy(file.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
                CustomGamePane.addImportSucessfullText(getCurrentCustomGamePane());
                ControllerValues.setGameFile(fileName.replaceAll(".txt", ""));
            } catch (IOException e) {
                DialogBoxes.alertBox("Import failed", "Your import was not saved", "");
            }
        } else {
            DialogBoxes.alertBox("Import failed", "Your import was not saved", "Please check that your import was of the right type");
        }
    }

    public void startCustomAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new NewGamePane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void backAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void deleteAction(ActionEvent event){
        String filePath = "src/main/resources/saves/saves.ser";
        File file = new File(filePath);
        try {
            if(file.exists() && SaveRegister.getSave(1) != null) {
                if(DialogBoxes.alertBoxChoices("CAUTION", "This will delete all progress", "Are you sure you want to continue?")){
                    file.delete();
                    ContinuePane.addDeleteObjects(getCurreContinuePane());
                    try {
                        SaveRegister.setDefaultSaves();
                    } catch (IOException e) {
                    e.printStackTrace();
                    }
                }
            } else {
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startAction(ActionEvent event){
        NewGamePane.updateSaveName();
        if(NewGamePane.saveName != null && !NewGamePane.saveName.equals("")){
            try {
                ControllerValues.setGameFile(NewGamePane.getStoryChoice());
                Game game = GameController.getNewGame();
                int saveNumber = 1;
                if (SaveRegister.saveExists(1)) {
                    saveNumber = 2;
                    if (SaveRegister.saveExists(2)) {
                        saveNumber = 3;
                    }
                }
                
                Save save = new Save(game.getStory().getOpeningPassage(), NewGamePane.saveName, saveNumber, ControllerValues.getGameFile());
                SaveRegister.setSave(save, saveNumber);
                Story.setCurrentSave(save);
                
                try {
                    GameController.setCurrentGame(game);
                    GameController.setCurrentPassage(game.begin());
                    NextLevelAnimation.animation();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        } 
    }
}
