package edu.ntnu.g60.entities;

import java.util.List;

public class PlayerEntity {
    private String name;
    private int health;
    private int score;
    private int gold;
    private List<String> inventory;
    
    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
     public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
     public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    
     public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

}
