package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.exceptions.model.actions.IllegalActionException;
import edu.ntnu.g60.models.player.Player;

/**
 * This interface represents actions that can be performed on a player.
 * 
 * @author Stian Lyng
 */
public class GoldAction implements Action{
    int gold;

    /**
     * Creates a new GoldAction.
     * @param gold The gold to add to the player.
     */
    public GoldAction(int gold){
        this.gold = gold;
    }

    /**
     * Executes the action on the player.
     * @param player The player to execute the action on.
     * @throws IllegalActionException if the player is null.
     */
    public void execute(Player player) throws IllegalActionException { 
        if (player == null) throw new IllegalActionException("Player cannot be null.");
        player.addGold(gold);
    }
}