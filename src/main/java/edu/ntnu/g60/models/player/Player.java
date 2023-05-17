package edu.ntnu.g60.models.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Player class represents the player of the game.
 * It contains the name, health, score, gold, and inventory of the player.
 *
 * @author Stian Lyng
 */
public class Player implements Serializable {

  private String name;
  private int health;
  private int score;
  private int gold;
  private List<String> inventory;

  /**
   * Constructor for the Player class.
   * 
   * @param name      The name of the player.
   * @param health    The health of the player.
   * @param score     The score of the player.
   * @param gold      The gold of the player.
   * @param inventory The inventory of the player.
   * @throws IllegalArgumentException if name is null or blank
   * @throws IllegalArgumentException if health is less than 0
   * @throws IllegalArgumentException if score is less than 0
   * @throws IllegalArgumentException if gold is less than 0
   * @throws IllegalArgumentException if inventory is null
   */
  Player(String name, int health, int score, int gold, List<String> inventory) throws IllegalArgumentException {
    if (name == null || name.isBlank())
      throw new IllegalArgumentException("Name cannot be null or blank.");
    if (health < 0)
      throw new IllegalArgumentException("Health cannot be less than 0.");
    if (score < 0)
      throw new IllegalArgumentException("Score cannot be less than 0.");
    if (gold < 0)
      throw new IllegalArgumentException("Gold cannot be less than 0.");
    if (inventory == null)
      throw new IllegalArgumentException("Inventory cannot be null.");
    this.name = name;
    this.health = health;
    this.score = score;
    this.gold = gold;
    this.inventory = inventory;
  }

  /**
   * Copy constructor for the Player class.
   * 
   * @param other The Player object to copy.
   */
  public Player(Player other) {
    this.name = other.name;
    this.health = other.health;
    this.score = other.score;
    this.gold = other.gold;
    this.inventory = new ArrayList<>(other.inventory);
  }

  public String getName() {
    return this.name;
  }

  public void addHealth(int health) {
    this.health += health;
  }

  public int getHealth() {
    return this.health;
  }

  public void addScore(int points) {
    this.score += points;
  }

  public int getScore() {
    return this.score;
  }

  public void addGold(int gold) {
    this.gold += gold;
  }

  public int getGold() {
    return this.gold;
  }

  /**
   * Adds an item to the inventory.
   * 
   * @param item The item to add.
   * @throws IllegalArgumentException if item is null or blank.
   */
  public void addToInventory(String item) throws IllegalArgumentException {
    if (item == null || item.isBlank())
      throw new IllegalArgumentException("Item cannot be null or blank.");
    inventory.add(item);
  }

  /**
   * Removes an item from the inventory.
   * 
   * @param item The item to remove.
   * @throws IllegalArgumentException if item is null or blank.
   */
  public void removeFromInventory(String item) throws IllegalArgumentException {
    if (item == null || item.isBlank())
      throw new IllegalArgumentException("Item cannot be null or blank.");
    this.inventory.remove(item);
  }

  public List<String> getInventory() {
    return inventory;
  }

  @Override
  public String toString() {
    return "Name: " + name
        + ", Health: " + health
        + ", Score: " + score
        + ", Gold: " + gold
        + ", Inventory: " + inventory;
  }
}
