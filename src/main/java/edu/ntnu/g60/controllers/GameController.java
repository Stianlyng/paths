package edu.ntnu.g60.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.fileHandling.StoryParser;
import javafx.concurrent.Task;

public class GameController {

    static String playerName;
    static String storyName;

    public static void setStoryName(String name){
        storyName = name;
    }

    public static String getStoryName(){
        return storyName;
    }

    public static void setPLayerName(String name){
        playerName = name;
    }

    public static String getPlayerName(){
        return playerName;
    }


    public static List<String> listFilesInFolder() {
        Path folderPath = Paths.get("src/main/resources/stories");

        try (Stream<Path> paths = Files.list(folderPath)) {
            return paths.filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .map(name -> name.substring(0, name.lastIndexOf('.')))
                        .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void createNewGame(){
        
        List<String> inventory = List.of("Sword");

        Player player = new PlayerBuilder()
                .setName(playerName)
                .setHealth(100)
                .setGold(0)
                .setScore(0)
                .setInventory(inventory)
                .build();

        String storyPath = storyName;
        StoryParser parser = new StoryParser(storyPath);
        Story story = parser.build();


        List<Goal> goals = List.of(
                new HealthGoal(110),
                new GoldGoal(0),
                new InventoryGoal(List.of("Sword")),
                new ScoreGoal(100)
        );

        GameManager.getInstance().setPlayer(player);
        GameManager.getInstance().setStory(story);
        GameManager.getInstance().setGoals(goals);
        GameManager.getInstance().createGame();
    }

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
}
