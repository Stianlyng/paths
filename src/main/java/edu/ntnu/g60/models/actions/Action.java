package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.models.Player;

@FunctionalInterface
public interface Action {
    public void execute(Player player);
}