package edu.ntnu.g60.views;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Application class.
 */
public class App extends Application {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Paths Adventure Game");
        SelectPlayer selectPlayerScene = new SelectPlayer(primaryStage, WIDTH, HEIGHT);
        primaryStage.setScene(selectPlayerScene.getScene());
        primaryStage.show();
    }
}
