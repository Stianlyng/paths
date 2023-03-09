package edu.ntnu.g60;
import java.util.ArrayList;
import java.util.List;

public class Player {
  String name;
  int health;
  int score;
  int gold;
  List<String> inventory = new ArrayList<>();

  Player(String name, int health, int score, int gold){
    if(health >= 0){
      this.name = name;
      this.health = health;
      this.score = score;
      this.gold = gold;
    } else {
      throw new IllegalArgumentException("Health cannot be less than 0");
    }
  }

  public String getName(){
    return this.name;
  }

  public void addHealth(int health){
    this.health =+ health;
  }

  public int getHealth(){
    return this.health;
  }

  public void addScore(int points){
    this.score =+ score;
  }

  public int getScore(){
    return this.score;
  }

  public void addGold(int gold){
    this.gold =+ gold;
  }

  public int getGold(){
    return this.gold;
  }

  public void addToInventory(String item){
    inventory.add(item);
  }

  public List<String> getInventory() {
    return inventory;
  }

  @Override
  public String toString(){
    return "Name: " + getName()
        + "\nHealth: " + getHealth()
        + "\nScore: " + getScore()
        + "\nGold: " + getGold()
        + "\nInventory: " + getInventory();
  }
}
