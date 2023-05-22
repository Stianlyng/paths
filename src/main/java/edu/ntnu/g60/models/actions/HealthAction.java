package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.exceptions.model.actions.IllegalActionException;
import edu.ntnu.g60.models.player.Player;
import java.io.Serializable;

/**
 * This interface represents actions that can be performed on a player.
 *
 * @author Stian Lyng
 */
public class HealthAction implements Action, Serializable {

  int health;

  /**
   * Creates a new HealthAction.
   *
   * @param health The health to add to the player.
   */
  public HealthAction(int health) {
    this.health = health;
  }

  /**
   * Executes the action on the player.
   *
   * @param player The player to execute the action on.
   * @throws IllegalActionException if the player is null.
   */
  public void execute(Player player) throws IllegalActionException {
    if (player == null) throw new IllegalActionException("Player cannot be null.");
    player.addHealth(health);
  }
}
