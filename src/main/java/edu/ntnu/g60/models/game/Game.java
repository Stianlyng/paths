package edu.ntnu.g60.models.game;

import edu.ntnu.g60.exceptions.InvalidLinkException;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.story.Story;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game class is the main class of the game.
 * It contains the player, the story, and the goals.
 *
 * @author Stian Lyng
 */
public class Game implements Serializable {

  private final Player player;
  private final Story story;
  private final List<Goal> goals;

  /**
   * Constructor for the Game class.
   *
   * @param player The player of the game.
   * @param story  The story of the game.
   * @param goals  The goals of the game.
   */
  public Game(Player player, Story story, List<Goal> goals) throws IllegalArgumentException {
    if (player == null) throw new IllegalArgumentException("Player cannot be null.");
    if (story == null) throw new IllegalArgumentException("Story cannot be null.");
    if (goals == null) throw new IllegalArgumentException("Goals cannot be null.");
    this.player = player;
    this.story = story;
    this.goals = goals;
  }

  /**
   * Deep Copy constructor for the Game class.
   *
   */
  public Game(Game other) {
    this.player = new Player(other.player);
    this.story = new Story(other.story);
    this.goals = new ArrayList<>(other.goals);
  }

  /**
   * Gets the player of the game.
   *
   * @return The player of the game.
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Gets the story of the game.
   *
   * @return The story of the game.
   */
  public Story getStory() {
    return this.story;
  }

  /**
   * Gets the goals of the game.
   *
   * @return The goals of the game.
   */
  public List<Goal> getGoals() {
    return this.goals;
  }

  /**
   * Starts the game by returning the first passage of the story.
   *
   * @return The first passage of the story.
   */
  public Passage begin() {
    return this.story.getOpeningPassage();
  }

  /**
   * Moves the player to the passage that the link points to.
   *
   * @param link The link to the passage.
   * @return The passage that the link points to.
   */
  public Passage go(Link link) throws InvalidLinkException {
    Passage passage = this.story.getPassage(link);
    if (passage == null) {
      throw new InvalidLinkException("The link does not have an existing passage in the story.");
    }
    return this.story.getPassage(link);
  }
}
