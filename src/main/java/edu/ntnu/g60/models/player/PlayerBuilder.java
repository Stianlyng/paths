package edu.ntnu.g60.models.player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for building a Player object.
 * 
 * @author Stian Lyng
 */
public class PlayerBuilder {
    
    /**
     * The name of the player.
     */
    private String name;
    
    /**
     * The health of the player.
     */
    private int health;
    
    /**
     * The score of the player.
     */
    private int score;
    
    /**
     * The amount of gold of the player.
     */
    private int gold;
    
    /**
     * The inventory of the player.
     */
    private List<String> inventory;

    /**
     * Constructor for the PlayerBuilder class.
     * With default values.
     */
    public PlayerBuilder() {
        this.name = "Unnamed";
        this.health = 100;
        this.score = 0;
        this.gold = 0;
        this.inventory = new ArrayList<>();
    }

    /**
     * Sets the name of the player.
     *
     * @param name The name of the player.
     * @return The PlayerBuilder object.
     */
    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the health of the player.
     *
     * @param health The health of the player.
     * @return The PlayerBuilder object.
     */
    public PlayerBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    /**
     * Sets the score of the player.
     *
     * @param score The score of the player.
     * @return The PlayerBuilder object.
     */
    public PlayerBuilder setScore(int score) {
        this.score = score;
        return this;
    }

    /**
     * Sets the gold of the player.
     *
     * @param gold The gold of the player.
     * @return The PlayerBuilder object.
     */
    public PlayerBuilder setGold(int gold) {
        this.gold = gold;
        return this;
    }

    /**
     * Sets the inventory of the player.
     *
     * @param inventory The inventory of the player.
     * @return The PlayerBuilder object.
     */
    public PlayerBuilder setInventory(List<String> inventory) {
        this.inventory = inventory;
        return this;
    }

    /**
     * Adds an item to the inventory of the player.
     * If only one item is to be added, use this method.
     *
     * @param item The item to add to the inventory of the player.
     * @return The PlayerBuilder object.
     */
    public PlayerBuilder addItemToInventory(String item) {
        this.inventory.add(item);
        return this;
    }

    /**
     * Builds the Player object.
     * 
     * @return The Player object.
     */
    public Player build() {
        Player player = new Player(name, health, score, gold, inventory);
        return player;
    }

}