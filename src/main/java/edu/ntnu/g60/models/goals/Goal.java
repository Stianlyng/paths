package edu.ntnu.g60.models.goals;

import edu.ntnu.g60.models.Player;

@FunctionalInterface
public interface Goal{
  public boolean isFulfilled(Player player);
}




