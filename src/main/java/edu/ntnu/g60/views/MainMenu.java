package edu.ntnu.g60.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Class representing the main menu.
 */
public class MainMenu {

    private static final int PADDING = 20;
    private static final double BUTTON_WIDTH = 200;

    private Scene scene;

    public MainMenu(Stage primaryStage, String playerName, int WIDTH, int HEIGHT) {

        Button newGameButton = new Button("New Game");
        Button loadGameButton = new Button("Load Game");
        Button importGameFileButton = new Button("Import Gamefile");

        newGameButton.setPrefWidth(BUTTON_WIDTH);
        loadGameButton.setPrefWidth(BUTTON_WIDTH);
        importGameFileButton.setPrefWidth(BUTTON_WIDTH);

        newGameButton.setOnAction(e -> {
            SelectStory selectStory = new SelectStory(primaryStage, WIDTH, HEIGHT);
            primaryStage.setScene(selectStory.getScene());
        });

        loadGameButton.setOnAction(e -> {
            LoadGame loadGame = new LoadGame(primaryStage,playerName, WIDTH, HEIGHT);
            primaryStage.setScene(loadGame.getScene());
        });

        importGameFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            // TODO: Handle the selected file
            
            if (selectedFile != null) {
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        layout.add(new Label("Welcome " + playerName), 0, 0);
        layout.add(newGameButton, 0, 1);
        layout.add(loadGameButton, 0, 2);
        layout.add(importGameFileButton, 0, 3);

        scene = new Scene(layout, WIDTH, HEIGHT);
    }

    /**
     * Returns the scene for this view.
     */
    public Scene getScene() {
        return scene;
    }
}