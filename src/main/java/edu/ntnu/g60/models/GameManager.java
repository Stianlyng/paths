package edu.ntnu.g60.models;

import java.util.List;

import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.story.Story;

public class GameManager {
  private static GameManager instance;
  private Game game;
  private Player player;
  private Story story;
  private List<Goal> goals;

  private GameManager() {
    // Private constructor to prevent instantiation
  }

  public static GameManager getInstance() {
    if (instance == null) {
      instance = new GameManager();
    }
    return instance;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setStory(Story story) {
    this.story = story;
  }

  public void setGoals(List<Goal> goals) {
    this.goals = goals;
  }

  public void createGame() {
    if (game != null) {
      throw new IllegalStateException("A game is already in progress.");
    }
    if (player == null || story == null || goals == null) {
      throw new IllegalStateException("Player, story, and goals must be set before creating a game.");
    }
    game = new Game(player, story, goals);
  }

  public Game getGame() {
    if (game == null) {
      throw new IllegalStateException("No game has been created yet.");
    }
    return game;
  }

  public void endGame() {
    game = null;
  }
}