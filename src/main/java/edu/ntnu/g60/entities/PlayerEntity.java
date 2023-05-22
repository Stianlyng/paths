package edu.ntnu.g60.entities;

import java.util.List;

/**
 * This class represents the structure of a player in the JSON file.
 * It is used by the StoryParser to parse the JSON file.
 *
 * @see StoryParser
 * @author Stian Lyng
 */
public class PlayerEntity {
    
    /**
     * This holds the name of the player
     */
    private String name;
    
    /**
     * This holds the health of the player
     */
    private int health;
    
    /**
     * This holds the score of the player
     */
    private int score;
    
    /**
     * This holds the gold of the player
     */
    private int gold;
    
    /**
     * This holds the inventory of the player
     */
    private List<String> inventory;
    
    /**
     * Gets the name of the player.
     *
     * @return A string representing the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name A string representing the name of the player.
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets the inventory of the player.
     *
     * @return A list of strings representing the items in the player's inventory.
     */
    public List<String> getInventory() {
        return inventory;
    }

    /**
     * Sets the inventory of the player.
     *
     * @param inventory A list of strings representing the items in the player's inventory.
     */
    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

}