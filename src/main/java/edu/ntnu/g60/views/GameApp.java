package edu.ntnu.g60.views;

import java.io.FileInputStream;
import java.io.IOException;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class GameApp extends Application {
    
    private static final int HEIGHT = 800;
    private static final int WIDTH = 600;
    private static final Color backgound = Color.WHITE;
    private static final String TITLE = "Half life 3";
    private static final String ICON_PATH = "src/main/resources/images/icons/icon.png";
    private static final String STYLESHEET = "StyleSheet.css";

    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        GameApp.stage = stage;
        stage.setTitle(TITLE);
        stage.getIcons().add(new Image(new FileInputStream(ICON_PATH)));
        stage.setResizable(false);
        OpeningPane startMenu = new OpeningPane();
        Scene scene = new Scene(startMenu, HEIGHT, WIDTH, backgound);
        stage.setScene(scene);
        scene.getStylesheets().add(STYLESHEET);
        stage.show();
    }

    public static void changeRootPane(Pane pane) {
        try {
            stage.getScene().setRoot(pane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //move maybe?
    public static void delay(long millis, Runnable continuation){
        Task<Void> sleeper = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    public static void main(String[] args){launch();}
}