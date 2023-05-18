package edu.ntnu.g60.controllers;

import java.util.List;

import edu.ntnu.g60.exceptions.BrokenLinkException;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageManager;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.parsers.StoryParser;


/**
 * The GameController class is responsible for controlling the game flow and managing game-related operations.
 * It provides methods for setting and retrieving player, story, and save names, 
 * creating a new game, and delaying execution.
*/
public class GameController {

    static String playerName;

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


    

}
