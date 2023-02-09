
public class InventoryGoal implements Goal{
    List<String> mandatoryItems;

    InventoryGoal(List<String> mandatoryItems){
        this.mandatoryItems = mandatoryItems;
    }

    public boolean isFulfilled(Player player){
        
        for (int item : mandatoryItems){
            if(player.getInventory.contains(item)){
                return true;
            }else{
                return false;
            }
        }
    }
}

//for each greia er litt usikker
