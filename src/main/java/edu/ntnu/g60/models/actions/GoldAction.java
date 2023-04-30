package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.models.player.Player;

public class GoldAction implements Action{
    int gold;

    public GoldAction(int gold){
        this.gold = gold;
    }

    public void execute(Player player){
        player.addGold(gold);
    }
}