package edu.ntnu.g60.controllers;

import java.util.List;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.parsers.StoryParser;
import javafx.concurrent.Task;


/**
 * The GameController class is responsible for controlling the game flow and managing game-related operations.
 * It provides methods for setting and retrieving player, story, and save names, 
 * creating a new game, and delaying execution.
*/
public class GameController {

    static String playerName;
    static String storyName;
    static String saveName;

    /**
    * Sets the name of the save file.
    *
    * @param name the name of the save file
    */
    public static void setSaveName(String name){
        saveName = name;
    }

    /**
    * Retrieves the name of the save file.
    *
    * @return the name of the save file
    */
    public static String getSaveName(){
        return saveName;
    }

    /**
    * Sets the name of the story.
    *
    * @param name the name of the story
    */
    public static void setStoryName(String name){
        storyName = name;
    }

    /**
    * Retrieves the name of the story.
    *
    * @return the name of the story
    */
    public static String getStoryName(){
        return storyName;
    }

    /**
    * Sets the name of the player.
    *
    * @param name the name of the player
    */
    public static void setPLayerName(String name){
        playerName = name;
    }

    /**
    * Retrieves the name of the player.
    *
    * @return the name of the player
    */
    public static String getPlayerName(){
        return playerName;
    }


    /**
    * Creates a new game instance.
    * Initializes the player, story, goals, and creates the game.
    */
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

    /**
     * Delays the execution of a given continuation by the specified number of milliseconds.
     * @param millis the number of milliseconds to delay
     * @param continuation the task to execute after the delay
    */
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
