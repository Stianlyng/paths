package edu.ntnu.g60.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import edu.ntnu.g60.exceptions.BrokenLinkException;
import edu.ntnu.g60.exceptions.InvalidLinkException;
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


/**
 * The StartMenuController class is responsible for controlling the start menu of the game.
 * It handles actions and interactions related to starting a new game, continuing a saved game,
 * importing game data, selecting players, and navigating to different screens within the game.
 * @author olav sie, stian lyng
*/
public class StartMenuController {

    public static ContinueGamePane currentContinuePane;
    private static String playerName;


    /**
     * Retrieves the current ContinueGamePane object.
     * 
     * @return The current ContinueGamePane object.
    */
    public static ContinueGamePane getCurreContinuePane(){
        return currentContinuePane;
    }

    /**
     * Sets the current ContinueGamePane object.
     * 
     * @param pane The ContinueGamePane object to set as current.
    */
    public static void setCurrentContinuePane(ContinueGamePane pane){
        currentContinuePane = pane;
    }

    /**
     * Populates the save game buttons with saved game data for the current player.
     * 
     * @param save1Button The button for save slot 1.
     * @param save2Button The button for save slot 2.
     * @param save3Button The button for save slot 3.
     * @throws IOException If an I/O error occurs while retrieving save data.
    */
    public static void populateSaveButtons(Button save1Button, Button save2Button, Button save3Button) throws IOException {
        Set<String> playerSavesSet;
        try {
            playerSavesSet = SaveFileHandler.getPlayerSaves(playerName);
            List<String> playerSaves = new ArrayList<>(playerSavesSet);
            for(int i = 1; i < 4; i++) {
                if (i - 1 < playerSaves.size()) {
                    final int index = i - 1;
                    Button saveButton = i == 1 ? save1Button : i == 2 ? save2Button : save3Button;
                    saveButton.setText(playerSaves.get(i - 1));
                    
                    saveButton.setOnAction(e -> {

                    Optional<SerializedGameState> saveOpt = SaveFileHandler.loadGameFromFile(playerSaves.get(index));
                    if (saveOpt.isPresent()) {
                        SerializedGameState save = saveOpt.get();
                        GameManager.getInstance().setPlayer(save.getGame().getPlayer());
                        GameManager.getInstance().setStory(save.getGame().getStory());
                        GameManager.getInstance().setGoals(save.getGame().getGoals());
                        GameManager.getInstance().createGame();
                        
                        try {
                            Passage passage = GameManager.getInstance().getGame().go(save.getCurrentLink()); 
                             NextLevelAnimation.animation(passage);
                         } catch (MalformedURLException | FileNotFoundException | InvalidLinkException e1) {
                             e1.printStackTrace();
                         }
                    } else {
                        DialogBoxes.alertBox("Load failed", "Your load was not saved", "");
                    }
                    });
                } else {
                    Button saveButton = i == 1 ? save1Button : i == 2 ? save2Button : save3Button;
                    saveButton.setText("Empty");
                }
            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
     }

    /**
     * Handles the action for import a New File
     *
     * @param event the ActionEvent representing the button click event
     */
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
            DialogBoxes.alertBox("Import failed", "Please choose a diffrent file", "");
        }
    }

