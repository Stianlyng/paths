package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.models.Player;


public class InventoryAction implements Action{
    String inventory;

    public InventoryAction(String  inventory){
        this.inventory = inventory;
    }

    public void execute(Player player){
        player.addToInventory(inventory);
    }

}