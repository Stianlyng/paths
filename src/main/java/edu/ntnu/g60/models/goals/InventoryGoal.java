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
    List<String> mandatoryItems;

    public InventoryGoal(List<String> mandatoryItems) {
        this.mandatoryItems = mandatoryItems;
    }

    public boolean isFulfilled(Player player) {
        for (String item : mandatoryItems) {
            if (!player.getInventory().contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "InventoryGoal: " + this.mandatoryItems;
    }

}
