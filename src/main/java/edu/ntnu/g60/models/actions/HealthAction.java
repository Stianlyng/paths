package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.models.Player;

public class HealthAction implements Action{
    int health;

    public HealthAction(int health){
        this.health = health;
    }

    public void execute(Player player){
        player.addHealth(health);
    }

}