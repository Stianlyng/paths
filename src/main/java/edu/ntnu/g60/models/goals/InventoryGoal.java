package edu.ntnu.g60.models.goals;
import java.util.List;

import edu.ntnu.g60.models.player.Player;

public class InventoryGoal implements Goal{
    List<String> mandatoryItems;

    public InventoryGoal(List<String> mandatoryItems){
        this.mandatoryItems = mandatoryItems;
    }


    public boolean isFulfilled(Player player){ 
        for (String item : mandatoryItems){
            if(!player.getInventory().contains(item)){
                return false;
            }
        }
        return true;
    }
}

