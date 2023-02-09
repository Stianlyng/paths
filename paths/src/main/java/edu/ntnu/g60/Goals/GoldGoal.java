package edu.ntnu.g60.Goals;

import edu.ntnu.g60.Player;



public class GoldGoal implements Goal{
    
    int minimumGold;
    
    public GoldGoal(int minimumGold){
        this.minimumGold = minimumGold;
    }
    
    public boolean isFulfilled(Player player){
        if(player.getGold() >= this.minimumGold){
            return true;
        }else{
            return false;
        }
    }
    
}


  