    /**
     * Handles the action when the "Choose Player" button is clicked.
     * saves the chosen plaeyr to playerName
     *
     * @param event the ActionEvent representing the button click event
     */
    public void ChoosePlayerAction(ActionEvent event){
        SelectPlayerPane.updatePlayerName();
        playerName = SelectPlayerPane.getPlayerChoice();
        if(playerName != null && playerName != ""){
            try {
                GameApp.changeRootPane(new MainMenuPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            initializePlayer(playerName);
        }
    }

    /**
     * Handles the action to delete all saves of a player
     *
     * @param event the ActionEvent representing the button click event
     */
    public void deleteAllPlayerSavesAction(ActionEvent event){
        Set<String> playerSaves;
        try {
            playerSaves = SaveFileHandler.getPlayerSaves(playerName);
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
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    /**
     * Handles the action when the "Create Player" button is clicked.
     * Saves the selected player to playerName.
     *
     * @param event the ActionEvent representing the button click event
     */
    public void createPlayerAction(ActionEvent event){
        SelectPlayerPane.updatePlayerName();
        if(SelectPlayerPane.playerName != null && !SelectPlayerPane.playerName.equals("")){
            playerName = SelectPlayerPane.playerName;
            try {
                GameApp.changeRootPane(new MainMenuPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        initializePlayer(playerName);
    }

    /**
     * Handles the action when the "Start Game" button is clicked in the NewGamePane.
     *
     * @param event the ActionEvent representing the button click event
     */
    public void startGameAction(ActionEvent event){
        try {
            Passage openingPassage = createNewGame(NewGamePane.getStoryChoice());
            NextLevelAnimation.animation(openingPassage);
        } catch (MalformedURLException | BrokenLinkException | FileNotFoundException e) {
            DialogBoxes.alertBox("ERROR", "", e.getMessage());
        }
    }
    
    /**
     * Switches the current showing pane to the ContinueGamePane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goTocontinueGamePaneAction(ActionEvent event){
        try {
            ContinueGamePane pane = new ContinueGamePane();
            setCurrentContinuePane(pane);
            GameApp.changeRootPane(pane);
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Switches the current showing pane to the InformationFileStructurePane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToInformationFileStructurePaneAction(MouseEvent event){
        try {
            GameApp.changeRootPane(new FileStructureInformationPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Switches the current showing pane to the CustomGamePane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToCustomGamePaneAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new CustomGamePane());
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Switches the current showing pane to the InformationPane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToInformationPaneAction(MouseEvent event){
        try {
            GameApp.changeRootPane(new GameInformationPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Switches the current showing pane to the SettingsPane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToTextEditorPaneAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new TextEditorPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Switches the current showing pane to the SettingsPane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToSettingsPaneAction(MouseEvent event){
        try {
            GameApp.changeRootPane(new SettingsPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Switches the current showing pane to the SettingsPane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToSettingsPaneAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new SettingsPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Switches the current showing pane to the NewGamePane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToNewGamePaneAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new NewGamePane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Switches the current showing pane to the OpeningPane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToOpeningPaneAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new MainMenuPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Switches the current showing pane to the SelectPlayerPane
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToSelectPlayerPaneAction(ActionEvent evnet){
        try {
            GameApp.changeRootPane(new SelectPlayerPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Creates a new game based on the selected story.
     *
     * @param storySelection the selected story for the game
     * @return the initial Passage of the created game
     * @throws BrokenLinkException if there is a broken link in the story
     */
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

    public File openedFile;

    /**
     * Opens a story file in the text editor.
     * 
     * @param event the ActionEvent representing the button click event
     */
    public void openFileInEditorAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        URL resourceUrl = getClass().getResource("/stories");
        if (resourceUrl != null) {
            String resourcePath = resourceUrl.getPath();
            File initialDirectory = new File(resourcePath);
            fileChooser.setInitialDirectory(initialDirectory);
        }
        File file = fileChooser.showOpenDialog(GameApp.getStage());
        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                StringBuilder content = new StringBuilder();
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
                scanner.close();
                TextEditorPane.getTextArea().setText(content.toString());
                openedFile = file;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the file opend in the text editor.
     * 
     * @param event the ActionEvent representing the button click event
     */
    public void saveFileInEditorAction(ActionEvent event) {
        if (openedFile != null) {
            try {
                FileWriter writer = new FileWriter(openedFile);
                writer.write(TextEditorPane.getTextArea().getText());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Initializes the player with the specified player name.
     *
     * @param playerName the name of the player
     */
    private void initializePlayer(String playerName) {
        List<String> inventory = List.of();

        Player player = new PlayerBuilder()
            .setName(playerName)
            .setHealth(100)
            .setGold(0)
            .setScore(0)
            .setInventory(inventory)
            .build();

        GameManager.getInstance().setPlayer(player);
    }

    /**
     * Retrieves the existing stories.
     *
     * @return an array of available story names
     */
    public String[] getStories(){
        List<String> availableStories;
        try {
            availableStories = SaveFileHandler.listFilesInFolder();
            return availableStories.toArray(new String[availableStories.size()]);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the existing player names.
     *
     * @return an array of available player names
     */
    public String[] getPlayerNames(){
        List<String> availablePlayers;
        try {
            availablePlayers = new ArrayList<>(SaveFileHandler.getAvailablePlayers());
            return availablePlayers.toArray(new String[availablePlayers.size()]);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
