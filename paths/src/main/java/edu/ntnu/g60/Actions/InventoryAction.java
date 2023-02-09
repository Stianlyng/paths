package edu.ntnu.g60.Actions;

import edu.ntnu.g60.Player;
import java.util.List;


public class InventoryAction implements Action{
    List<String> inventory;

    public InventoryAction(List<String>  inventory){
        this.inventory = inventory;
    }

    public void execute(Player player){
        
    }

}