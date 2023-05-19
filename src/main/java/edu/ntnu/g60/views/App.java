package edu.ntnu.g60.views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main Application class.
 */
public class App extends Application {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Paths Adventure Game");

        SelectPlayer selectPlayerScene = new SelectPlayer();
        changeRootPane(selectPlayerScene.getLayout());

        primaryStage.show();
    }

    /**
     * Get the stage of the application
     * @return Stage - the application stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Set the scene of the application
     * @param pane Pane - the pane to set as the scene
     */
    public static void changeRootPane(Pane pane) {
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.getStylesheets().add("stylesheet.css");
        stage.setScene(scene);
    }
}
