package edu.ntnu.g60.models.goals;

import java.io.Serializable;

import edu.ntnu.g60.models.player.Player;



public class GoldGoal implements Goal, Serializable{
    
    int minimumGold;
    
    public GoldGoal(int minimumGold){
        this.minimumGold = minimumGold;
    }
    
    public boolean isFulfilled(Player player){
        return player.getGold() >= this.minimumGold;
    }
    
}


  


