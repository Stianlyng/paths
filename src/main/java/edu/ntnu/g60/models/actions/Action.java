package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.models.player.Player;

/**
 * This interface represents actions that can be performed on a player.
 *
 * @author Stian Lyng
 */
public interface Action {
  /**
   * Executes the action on the player.
   *
   * @param player
   */
  public void execute(Player player);
}
