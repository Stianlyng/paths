package edu.ntnu.g60.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

import edu.ntnu.g60.exceptions.BrokenLinkException;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.SaveFileHandler;
import edu.ntnu.g60.utils.parsers.StoryParser;

/**
 * Class representing the story selection scene.
 */
public class SelectStory {

    private static final int PADDING = 20;
    private static final double BUTTON_WIDTH = 200;

    private Scene scene;

    public SelectStory(Stage primaryStage, int WIDTH, int HEIGHT) {

        ComboBox<String> storySelection = new ComboBox<>();
        storySelection.setPromptText("Select a story");
        List<String> stories = SaveFileHandler.listFilesInFolder();
        storySelection.getItems().addAll(stories);

        Button playButton = new Button("Play Story");
        playButton.setPrefWidth(BUTTON_WIDTH);
        playButton.setOnAction(e -> {

            StoryParser parser = new StoryParser(storySelection.getValue());
            try {
                Story story = parser.build();

                List<Goal> goals = List.of(
                    new HealthGoal(110),
                    new GoldGoal(0),
                    new InventoryGoal(List.of("Sword")),
                    new ScoreGoal(100)
                );
                GameManager.getInstance().setStory(story);
                GameManager.getInstance().setGoals(goals);
                GameManager.getInstance().setGameName("kuk"); //todo; fjern??
                GameManager.getInstance().createGame();

            } catch (BrokenLinkException error) {
                System.out.println(error.getMessage());
            }
            Passage openingPassage = GameManager.getInstance().getGame().begin();
            PlayGame playGame = new PlayGame(primaryStage, openingPassage, WIDTH, HEIGHT);
            primaryStage.setScene(playGame.getScene());
        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        layout.add(new Label("Welcome"), 0, 0);
        layout.add(storySelection, 0, 1);
        layout.add(playButton, 0, 2);

        scene = new Scene(layout, WIDTH, HEIGHT);
    }

    /**
     * Returns the scene for this view.
     */
    public Scene getScene() {
        return scene;
    }
    
}