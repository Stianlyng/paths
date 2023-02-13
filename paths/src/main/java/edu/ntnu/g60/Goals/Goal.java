package edu.ntnu.g60.Goals;

import edu.ntnu.g60.Player;

@FunctionalInterface
interface Goal{
  public boolean isFulfilled(Player player);
}




