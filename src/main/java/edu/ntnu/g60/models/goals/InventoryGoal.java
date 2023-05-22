package edu.ntnu.g60.models.goals;

import java.io.Serializable;
import java.util.List;

import edu.ntnu.g60.models.player.Player;

/**
 * This interface represents actions that can be performed on a player.
 * 
 * @author Stian Lyng
 */
public class InventoryGoal implements Goal, Serializable {

    /**
     * This holds the mandatory items required to fulfill the goal
     */
    List<String> mandatoryItems;

    /**
     * Creates a new InventoryGoal with the given mandatory items.
     * 
     * @param mandatoryItems The mandatory items required to fulfill the goal.
     */
    public InventoryGoal(List<String> mandatoryItems) {
        this.mandatoryItems = mandatoryItems;
    }

    /**
     * Gets the mandatory items required to fulfill the goal.
     * 
     * @return A list of strings representing the mandatory items required to fulfill the goal.
     */
    public boolean isFulfilled(Player player) {
        for (String item : mandatoryItems) {
            if (!player.getInventory().contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the mandatory items required to fulfill the goal.
     * 
     * @return A list of strings representing the mandatory items required to fulfill the goal.
     */
    @Override
    public String toString() {
        return "InventoryGoal: " + this.mandatoryItems;
    }

}
