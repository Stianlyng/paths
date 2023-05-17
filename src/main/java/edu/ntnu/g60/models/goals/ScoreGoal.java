package edu.ntnu.g60.models.goals;

import java.io.Serializable;

import edu.ntnu.g60.models.player.Player;

/**
 * This interface represents actions that can be performed on a player.
 * 
 * @author Stian Lyng
 */
public class ScoreGoal implements Goal, Serializable {
    int minimumPoints;

    public ScoreGoal(int minimumPoints) {
        this.minimumPoints = minimumPoints;
    }

    public boolean isFulfilled(Player player) {

        if (player.getScore() >= this.minimumPoints) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "ScoreGoal: " + this.minimumPoints;
    }
}
