package edu.ntnu.g60.views;

import java.io.FileInputStream;
import java.io.IOException;

import edu.ntnu.g60.controllers.SoundController;
import edu.ntnu.g60.views.StartMenu.SelectPlayerPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaException;
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
        SelectPlayerPane startMenu = new SelectPlayerPane();
        Scene scene = new Scene(startMenu, HEIGHT, WIDTH, backgound);
        stage.setScene(scene);
        scene.getStylesheets().add(STYLESHEET);
        stage.show();
        try {
            SoundController.playMusic("music");
            SoundController.setApplicationVolume((double) 0.03);
        } catch (MediaException e) {
            // Handle the exception, e.g., display an error message or fallback to alternative sound playback
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
    }

    public static void closeApplication(){
        stage.close();
    }

    public static void changeRootPane(Pane pane) {
        try {
            stage.getScene().setRoot(pane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){launch();}
}
