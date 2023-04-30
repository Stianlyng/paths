package edu.ntnu.g60.controllers;

import java.util.ArrayList;
import java.util.List;

import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.utils.fileHandling.StoryParser;

public class GameController {
    public static Game currentGame;
    public static Passage currentPassage;

    public static void setCurrentGame(Game game){
        currentGame = game;
    }

    public static Game getCurrentGame(){
        return currentGame;
    }

    public static void setCurrentPassage(Passage passage){
        currentPassage = passage;
    }

    public static Passage getCurrentPassage(){
        return currentPassage;
    }


    public static Game getNewGame(){

        StoryParser parser = new StoryParser("haunted_house");
        Story story = parser.build();



        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new HealthGoal(4));
    
        Player player = new PlayerBuilder()
                .setName("Alice")
                .build();

        Game game = new Game(player, story, goals);
        return game;
    }
}
