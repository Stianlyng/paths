package edu.ntnu.g60.entities;

import java.util.ArrayList;
import java.util.List;

public class GoalEntity {

  private int score;
  private int gold;
  private int health;
  private List<String> inventory;

  public static final int MINIMUM_GOLD = 100;
  public static final int MINIMUM_HEALTH = 100;
  public static final int MINIMUM_SCORE = 100;

  /**
   * Constructor for the GoalEntity class.
   * Contains the default values for the goal of the game.
   */
  public GoalEntity() {
    this.score = MINIMUM_SCORE;
    this.gold = MINIMUM_GOLD;
    this.health = MINIMUM_HEALTH;
    this.inventory = new ArrayList<>();
  }

  /**
   * Gets the score of the player.
   *
   * @return An integer representing the score of the player.
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the score of the player.
   *
   * @param score An integer representing the score of the player.
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Gets the gold of the player.
   *
   * @return An integer representing the gold of the player.
   */
  public int getGold() {
    return gold;
  }

  /**
   * Sets the gold of the player.
   *
   * @param gold An integer representing the gold of the player.
   */
  public void setGold(int gold) {
    this.gold = gold;
  }

  /**
   * Gets the health of the player.
   *
   * @return An integer representing the health of the player.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the health of the player.
   *
   * @param health An integer representing the health of the player.
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Gets the inventory of the player.
   *
   * @return A list of strings representing the inventory of the player.
   */
  public List<String> getInventory() {
    return inventory;
  }

  /**
   * Sets the inventory of the player.
   *
   * @param inventory A list of strings representing the inventory of the player.
   */
  public void setInventory(List<String> inventory) {
    this.inventory = inventory;
  }
}
