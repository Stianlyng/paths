package edu.ntnu.g60.models.actions;

import java.io.Serializable;

import edu.ntnu.g60.exceptions.model.actions.IllegalActionException;
import edu.ntnu.g60.models.player.Player;

/**
 * This interface represents actions that can be performed on a player.
 * 
 * @author Stian Lyng
 */
public class InventoryAction implements Action, Serializable {
    String inventory;

    /**
     * Creates a new InventoryAction.
     * 
     * @param inventory The inventory to add to the player.
     */
    public InventoryAction(String inventory) {
        this.inventory = inventory;
    }

    /**
     * Executes the action on the player.
     *
     * @param player The player to execute the action on.
     * @throws IllegalActionException if the player is null.
     */
    public void execute(Player player) throws IllegalActionException {
        if (player == null)
            throw new IllegalActionException("Player cannot be null.");
        player.addToInventory(inventory);
    }

}