package edu.ntnu.g60.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoalEntityTest {

    private GoalEntity goalEntity;

    @BeforeEach
    void setUp() {
        goalEntity = new GoalEntity();
    }

    @Test
    void testConstructor() {
        assertEquals(GoalEntity.MINIMUM_SCORE, goalEntity.getScore());
        assertEquals(GoalEntity.MINIMUM_GOLD, goalEntity.getGold());
        assertEquals(GoalEntity.MINIMUM_HEALTH, goalEntity.getHealth());
        assertTrue(goalEntity.getInventory().isEmpty());
    }

    @Test
    void testSetAndGetScore() {
        int newScore = 200;
        goalEntity.setScore(newScore);
        assertEquals(newScore, goalEntity.getScore());
    }

    @Test
    void testSetAndGetGold() {
        int newGold = 200;
        goalEntity.setGold(newGold);
        assertEquals(newGold, goalEntity.getGold());
    }

    @Test
    void testSetAndGetHealth() {
        int newHealth = 200;
        goalEntity.setHealth(newHealth);
        assertEquals(newHealth, goalEntity.getHealth());
    }

    @Test
    void testSetAndGetInventory() {
        List<String> newInventory = new ArrayList<>(Arrays.asList("sword", "shield"));
        goalEntity.setInventory(newInventory);
        assertEquals(newInventory, goalEntity.getInventory());
    }
}