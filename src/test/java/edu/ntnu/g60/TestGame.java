package edu.ntnu.g60;


import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.PassageBuilder;
import edu.ntnu.g60.models.Player;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.models.actions.*;
import edu.ntnu.g60.models.goals.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestGame {

  private Player player;
  private Story story;
  private List<Goal> goals;

  @BeforeEach
  public void setUp() {
    goals = new ArrayList<>();
    goals.add(new HealthGoal(4));
    
    player = new Player("Elon");
    Passage openingPassage = new PassageBuilder()
            .withTitle("Level 1")
            .withContent("You are in a dark room.")
            .build();

    story = new Story("The Dark World", openingPassage);
  }

    // main method
    
  /* For later....
  Player player = new Player("Bobby", 10, 10, 10);
  Passage openingPassage = new Passage("Level 1", "You are in a dark room.");
  Story story = new Story("The Dark World", openingPassage);
  
  //Game game = new Game(player, story);
  
  System.out.println("Welcome to " + story.getTitle());
  System.out.println("You are " + player);
  System.out.println("You are in " + openingPassage.getTitle());
  */
 
  }
  
  