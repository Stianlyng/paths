package edu.ntnu.g60.models.player;

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

    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    public PlayerBuilder setScore(int score) {
        this.score = score;
        return this;
    }

    public PlayerBuilder setGold(int gold) {
        this.gold = gold;
        return this;
    }

    public PlayerBuilder setInventory(List<String> inventory) {
        this.inventory = inventory;
        return this;
    }

    public PlayerBuilder addItemToInventory(String item) {
        this.inventory.add(item);
        return this;
    }

    public Player build() {
        Player player = new Player(name, health, score, gold, inventory);
        return player;
    }

    /*
    public static void main(String[] args) {
        Player player = new PlayerBuilder()
            .setName("John Doe")
            .setHealth(100)
            .setScore(0)
            .setGold(50)
            .addItemToInventory("Sword")
            .addItemToInventory("Shield")
            .build();
    }
    */

}