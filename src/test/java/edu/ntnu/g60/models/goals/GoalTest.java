package edu.ntnu.g60.models.goals;

import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for the Goal class.
 * 
 * @author Stian Lyng
 */
public class GoalTest {


    // the player
    private Player player;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp() {
        player = new PlayerBuilder()
                .setName("Test Player")
                .setGold(100)
                .setHealth(100)
                .setInventory(Arrays.asList("sword", "shield"))
                .setScore(50)
                .build();
    }

    /**
     * Tests if the gold goal is correctly fulfilled or not based on player's gold quantity.
     */   
    @Test
    public void testGoldGoal() {
        GoldGoal goldGoal = new GoldGoal(50);
        assertTrue(goldGoal.isFulfilled(player));

        player.addGold(-55);
        assertFalse(goldGoal.isFulfilled(player));
    }

    /**
     * Tests if the health goal is correctly fulfilled or not based on player's health points.
     */
    @Test
    public void testHealthGoal() {
        HealthGoal healthGoal = new HealthGoal(50);
        assertTrue(healthGoal.isFulfilled(player));
        player.addHealth(-60);
        assertFalse(healthGoal.isFulfilled(player));
    }

    /**
     * Tests if the inventory goal is correctly fulfilled or not based on the items present in the player's inventory.
     */
    @Test
    public void testInventoryGoal() {
        List<String> items = Arrays.asList("sword", "shield");
        InventoryGoal inventoryGoal = new InventoryGoal(items);
        assertTrue(inventoryGoal.isFulfilled(player));

        inventoryGoal = new InventoryGoal(Arrays.asList("sword", "shield", "potion"));
        assertFalse(inventoryGoal.isFulfilled(player));
    }

    /**
     * Tests if the score goal is correctly fulfilled or not based on player's score.
     */
    @Test
    public void testScoreGoal() {
        ScoreGoal scoreGoal = new ScoreGoal(50);
        assertTrue(scoreGoal.isFulfilled(player));

        player.addScore(-60);
        assertFalse(scoreGoal.isFulfilled(player));
    }
    
    
}