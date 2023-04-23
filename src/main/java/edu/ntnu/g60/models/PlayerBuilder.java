package edu.ntnu.g60.models;

import java.util.ArrayList;
import java.util.List;

public class PlayerBuilder {
    private String name;
    private int health;
    private int score;
    private int gold;
    private List<String> inventory;

    public PlayerBuilder() {
        this.name = "Unnamed";
        this.health = 100;
        this.score = 0;
        this.gold = 0;
        this.inventory = new ArrayList<>();
    }

    public PlayerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder withHealth(int health) {
        this.health = health;
        return this;
    }

    public PlayerBuilder withScore(int score) {
        this.score = score;
        return this;
    }

    public PlayerBuilder withGold(int gold) {
        this.gold = gold;
        return this;
    }

    public PlayerBuilder withInventory(List<String> inventory) {
        this.inventory = inventory;
        return this;
    }

    public PlayerBuilder addItemToInventory(String item) {
        this.inventory.add(item);
        return this;
    }

    public Player build() {
        Player player = new Player(name, inventory);
        player.addHealth(health - 100); // Subtract 100 as it is set in the constructor
        player.addScore(score);
        player.addGold(gold);
        return player;
    }

    /*
    public static void main(String[] args) {
        Player player = new PlayerBuilder()
            .withName("John Doe")
            .withHealth(100)
            .withScore(0)
            .withGold(50)
            .addItemToInventory("Sword")
            .addItemToInventory("Shield")
            .build();
    }
    */

}