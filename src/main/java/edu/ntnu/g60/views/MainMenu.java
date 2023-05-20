package edu.ntnu.g60.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

import edu.ntnu.g60.components.BackgroundComponent;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.utils.parsers.TextfileParser;

/**
 * Class representing the main menu.
 */
public class MainMenu extends StackPane{

    private static final int PADDING = 20;
    private static final double BUTTON_WIDTH = 200;

    private GridPane layout;  

    public MainMenu(String playerName) {

        Background background = BackgroundComponent.createBackground("mainMenuScene.png");
        initializePlayer(playerName);
        Button newGameButton = newGame(playerName);
        Button loadGameButton = loadGame(playerName);
        Button importGameFileButton = importGameFile();

        layout = new GridPane();  
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        layout.add(new Label("Welcome " + playerName), 0, 0);
        layout.add(newGameButton, 0, 1);
        layout.add(loadGameButton, 0, 2);
        layout.add(importGameFileButton, 0, 3);
        layout.setBackground(background);
        this.getChildren().add(layout);
 
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

    private Button newGame(String playerName){
        Button newGameButton = new Button("New Game");
        newGameButton.setPrefWidth(BUTTON_WIDTH);
        newGameButton.setOnAction(e -> {
            switchToSelectStory(playerName);
        });
        return newGameButton;
    }
    
    private Button loadGame(String playerName){
        Button loadGameButton = new Button("Load Game");
        loadGameButton.setPrefWidth(BUTTON_WIDTH);
        loadGameButton.setOnAction(e -> {
            switchToLoadGame(playerName);
        });
        return loadGameButton;
    }
    
    private Button importGameFile(){
        Button importGameFileButton = new Button("Import Gamefile");
        importGameFileButton.setPrefWidth(BUTTON_WIDTH);
        importGameFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(App.getStage());
        
            if (selectedFile != null) {
                boolean parsedStory = TextfileParser.parseStory(selectedFile);
                if (parsedStory) {
                    // suksessmelding her
                } else {
                    // feilmelding her
                }
            } else {
                //  om brukeren ikke velger en fil
            }
        });
        return importGameFileButton;
    }

    public StackPane getLayout() {
        return this;
    }

    private void switchToSelectStory(String playerName) {
        SelectStory selectStory = new SelectStory();
        App.changeRootPane(selectStory.getLayout());
    }

    private void switchToLoadGame(String playerName) {
        LoadGame loadGame = new LoadGame(playerName);
        App.changeRootPane(loadGame.getLayout());
    }
        
}

