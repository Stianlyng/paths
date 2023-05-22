package edu.ntnu.g60.models.goals;

import java.io.Serializable;

import edu.ntnu.g60.models.player.Player;

/**
 * This interface represents actions that can be performed on a player.
 * 
 * @author Stian Lyng
 */
public class ScoreGoal implements Goal, Serializable {
    
    /**
     * This holds the minimum points required to fulfill the goal
     */
    int minimumPoints;

    /**
     * Creates a new ScoreGoal with the given minimum points.
     * 
     * @param minimumPoints The minimum points required to fulfill the goal.
     */
    public ScoreGoal(int minimumPoints) {
        this.minimumPoints = minimumPoints;
    }

    /**
     * Gets the minimum points required to fulfill the goal.
     * 
     * @return An integer representing the minimum points required to fulfill the goal.
     */
    public boolean isFulfilled(Player player) {

        if (player.getScore() >= this.minimumPoints) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the minimum points required to fulfill the goal.
     * 
     * @return An integer representing the minimum points required to fulfill the goal.
     */
    @Override
    public String toString() {
        return "ScoreGoal: " + this.minimumPoints;
    }
}
