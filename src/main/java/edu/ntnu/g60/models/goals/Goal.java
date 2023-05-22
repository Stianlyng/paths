package edu.ntnu.g60.models.goals;

import edu.ntnu.g60.models.player.Player;

/**
 * This interface represents actions that can be performed on a player.
 *
 * @author Stian Lyng
 */
public interface Goal {
  public boolean isFulfilled(Player player);
}
