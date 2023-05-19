package edu.ntnu.g60.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Set;

import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.utils.SaveFileHandler;
import edu.ntnu.g60.utils.SerializedGameState;
/**
 * Class representing the story selection scene.
 */
public class LoadGame extends StackPane{

    private static final int PADDING = 20;
    private static final double BUTTON_WIDTH = 200;
    private int width;
    private int height;

    public LoadGame(String playerName, int width, int height) {
        this.width = width;
        this.height = height;

        ComboBox<String> storySelection = new ComboBox<>();
        storySelection.setPromptText("Select a save");
        Set<String> saves = SaveFileHandler.getPlayerSaves(playerName);
        storySelection.getItems().addAll(saves);

        Button playButton = new Button("Continue Story");
        playButton.setPrefWidth(BUTTON_WIDTH);
        playButton.setOnAction(e -> {

            SerializedGameState gameState = SaveFileHandler.loadGameFromFile(storySelection.getValue());

            GameManager.getInstance().setStory(gameState.getGame().getStory());
            GameManager.getInstance().setGoals(gameState.getGame().getGoals());
            GameManager.getInstance().createGame();


            Passage currentPassage = GameManager.getInstance().getGame().go(gameState.getCurrentLink()); 
            switchToPlayGame(currentPassage);
        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        layout.add(new Label("Welcome"), 0, 0);
        layout.add(storySelection, 0, 1);
        layout.add(playButton, 0, 2);
        this.getChildren().add(layout);

    }

    private void switchToPlayGame(Passage currentPassage) {
        PlayGame playGame = new PlayGame(currentPassage, width, height);
        App.changeRootPane(playGame.getLayout());
    }

    public StackPane getLayout() {
        return this;
    }
    
}