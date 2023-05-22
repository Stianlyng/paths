package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.entities.ActionEntity;
import edu.ntnu.g60.exceptions.model.actions.InvalidActionTypeException;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the ActionFactory class.
 * 
 * @author Stian Lyng
 * @version 1.0
 */
class ActionFactoryTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new PlayerBuilder()
                .setName("s1mple")
                .setHealth(100)
                .setScore(0)
                .setGold(0)
                .build();
    }

    /**
     * Checks that the player's health is increased by the value provided in the ActionEntity.
     */
    @Test
    void health_is_increased() {
        ActionEntity actionEntity = new ActionEntity();
        actionEntity.setType("HEALTH");
        actionEntity.setValue(50);
        Action action = ActionFactory.createAction(actionEntity);
        action.execute(player);
        assertEquals(150, player.getHealth());
    }

    /**
     * checks that the players gold is increased by the value provided in the ActionEntity.
     */
    @Test
    void gold_is_increased() {
        ActionEntity actionEntity = new ActionEntity();
        actionEntity.setType("GOLD");
        actionEntity.setValue(100);
        Action action = ActionFactory.createAction(actionEntity);
        action.execute(player);
        assertEquals(100, player.getGold());
    }

    /**
     * Checks that the players score is increased by the value provided in the ActionEntity.
     */
    @Test
    void score_is_increased() {
        ActionEntity actionEntity = new ActionEntity();
        actionEntity.setType("SCORE");
        actionEntity.setValue(200);
        Action action = ActionFactory.createAction(actionEntity);
        action.execute(player);
        assertEquals(200, player.getScore());
    }

    /**
     * Checks that the item provided in the ActionEntity is added to the players inventory.
     */
    @Test
    void inventory_has_item() {
        ActionEntity actionEntity = new ActionEntity();
        actionEntity.setType("INVENTORY");
        actionEntity.setValue("sword");
        Action action = ActionFactory.createAction(actionEntity);
        action.execute(player);
        assertEquals("sword", player.getInventory().get(0));
    }

    /**
     * Checks that an InvalidActionTypeException is thrown if the type is invalid.
     */
    @Test
    void action_throws_invalid_type() {
        ActionEntity actionEntity = new ActionEntity();
        actionEntity.setType("INVALID");
        actionEntity.setValue(0);
        assertThrows(InvalidActionTypeException.class, () -> ActionFactory.createAction(actionEntity));
    }

    /**
     * Checks that an InvalidActionTypeException is thrown if the value is invalid.
     */
    @Test
    void action_throws_invalid_value() {
        ActionEntity actionEntity = new ActionEntity();
        actionEntity.setType("HEALTH");
        actionEntity.setValue("invalid");
        assertThrows(InvalidActionTypeException.class, () -> ActionFactory.createAction(actionEntity));
    }
}