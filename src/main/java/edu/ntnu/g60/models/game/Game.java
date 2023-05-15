package edu.ntnu.g60.models.game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.story.Story;

/**
 * The Game class is the main class of the game. 
 * It contains the player, the story, and the goals.
 */
public class Game implements Serializable{

  private final Player player;
  private final Story story;
  private final List<Goal> goals;
  private Passage currentPassage; // Add this field

  /**
   * Constructor for the Game class.
   * @param player The player of the game.
   * @param story The story of the game.
   * @param goals The goals of the game.
   */
  public Game(Player player, Story story, List<Goal> goals) throws IllegalArgumentException{
    if (player == null) throw new IllegalArgumentException("Player cannot be null.");
    if (story == null) throw new IllegalArgumentException("Story cannot be null.");
    if (goals == null) throw new IllegalArgumentException("Goals cannot be null.");
    this.player = player;
    this.story = story;
    this.goals = goals;
    this.currentPassage = this.story.getOpeningPassage(); // Initialize the current passage
  }

  /**
   * Constructor for the Game class.
   * 
   * Shallow copy of the goals is fine because Goal objects are immutable
   * todo; finish the java doc
   */
  public Game(Game other) {
      this.player = new Player(other.player); 
        this.story = new Story(other.story); 
        this.goals = new ArrayList<>(other.goals); 
        this.currentPassage = this.story.getPassage(other.currentPassage.getTitle()); // Copy the current passage
  }

  public Player getPlayer() {
    return this.player;
  }

  public Story getStory() {
    return this.story;
  }

  public List<Goal> getGoals() {
    return this.goals;
  }

  public Passage getCurrentPassage() {
        return this.currentPassage; // Add this getter method
  }

  public void setCurrentPassage(Passage passage){
    this.currentPassage = passage;
  }

  /**
   * Starts the game by returning the first passage of the story.
   * @return The first passage of the story.
   */
  public Passage begin() {
    this.currentPassage = this.story.getOpeningPassage(); 
    return this.currentPassage;
  }


  /**
   * Moves the player to the passage that the link points to.
   * @param link The link to the passage.
   * @return The passage that the link points to.
   */
  public Passage go(Link link) {
      this.currentPassage = this.story.getPassage(link.getReference()); // Update the current passage
      return this.currentPassage;

  }
}