package edu.ntnu.g60.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadGame{

    private static final int PADDING = 20;

    private Scene scene;

    public LoadGame(Stage primaryStage, int WIDTH, int HEIGHT) {
        VBox newSceneLayout = new VBox(new Label("Welcome " ));
        newSceneLayout.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        scene = new Scene(newSceneLayout, WIDTH, HEIGHT);
    }

    /**
     * Returns the scene for this view.
     */
    public Scene getScene() {
        return scene;
    }
}