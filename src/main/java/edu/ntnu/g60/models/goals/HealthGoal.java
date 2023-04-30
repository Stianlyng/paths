package edu.ntnu.g60.models.goals;

import edu.ntnu.g60.models.player.Player;

public class HealthGoal implements Goal{
    int minimumHealth;

    public HealthGoal(int minimumHealth){
        this.minimumHealth = minimumHealth;
    }

    public boolean isFulfilled(Player player){
        if(player.getHealth() >= this.minimumHealth ){
            return true;
        }else{
            return false;
        }
    }
}

