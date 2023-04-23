package edu.ntnu.g60.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new PlayerBuilder().withName("John").build();
    }

    @Test
    void testConstructorWithInventory() {
        List<String> inventory = Arrays.asList("sword", "shield");
        Player playerWithInventory = new PlayerBuilder()
            .withName("Alice")
            .withHealth(100)
            .withScore(0)
            .withGold(0)
            .withInventory(inventory)
            .build();
        assertEquals("Alice", playerWithInventory.getName());
        assertEquals(100, playerWithInventory.getHealth());
        assertEquals(0, playerWithInventory.getScore());
        assertEquals(0, playerWithInventory.getGold());
        assertEquals(inventory, playerWithInventory.getInventory());
    }

    @Test
    void testAddHealth() {
        player.addHealth(20);
        assertEquals(120, player.getHealth());
    }

    @Test
    void testAddScore() {
        player.addScore(10);
        assertEquals(10, player.getScore());
    }

    @Test
    void testAddGold() {
        player.addGold(5);
        assertEquals(5, player.getGold());
    }

    @Test
    void testAddToInventory() {
        player.addToInventory("sword");
        List<String> expectedInventory = Arrays.asList("sword");
        assertEquals(expectedInventory, player.getInventory());
        assertThrows(IllegalArgumentException.class, () -> player.addToInventory(""));
        assertThrows(IllegalArgumentException.class, () -> player.addToInventory(null));
    }

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