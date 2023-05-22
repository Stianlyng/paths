package edu.ntnu.g60.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerEntityTest {

  private PlayerEntity playerEntity;

  @BeforeEach
  public void setUp() {
    playerEntity = new PlayerEntity();
  }

  @Test
  public void testGetName() {
    String name = "testName";
    playerEntity.setName(name);
    assertEquals(name, playerEntity.getName());
  }

  @Test
  public void testSetName() {
    String name = "newName";
    playerEntity.setName(name);
    assertEquals(name, playerEntity.getName());
  }

  @Test
  public void testGetHealth() {
    int health = 100;
    playerEntity.setHealth(health);
    assertEquals(health, playerEntity.getHealth());
  }

  @Test
  public void testSetHealth() {
    int health = 200;
    playerEntity.setHealth(health);
    assertEquals(health, playerEntity.getHealth());
  }

  @Test
  public void testGetScore() {
    int score = 50;
    playerEntity.setScore(score);
    assertEquals(score, playerEntity.getScore());
  }

  @Test
  public void testSetScore() {
    int score = 100;
    playerEntity.setScore(score);
    assertEquals(score, playerEntity.getScore());
  }

  @Test
  public void testGetGold() {
    int gold = 500;
    playerEntity.setGold(gold);
    assertEquals(gold, playerEntity.getGold());
  }

  @Test
  public void testSetGold() {
    int gold = 1000;
    playerEntity.setGold(gold);
    assertEquals(gold, playerEntity.getGold());
  }

  @Test
  public void testGetInventory() {
    List<String> inventory = new ArrayList<>();
    inventory.add("Sword");
    playerEntity.setInventory(inventory);
    assertEquals(inventory, playerEntity.getInventory());
  }

  @Test
  public void testSetInventory() {
    List<String> inventory = new ArrayList<>();
    inventory.add("Shield");
    playerEntity.setInventory(inventory);
    assertEquals(inventory, playerEntity.getInventory());
  }
}
