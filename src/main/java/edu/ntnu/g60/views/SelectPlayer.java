package edu.ntnu.g60.views;

import java.util.List;

import edu.ntnu.g60.controllers.SelectPlayerController;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.utils.SaveFileHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SelectPlayer {

    private static final int PADDING = 20;

    private Scene scene;
    private SelectPlayerController controller;

    public SelectPlayer(Stage primaryStage, int WIDTH, int HEIGHT) {
        controller = new SelectPlayerController();

        TextField newPlayerName = new TextField();
        newPlayerName.setPromptText("Enter a new player name");

        ComboBox<String> playerSelection = new ComboBox<>();
        playerSelection.setPromptText("Select a player");
        playerSelection.getItems().addAll(SaveFileHandler.getAvailablePlayers());

        playerSelection.setOnAction(e -> newPlayerName.setText(playerSelection.getValue()));

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            String enteredPlayer = newPlayerName.getText();

            List<String> inventory = List.of("Sword");

            Player player = new PlayerBuilder()
                .setName(newPlayerName.getText())
                .setHealth(100)
                .setGold(0)
                .setScore(0)
                .setInventory(inventory)
                .build();

            GameManager.getInstance().setPlayer(player);

            controller.handleContinueButton(primaryStage, enteredPlayer, WIDTH, HEIGHT);
        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        layout.add(playerSelection, 0, 0);
        layout.add(newPlayerName, 0, 1);
        layout.add(continueButton, 0, 2);

        scene = new Scene(layout, WIDTH, HEIGHT);
    }

    /**
     * Returns the scene for this view.
     */
    public Scene getScene() {
        return scene;
    }
}