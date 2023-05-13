package edu.ntnu.g60.models.goals;

import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class GoalTest {


    /**
     * Test the functionality of the GoldGoal class.
     * The test first creates a player with 100 gold and a goal of 50 gold.
     * It checks that the goal is fulfilled.
     * Then it subtracts 55 gold from the player and checks that the goal is not fulfilled.
     */
    @Test
    public void testGoldGoal() {
        Player player = new PlayerBuilder()
                .setName("John Doe")
                .setGold(100)
                .build();
        GoldGoal goldGoal = new GoldGoal(50);
        assertTrue(goldGoal.isFulfilled(player));

        player.addGold(-55);
        assertFalse(goldGoal.isFulfilled(player));
    }

    /**
     * Test the functionality of the HealthGoal class.
     * The test first creates a player with 100 health and a goal of 50 health.
     * It checks that the goal is fulfilled.
     * Then it subtracts 60 health from the player and checks that the goal is not fulfilled.
     */
    @Test
    public void testHealthGoal() {
        Player player = new PlayerBuilder()
                .setName("John Doe")
                .setHealth(100)
                .build();
        HealthGoal healthGoal = new HealthGoal(50);
        assertTrue(healthGoal.isFulfilled(player));

        player.addHealth(-60);
        assertFalse(healthGoal.isFulfilled(player));
    }

    /**
     * Test the functionality of the InventoryGoal class.
     * The test first creates a player with a sword and a shield in their inventory and a goal of having a sword and a shield.
     * It checks that the goal is fulfilled.
     * Then it changes the goal to require a sword, a shield, and a potion, and checks that the goal is not fulfilled.
     */
    @Test
    public void testInventoryGoal() {
        List<String> items = Arrays.asList("sword", "shield");
        Player player = new PlayerBuilder()
                .setName("John Doe")
                .setInventory(items)
                .build();

        InventoryGoal inventoryGoal = new InventoryGoal(items);
        assertTrue(inventoryGoal.isFulfilled(player));

        inventoryGoal = new InventoryGoal(Arrays.asList("sword", "shield", "potion"));
        assertFalse(inventoryGoal.isFulfilled(player));
    }

    /**
     * Test the functionality of the ScoreGoal class.
     * The test first creates a player with a score of 100 and a goal of 50 points.
     * It checks that the goal is fulfilled.
     * Then it subtracts 60 points from the player and checks that the goal is not fulfilled.
     */
    @Test
    public void testScoreGoal() {
        Player player = new PlayerBuilder()
                .setName("John Doe")
                .setScore(100)
                .build();

        ScoreGoal scoreGoal = new ScoreGoal(50);
        assertTrue(scoreGoal.isFulfilled(player));

        player.addScore(-60);
        assertFalse(scoreGoal.isFulfilled(player));
    }
    
    
}