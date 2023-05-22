package edu.ntnu.g60.models.goals;

import edu.ntnu.g60.models.player.Player;
import java.io.Serializable;

/**
 * This interface represents actions that can be performed on a player.
 *
 * @author Stian Lyng
 */
public class HealthGoal implements Goal, Serializable {

  /**
   * This holds the minimum health required to fulfill the goal
   */
  int minimumHealth;

  /**
   * Creates a new HealthGoal with the given minimum health.
   *
   * @param minimumHealth The minimum health required to fulfill the goal.
   */
  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  /**
   * Gets the minimum health required to fulfill the goal.
   *
   * @return An integer representing the minimum health required to fulfill the goal.
   */
  public boolean isFulfilled(Player player) {
    if (player.getHealth() >= this.minimumHealth) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the minimum health required to fulfill the goal.
   *
   * @return An integer representing the minimum health required to fulfill the goal.
   */
  @Override
  public String toString() {
    return "HealthGoal: " + this.minimumHealth;
  }
}
