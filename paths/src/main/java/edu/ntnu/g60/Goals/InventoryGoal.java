package edu.ntnu.g60.Goals;
import edu.ntnu.g60.Player;
import java.util.List;

public class InventoryGoal implements Goal{
    List<String> mandatoryItems;

    public InventoryGoal(List<String> mandatoryItems){
        this.mandatoryItems = mandatoryItems;
    }

    public boolean isFulfilled(Player player){
        boolean fulfilled = false;
        for (String item : mandatoryItems){
            if(player.getInventory().contains(item)){
                fulfilled = true;
            }else{
                fulfilled = false;
            }
        }
        return fulfilled;
    }
}

