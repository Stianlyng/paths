package edu.ntnu.g60.models.goals;

import java.io.Serializable;

import edu.ntnu.g60.models.player.Player;
/**
 * This interface represents actions that can be performed on a player.
 * 
 * @author Stian Lyng
 */
public class HealthGoal implements Goal, Serializable{
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
    
    @Override
    public String toString(){
        return "HealthGoal: " + this.minimumHealth;
    }
    
}

