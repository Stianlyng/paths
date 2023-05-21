package edu.ntnu.g60.models.actions;


import edu.ntnu.g60.exceptions.model.actions.IllegalActionException;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;


/**
 * Test class for all the actionis.
 * 
 * @author Stian Lyng
 */
public class ActionsTest {

    private Player player;

    /**
     * Creates a new player before each test.
     */
    @BeforeEach
    public void setUp() {
        player = new PlayerBuilder()
                .setName("s1mple")
                .setHealth(0)
                .setScore(0)
                .setGold(0)
                .build();
    }

    /**
     * Checks that the player's health is increased by the value provided.
     * @throws IllegalActionException if the player is null.
     */
    @Test
    public void testGoldAction() throws IllegalActionException {
        GoldAction goldAction = new GoldAction(50);
        goldAction.execute(player);
        assertEquals(50, player.getGold());
        assertThrows(IllegalActionException.class, () -> goldAction.execute(null));
    }

    /**
     * Checks that the player's health is increased by the value provided.
     * @throws IllegalActionException if the player is null.
     */
    @Test
    public void testHealthAction() throws IllegalActionException {
        HealthAction healthAction = new HealthAction(10);
        healthAction.execute(player);
        assertEquals(10, player.getHealth());
        assertThrows(IllegalActionException.class, () -> healthAction.execute(null));
    }

    /**
     * Checks that the player's health is increased by the value provided.
     * @throws IllegalActionException if the player is null.
     */
    @Test
    public void testInventoryAction() throws IllegalActionException {
        InventoryAction inventoryAction = new InventoryAction("Sword");
        inventoryAction.execute(player);
        assertEquals(1, player.getInventory().size());
        assertEquals("Sword", player.getInventory().get(0));
        assertThrows(IllegalActionException.class, () -> inventoryAction.execute(null));
    }

    /**
     * Checks that the player's health is increased by the value provided.
     * @throws IllegalActionException if the player is null.
     */
    @Test
    public void testScoreAction() throws IllegalActionException {
        ScoreAction scoreAction = new ScoreAction(100);
        scoreAction.execute(player);
        assertEquals(100, player.getScore());
        assertThrows(IllegalActionException.class, () -> scoreAction.execute(null));
    }
    
}