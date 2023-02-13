package edu.ntnu.g60.Actions;

import edu.ntnu.g60.Player;

@FunctionalInterface
interface Action {
    public void execute(Player player);
}



