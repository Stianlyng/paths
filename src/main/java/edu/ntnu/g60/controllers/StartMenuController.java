package edu.ntnu.g60.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import edu.ntnu.g60.exceptions.BrokenLinkException;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.goals.*;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.player.*;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.SaveFileHandler;
import edu.ntnu.g60.utils.SerializedGameState;
import edu.ntnu.g60.utils.parsers.*;
import edu.ntnu.g60.views.DialogBoxes;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.Animations.NextLevelAnimation;
import edu.ntnu.g60.views.StartMenu.*;
import edu.ntnu.g60.views.settings.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class StartMenuController {

    public static ContinueGamePane currentContinuePane;
    private static String playerName;


    public static ContinueGamePane getCurreContinuePane(){
        return currentContinuePane;
    }

    public static void setCurrentContinuePane(ContinueGamePane pane){
        currentContinuePane = pane;
    }


    public static void populateSaveButtons(Button save1Button, Button save2Button, Button save3Button) throws IOException {
        Set<String> playerSavesSet = SaveFileHandler.getPlayerSaves(playerName);
        List<String> playerSaves = new ArrayList<>(playerSavesSet);
        for(int i = 1; i < 4; i++) {
            if (i - 1 < playerSaves.size()) {
                final int index = i - 1;
                Button saveButton = i == 1 ? save1Button : i == 2 ? save2Button : save3Button;
                saveButton.setText(playerSaves.get(i - 1));
                
                saveButton.setOnAction(e -> {
                     GameManager.getInstance().endGame();
                     SerializedGameState save = SaveFileHandler.loadGameFromFile(playerSaves.get(index));
                     GameManager.getInstance().setPlayer(save.getGame().getPlayer());
                     GameManager.getInstance().setStory(save.getGame().getStory());
                     GameManager.getInstance().setGoals(save.getGame().getGoals());
                     GameManager.getInstance().createGame();
 
                     Passage passage = GameManager.getInstance().getGame().go(save.getCurrentLink()); 
                     try {
                         NextLevelAnimation.animation(passage);
                     } catch (MalformedURLException e1) {
                         e1.printStackTrace();
                     }
                });
            } else {
                Button saveButton = i == 1 ? save1Button : i == 2 ? save2Button : save3Button;
                saveButton.setText("Empty");
            }
        }
     }

    public void importNewFileAction(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PATHS files (*.paths)", "*.paths");
        fileChooser.getExtensionFilters().addAll(extFilter);
        File selectedFile = fileChooser.showOpenDialog(GameApp.getStage());
        
        if (selectedFile != null) {
            boolean parsedStory = TextfileParser.parseStory(selectedFile);
            if (parsedStory) {
                DialogBoxes.alertBox("Import Sucessfull!", "Your import was saved", "");
            } else {
                DialogBoxes.alertBox("Import failed", "Your import was not saved", "");
            }
        } else {
            DialogBoxes.alertBox("Import failed", "Please choose a file", "");
        }
    }

    public void ChoosePlayerAction(ActionEvent event){
        SelectPlayerPane.updatePlayerName();
        playerName = SelectPlayerPane.getPlayerChoice();
        if(playerName != null && playerName != ""){
            try {
                GameApp.changeRootPane(new OpeningPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            initializePlayer(playerName);
        }
    }

    public void deleteAllPlayerSavesAction(ActionEvent event){
        Set<String> playerSaves = SaveFileHandler.getPlayerSaves(playerName);
        if(!playerSaves.isEmpty()) {
            if(DialogBoxes.alertBoxWithChoices("CAUTION", "This will delete all progress", "Are you sure you want to continue?")){
                ContinueGamePane.addDeleteObjects(getCurreContinuePane());
                try {
                    SaveFileHandler.deletePlayerSaves(playerName);
                } catch (IOException e) {
                e.printStackTrace();
                }
            }
        }
    }

    public void createPlayerAction(ActionEvent event){
        SelectPlayerPane.updatePlayerName();
        if(SelectPlayerPane.playerName != null && !SelectPlayerPane.playerName.equals("")){
            playerName = SelectPlayerPane.playerName;
            try {
                GameApp.changeRootPane(new OpeningPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        initializePlayer(playerName);
    }

    public void startGameAction(ActionEvent event){
        try {
            Passage openingPassage = createNewGame(NewGamePane.getStoryChoice());
            NextLevelAnimation.animation(openingPassage);
        } catch (MalformedURLException | BrokenLinkException e) {
            e.printStackTrace();
        }
    }
    
    public void goTocontinueGamePaneAction(ActionEvent event){
        try {
            ContinueGamePane pane = new ContinueGamePane();
            setCurrentContinuePane(pane);
            GameApp.changeRootPane(pane);
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void goToInformationFileStructurePaneAction(MouseEvent event){
        try {
            GameApp.changeRootPane(new InformationFileStructurePane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void goToCustomGamePaneAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new CustomGamePane());
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void goToInformationPaneAction(MouseEvent event){
        try {
            GameApp.changeRootPane(new InformationPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void goToSettingsPaneAction(MouseEvent event){
        try {
            GameApp.changeRootPane(new SettingsPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void goToNewGamePaneAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new NewGamePane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void goToOpeningPaneAction(ActionEvent evnet){
        try {
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void goToSelectPlayerPaneAction(ActionEvent evnet){
        try {
            GameApp.changeRootPane(new SelectPlayerPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static Passage createNewGame(String storySelection) throws BrokenLinkException{
        StoryParser parser = new StoryParser(storySelection);
        try {
            
            Story story = parser.getStory();
            List<Goal> goals = parser.getGoals();

            GameManager.getInstance().setStory(story);
            GameManager.getInstance().setGoals(goals);
            GameManager.getInstance().createGame();

        } catch (BrokenLinkException error) {
            System.out.println(error.getMessage());
        }
        Passage passage = GameManager.getInstance().getGame().begin();
        return passage;
    }

    private void initializePlayer(String playerName) {
        List<String> inventory = List.of("Sword");

        Player player = new PlayerBuilder()
            .setName(playerName)
            .setHealth(100)
            .setGold(0)
            .setScore(0)
            .setInventory(inventory)
            .build();

        GameManager.getInstance().setPlayer(player);
    }

    public String[] getStories(){
        List<String> availableStories = SaveFileHandler.listFilesInFolder();
        return availableStories.toArray(new String[availableStories.size()]);
    }

    public String[] getPlayerNames(){
        List<String> availablePlayers = new ArrayList<>(SaveFileHandler.getAvailablePlayers());
        return availablePlayers.toArray(new String[availablePlayers.size()]);
    }
}
