package edu.ntnu.g60.views;

import java.io.IOException;

import edu.ntnu.g60.controllers.SoundController;
import edu.ntnu.g60.exceptions.MusicControllerException;
import edu.ntnu.g60.views.StartMenu.SelectPlayerPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * The GameApp class is the main entry point for the game application.
 * It extends the JavaFX Application class and provides the start() method
 * to initialize and start the game.
 * The game application displays a window with a start menu and handles
 * various game-related operations such as playing music, changing the root pane, and closing the application.
 * @author olav sie
*/
public class GameApp extends Application {
    
    private static final int HEIGHT = 800;
    private static final int WIDTH = 600;
    private static final Color backgound = Color.WHITE;
    private static final String TITLE = "Paths";
    private static final String ICON_PATH = "/images/icons/icon.png";
    private static final String STYLESHEET = "StyleSheet.css";

    private static Stage stage;
    
    /**
     * The start() method is called when the game application is launched.
     * It initializes the game window, sets up the start menu, and plays background music.
     * 
     * @param stage the primary stage for the game application
     * @throws IOException                if an I/O error occurs
     * @throws InterruptedException       if the music playback is interrupted
     * @throws MusicControllerException   if there is an error with the music controller
    */
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        GameApp.stage = stage;
        stage.setTitle(TITLE);
        stage.getIcons().add(new Image(GameApp.class.getResourceAsStream(ICON_PATH)));

        stage.setResizable(false);
        SelectPlayerPane startMenu = new SelectPlayerPane();
        Scene scene = new Scene(startMenu, HEIGHT, WIDTH, backgound);
        stage.setScene(scene);
        scene.getStylesheets().add(STYLESHEET);
        stage.show();
        try {
            SoundController.playMusic("music");
            SoundController.setApplicationVolume((double) 0.03);
        } catch (MusicControllerException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Returns the stage of the game application.
     * 
     * @return the stage of the game application
    */
    public static Stage getStage(){
        return stage;
    }

    /**
     * Closes the game application.
    */
    public static void closeApplication(){
        stage.close();
    }

    /**
     * Changes the root pane of the game application to the specified pane.
     * 
     * @param pane the new root pane to be set
    */
    public static void changeRootPane(Pane pane) {
        try {
            stage.getScene().setRoot(pane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The main entry point of the game application.
     * It launches the JavaFX application.
     * 
     * @param args the command line arguments
    */
    public static void main(String[] args){launch();}
}
