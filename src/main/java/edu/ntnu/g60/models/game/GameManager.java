package edu.ntnu.g60.models.game;

import java.util.List;

import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.story.Story;

/**
 * The GameManager class is a singleton class that manages the creation, retrieval, and ending of a Game instance.
 * @author Stian Lyng
 */
public class GameManager {
  private static GameManager instance;
  private Game game;
  private Player player;
  private Story story;
  private List<Goal> goals;

  /**
   * Private constructor to prevent instantiation.
   */
  private GameManager() {}

  /**
   * Returns the single instance of the GameManager class, creating it if necessary.
   *
   * @return The single instance of the GameManager class.
   */
  public static GameManager getInstance() {
    if (instance == null) {
      instance = new GameManager();
    }
    return instance;
  }

  /**
   * Sets the Player for the next game.
   *
   * @param player The Player for the next game.
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Sets the Story for the next game.
   *
   * @param story The Story for the next game.
   */
  public void setStory(Story story) {
    this.story = story;
  }

  /**
   * Sets the Goals for the next game.
   *
   * @param goals The Goals for the next game.
   */
  public void setGoals(List<Goal> goals) {
    this.goals = goals;
  }

  /**
   * Creates a new Game instance with the previously set Player, Story, and Goals.
   * Throws an IllegalStateException if a game is already in progress or if the Player, Story, and Goals have not been set.
   */
  public void createGame() {
    if (game != null) {
      throw new IllegalStateException("A game is already in progress.");
    }
    if (player == null || story == null || goals == null) {
      throw new IllegalStateException("Player, story, and goals must be set before creating a game.");
    }
    game = new Game(player, story, goals);
  }

  /**
   * Returns the current Game instance.
   * Throws an IllegalStateException if no game has been created yet.
   *
   * @return The current Game instance.
   */
  public Game getGame() {
    if (game == null) {
      throw new IllegalStateException("No game has been created yet.");
    }
    return game;
  }

  /**
   * Ends the current game, setting the Game instance to null.
   */
  public void endGame() {
    game = null;
  }
}