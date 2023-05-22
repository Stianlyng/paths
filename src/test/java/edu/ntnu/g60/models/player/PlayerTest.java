package edu.ntnu.g60.models.player;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the player class and player builder class.
 *
 * @author Stian Lyng
 */
class PlayerTest {

  private Player player;

  @BeforeEach
  void setUp() {
    player = new PlayerBuilder().setName("John").build();
  }

  /**
   * Tests if PlayerBuilder correctly sets all fields of Player object.
   */
  @Test
  void testConstructorsetInventory() {
    List<String> inventory = Arrays.asList("sword", "shield");
    Player playersetInventory = new PlayerBuilder()
      .setName("Alice")
      .setHealth(100)
      .setScore(0)
      .setGold(0)
      .setInventory(inventory)
      .build();
    assertEquals("Alice", playersetInventory.getName());
    assertEquals(100, playersetInventory.getHealth());
    assertEquals(0, playersetInventory.getScore());
    assertEquals(0, playersetInventory.getGold());
    assertEquals(inventory, playersetInventory.getInventory());
  }

  /**
   * Tests if method addHealth correctly increases player's health.
   */
  @Test
  void testAddHealth() {
    player.addHealth(20);
    assertEquals(120, player.getHealth());
  }

  /**
   * Tests if method addScore correctly increases player's score.
   */
  @Test
  void testAddScore() {
    player.addScore(10);
    assertEquals(10, player.getScore());
  }

  /**
   * Tests if method addGold correctly increases player's gold.
   */
  @Test
  void testAddGold() {
    player.addGold(5);
    assertEquals(5, player.getGold());
  }

  /**
   * Tests if method addToInventory correctly adds item to player's inventory and if it correctly handles invalid inputs.
   */
  @Test
  void testAddToInventory() {
    player.addToInventory("sword");
    List<String> expectedInventory = Arrays.asList("sword");
    assertEquals(expectedInventory, player.getInventory());
    assertThrows(IllegalArgumentException.class, () -> player.addToInventory(""));
    assertThrows(IllegalArgumentException.class, () -> player.addToInventory(null));
  }

  /**
   * Tests the toString method.
   */
  @Test
  void testToString() {
    player.addHealth(20);
    player.addScore(10);
    player.addGold(5);
    player.addToInventory("sword");
    String expected = "Name: John, Health: 120, Score: 10, Gold: 5, Inventory: [sword]";
    assertEquals(expected, player.toString());
  }
}
