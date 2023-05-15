package edu.ntnu.g60.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.utils.fileHandling.TextfileParser;
import edu.ntnu.g60.views.DialogBoxes;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.Animations.NextLevelAnimation;
import edu.ntnu.g60.views.StartMenu.ContinuePane;
import edu.ntnu.g60.views.StartMenu.CustomGamePane;
import edu.ntnu.g60.views.StartMenu.NewGamePane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import edu.ntnu.g60.views.StartMenu.SelectPlayerPane;
import edu.ntnu.g60.views.settings.InformationFileStructurePane;
import edu.ntnu.g60.views.settings.InformationPane;
import edu.ntnu.g60.views.settings.ProjectPane;
import edu.ntnu.g60.views.settings.SettingsPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.commons.io.FilenameUtils;


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

    public void informationFileStructureAction(MouseEvent event){
        try{
            GameApp.changeRootPane(new InformationFileStructurePane());
        } catch (IOException e1) {

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


    public void importFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import game File");
    
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().addAll(extFilter, jsonFilter);
    
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getAbsolutePath();
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);

        if (file != null) {
            if (filePath.toLowerCase().endsWith(".txt") || filePath.toLowerCase().endsWith(".json")) {
                File destDir = new File("src/main/resources/stories");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
                Path destPath = Paths.get(destDir.getAbsolutePath(), file.getName());
                try {
                    Files.copy(file.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
                    CustomGamePane.addImportSucessfullText(getCurrentCustomGamePane());
                    ControllerValues.setGameFile(FilenameUtils.removeExtension(fileName));
                } catch (IOException e) {
                    DialogBoxes.alertBox("Import failed", "Your import was not saved", "");
                }
                if (filePath.toLowerCase().endsWith(".txt")){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        TextfileParser.parseStory(fileName.replace(".txt", ""));
                    } catch (IOException e) {
                        System.out.println(fileName);
                    }
                    String filePathOld = "src/main/resources/stories/" + fileName;
                    File fileOld = new File(filePathOld);
                    fileOld.delete();
                }
            } else {
                DialogBoxes.alertBox("Import failed", "Your import was not saved", "Please check that your import was of the right type");
            }
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

    public void backCustomAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new CustomGamePane());
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public String[] getPlayerNames(){
        List<String> availablePlayers = new ArrayList<>(GameManager.getAvailablePlayers());
        String[] players = availablePlayers.toArray(new String[availablePlayers.size()]);
        return players;
    }

    public void backActionOpening(ActionEvent evnet){
        try {
            GameApp.changeRootPane(new SelectPlayerPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void playerChoiceAction(ActionEvent event){
        SelectPlayerPane.updatePlayerName();
        GameController.setPLayerName(SelectPlayerPane.getPlayerChoice());
        try {
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void deleteAction(ActionEvent event){
        Set<String> playerSaves = GameManager.getInstance().getPlayerSaves(GameController.getPlayerName());
        if(!playerSaves.isEmpty()) {
            if(DialogBoxes.alertBoxChoices("CAUTION", "This will delete all progress", "Are you sure you want to continue?")){
                ContinuePane.addDeleteObjects(getCurreContinuePane());
                try {
                    GameManager.deletePlayerSaves(GameController.getPlayerName());
                } catch (IOException e) {
                e.printStackTrace();
                }
            }
        } else {
        }
    }

    public static void populateSaveButtons(Button save1Button, Button save2Button, Button save3Button) throws IOException {

       Set<String> playerSaves = GameManager.getInstance().getPlayerSaves(GameController.getPlayerName());
       String[] saveNames = new String[3];
       String[] storyNames = new String[3];
       int index = 0;
       for (String save : playerSaves) {
           String[] split = save.split("_");
           saveNames[index] = split[2].replace(".ser", "");
           storyNames[index] = split[1];
           index++;
       }
       if(playerSaves.size() == 2){
           saveNames[2] = "";
       }
       if(playerSaves.size() == 1){
           saveNames[1] = "";
           saveNames[2] = "";
       }

       IntStream.rangeClosed(1, 3).forEach(buttonNumber -> {
           if (playerSaves.size() >= buttonNumber) {
               Button saveButton = buttonNumber == 1 ? save1Button : buttonNumber == 2 ? save2Button : save3Button;
               saveButton.setText(saveNames[buttonNumber - 1]);
               
               saveButton.setOnAction(e -> {
                   try {
                       GameManager.getInstance().endGame();
                       GameController.setSaveName(saveNames[buttonNumber - 1]);
                       GameController.setStoryName(storyNames[buttonNumber - 1].replace(" ", "_"));
                       GameController.createNewGame();
                       GameManager.getInstance().loadGameFromFile(GameController.getPlayerName() + "_" + storyNames[buttonNumber - 1] + "_" + saveNames[buttonNumber - 1] + ".ser");
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
    }

    public String[] getStories(){
        List<String> availableStories = GameController.listFilesInFolder();
        return availableStories.toArray(new String[availableStories.size()]);
    }

    public void createPlayerAction(ActionEvent event){
        SelectPlayerPane.updatePlayerName();
        if(SelectPlayerPane.playerName != null && !SelectPlayerPane.playerName.equals("")){
            GameController.setPLayerName(SelectPlayerPane.playerName);
            try {
                GameApp.changeRootPane(new OpeningPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void startAction(ActionEvent event){
        GameManager.getInstance().endGame();
        Set<String> playerSaves = GameManager.getInstance().getPlayerSaves(GameController.getPlayerName());
        NewGamePane.updateSaveName();
        if(NewGamePane.saveName != null && !NewGamePane.saveName.equals("")){
            boolean overwrite = true;
            if(playerSaves.size() == 3){
                String[] saveNames = new String[3];
                int index = 0;
                for (String save : playerSaves) {
                    String[] split = save.split("_");
                    saveNames[index] = split[2].replace(".ser", "");
                    index++;
                }

                overwrite = DialogBoxes.alertBoxChoices("CAUTION!", "This will action will overwrite save: " + saveNames[2], "Are you sure you want to continue?");
            }
            if(overwrite){
                //TODO: delete save 3 if save 3 exist
                GameController.setSaveName(NewGamePane.saveName);
                ControllerValues.setGameFile(NewGamePane.getStoryChoice());
                GameController.setStoryName(NewGamePane.getStoryChoice());

                try {
                    GameController.createNewGame();
                    NextLevelAnimation.animation();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }

        } 
    }
}
