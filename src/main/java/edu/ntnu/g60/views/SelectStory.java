package edu.ntnu.g60.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;

import edu.ntnu.g60.components.BackgroundComponent;
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
public class SelectStory extends StackPane{

    private static final int PADDING = 20;
    private static final double BUTTON_WIDTH = 200;

    public SelectStory() {

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
                GameManager.getInstance().createGame();

            } catch (BrokenLinkException error) {
                System.out.println(error.getMessage());
            }
            Passage openingPassage = GameManager.getInstance().getGame().begin();
            switchToPlayGame(openingPassage);
        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        layout.add(new Label("Welcome"), 0, 0);
        layout.add(storySelection, 0, 1);
        layout.add(playButton, 0, 2);
        Background background = BackgroundComponent.createBackground("selectStoryScene.png");
        layout.setBackground(background);
        this.getChildren().add(layout);
 
    }

    private void switchToPlayGame(Passage openingPassage) {
        PlayGame playGame = new PlayGame(openingPassage);
        App.changeRootPane(playGame.getLayout());
    }

    public StackPane getLayout() {
        return this;
    }

    
}