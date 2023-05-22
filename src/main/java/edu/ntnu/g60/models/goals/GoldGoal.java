package edu.ntnu.g60.models.goals;

import java.io.Serializable;

import edu.ntnu.g60.models.player.Player;

/**
 * This interface represents actions that can be performed on a player.
 *
 * @author Stian Lyng
 */
public class GoldGoal implements Goal, Serializable {

    /**
     * This holds the minimum gold required to fulfill the goal
     */
    int minimumGold;

    /**
     * Creates a new GoldGoal with the given minimum gold.
     *
     * @param minimumGold The minimum gold required to fulfill the goal.
     */
    public GoldGoal(int minimumGold) {
        this.minimumGold = minimumGold;
    }

    /**
     * Gets the minimum gold required to fulfill the goal.
     *
     * @return An integer representing the minimum gold required to fulfill the goal.
     */
    public boolean isFulfilled(Player player) {
        return player.getGold() >= this.minimumGold;
    }

    /**
     * Gets the minimum gold required to fulfill the goal.
     *
     * @return An integer representing the minimum gold required to fulfill the goal.
     */
    @Override
    public String toString() {
        return "GoldGoal: " + this.minimumGold;
    }

}
