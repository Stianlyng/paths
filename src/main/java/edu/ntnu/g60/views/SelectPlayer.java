package edu.ntnu.g60.views;

import edu.ntnu.g60.components.BackgroundComponent;
import edu.ntnu.g60.utils.SaveFileHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class SelectPlayer extends StackPane {
    private static final int PADDING = 20;

    public SelectPlayer() {

        TextField newPlayerName = new TextField();
        newPlayerName.setPromptText("Enter a new player name");

        ComboBox<String> playerSelection = new ComboBox<>();
        playerSelection.setPromptText("Select a player");
        playerSelection.getItems().addAll(SaveFileHandler.getAvailablePlayers());

        playerSelection.setOnAction(e -> newPlayerName.setText(playerSelection.getValue()));

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            String enteredPlayer = newPlayerName.getText();

            switchToMainMenu(enteredPlayer);
        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        layout.add(playerSelection, 0, 0);
        layout.add(newPlayerName, 0, 1);
        layout.add(continueButton, 0, 2);

        Background background = BackgroundComponent.createBackground("selectPlayerScene.png");
        layout.setBackground(background);
        this.getChildren().add(layout);
    }

    public StackPane getLayout() {
        return this;
    }

    private void switchToMainMenu(String playerName) {
        MainMenu mainMenu = new MainMenu(playerName);
        App.changeRootPane(mainMenu.getLayout());
    }
}