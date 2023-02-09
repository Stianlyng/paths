package edu.ntnu.g60.Goals;
import edu.ntnu.g60.Player;
import java.util.List;

public class InventoryGoal implements Goal{
    List<String> mandatoryItems;

    InventoryGoal(List<String> mandatoryItems){
        this.mandatoryItems = mandatoryItems;
    }

    public boolean isFulfilled(Player player){
        
        for (String item : mandatoryItems){
            if(player.getInventory().contains(item)){
                return true;
            }else{
                return false;
            }
        }
    }
}

//for each greia er litt usikker
