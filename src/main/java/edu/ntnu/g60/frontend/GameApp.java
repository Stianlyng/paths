package edu.ntnu.g60.frontend;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Player;
import edu.ntnu.g60.models.PlayerBuilder;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.utils.fileParser.FileParser;
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

    private static Stage stage;
    
    //move
    static int textLine;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        GameApp.stage = stage;
        stage.setTitle("Half life 3");
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/images/icons/icon.png")));
        stage.setResizable(false);

        OpeningPane startMenu = new OpeningPane();
        Scene scene = new Scene(startMenu, HEIGHT, WIDTH, backgound);
        stage.setScene(scene);
        scene.getStylesheets().add("StyleSheet.css");
        stage.show();
    }

    //TODO: move
    public static int getTextLine(){
        return textLine;
    }

    //move
    public static void setTextLine(int amount){
        textLine = amount;
    }

    public static void changeRootPane(Pane pane) {
        try {
            stage.getScene().setRoot(pane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //TODO: move
    public static Game getGame(){
        FileParser fileParser = new FileParser("src/main/resources/textFiles/haunted_house.txt");
        Story story = fileParser.buildStory();

        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new HealthGoal(4));
        
        Player player = new PlayerBuilder()
                .setName("Alice")
                .build();

        Game game = new Game(player, story, goals);
        return game;
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
