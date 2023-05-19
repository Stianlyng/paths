package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageManager;
import edu.ntnu.g60.utils.FrontendUtils;
import edu.ntnu.g60.utils.SaveFileHandler;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.Animations.DeathAnimation;
import edu.ntnu.g60.views.Animations.EndGameAnimation;
import edu.ntnu.g60.views.Animations.NextLevelAnimation;
import edu.ntnu.g60.views.Animations.WinAnimation;
import edu.ntnu.g60.views.GamePanes.FightPane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import javafx.event.ActionEvent;

/**
 * The FightPaneController class is responsible for handling the actions and logic related to the fight pane in the game.
 * It provides methods for managing the fight actions, updating health values, and controlling the flow of the fight.
 */
public class FightPaneController {
    
    /**
    * The current health value of the enemy.
    */
    public static float enemyHealth;

    /**
    * The current health value of the player.
    * Independent from a Player objects health.
    */
    public static float playerHealth;

    /**
    * The currently active fight pane.
    */
    public static FightPane currentFightPane;

    /**
    * Sets the default health values for the enemy and player.
    */
    public static void setDefaultHealthValues(){
        enemyHealth = 1.00F;
        playerHealth = 1.00F;
    }

    /**
    * Retrieves the currently active fight pane.
    *
    * @return The current FightPane object.
    */
    public static FightPane getCurrentFightPane(){
        return currentFightPane;
    }

    /**
    * Sets the currently active fight pane.
    *
    * @param pane The FightPane object to set as the current fight pane.
    */
    public static void setCurrentFightPane(FightPane pane){
        currentFightPane = pane;
    }
    
    /**
    * Handles the exit action event by closing the game application.
    *
    * @param event The ActionEvent object representing the exit action.
    */
    public void exitAction(ActionEvent event){
        GameApp.closeApplication();
    }

    /**
    * Handles the menu action event by saving the game and changing the root pane to the opening pane.
    *
    * @param event The ActionEvent object representing the menu action.
    */
    public void menuAction(ActionEvent event){
        try {
            SaveFileHandler.saveGameToFile(GameManager.getInstance().getGame(), GameManager.getInstance().getGame().getGameName(), PassageManager.getInstance().getPassage().getTitle());
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Handles the fight action event by adding fight objects to the current fight pane.
    *
    * @param event The ActionEvent object representing the fight action.
    */
    public void fightAction(ActionEvent event){
        try {
            FightPane.addFightObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
    * Handles the heal action event by adding heal objects to the current fight pane.
    *
    * @param event The ActionEvent object representing the heal action.
    */
    public void healAction(ActionEvent event){
        try {
            FightPane.addHealObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
    * Handles the inventory action event by adding inventory objects to the current fight pane.
    *
    * @param event The ActionEvent object representing the inventory action.
    */
    public void inventoryAction(ActionEvent event){
        try {
            FightPane.addInventoryObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs the escape action.
     *
     * @param event the action event
     */
    public void escapeAction(ActionEvent event){
        try {
            FightPane.addEscapeObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs the first ability action.
     *
     * @param event the action event
     */
    public void abilityOneAction(ActionEvent event){
        playerAction(0.15F, 0.0F);
        enemyAction(0.4F, 0.0F);
    }

    /**
     * Performs the second ability action.
     *
     * @param event the action event
     */
    public void abilityTwoAction(ActionEvent event){
        playerAction(0.1F, 0.0F);
        enemyAction(0.2F, 0.0F);
    }

    /**
     * Performs the third ability action.
     *
     * @param event the action event
     */    
    public void abilityThreeAction(ActionEvent event){
        playerAction(0.65F, 0.0F);
        enemyAction(0.25F, 0.0F);
    }

    /**
     * Performs the first healing action.
     *
     * @param event the action event
     */
    public void healOneAction(ActionEvent event){
        playerAction(0.0F, 0.01F);
        enemyAction(0.2F, 0.0F);
    }

    /**
     * Performs the second healing action.
     *
     * @param event the action event
     */
    public void healTwoAction(ActionEvent event){
        playerAction(0.0F, (1.0F - playerHealth));
        enemyAction(0.1F, 0.0F);
    }

    public void inventoryOneAction(ActionEvent event){
        //TODO: ADD functionality
        //remove item from inventory
        //do something in fight
    }

    public void inventoryTwoAction(ActionEvent event){
        //TODO: ADD functionality
    }

    public void inventoryThreeAction(ActionEvent event){
        //TODO: ADD functionality
    }

    /**
     * Performs the second escape action.
     *
     * @param event the action event
     */
    public void escapeTwoAction(ActionEvent event){
        try {
            looseFight();
        } catch (MalformedURLException | FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Performs an empty action.
     *
     * @param event the action event
     */
    public void emptyAction(ActionEvent event){}


    /**
     * Performs the back action.
     *
     * @param event the action event
     */
    public void backAction(ActionEvent event){
        FightPane.addDefaultObjects(getCurrentFightPane());
    }

    /**
     * Handles the win condition of a fight.
     *
     * @throws MalformedURLException if a malformed URL is encountered
     * @throws FileNotFoundException if the file is not found
     */
    public static void winFight() throws MalformedURLException, FileNotFoundException{
        Link link2 = PassageManager.getInstance().getPassage().getLinks().get(1);
        for (Action action : link2.getActions()) {
            action.execute(GameManager.getInstance().getGame().getPlayer());
        }
        boolean goalsFulfilled = GameManager.getInstance().getGame().getGoals().stream().allMatch(goal -> goal.isFulfilled(GameManager.getInstance().getGame().getPlayer()));
        if(goalsFulfilled){
            WinAnimation.animation();
        } else if(link2.getReference().equalsIgnoreCase("game over")){
            DeathAnimation.animation();
        } else if (link2.getReference().equalsIgnoreCase("end game")){
            EndGameAnimation.animation();
        } else { 
            
            Passage currentPassage = GameManager.getInstance().getGame().go(link2);
            PassageManager.getInstance().setPassage(currentPassage); 

            NextLevelAnimation.animation();
            SaveFileHandler.saveGameToFile(GameManager.getInstance().getGame(), GameManager.getInstance().getGame().getGameName(), PassageManager.getInstance().getPassage().getTitle());
        }
    }
    
    /**
     * Handles the loose condition of a fight.
     *
     * @throws MalformedURLException if a malformed URL is encountered
     * @throws FileNotFoundException if the file is not found
     */
    public static void looseFight() throws MalformedURLException, FileNotFoundException{
        Link link1 = PassageManager.getInstance().getPassage().getLinks().get(0);
        for (Action action : link1.getActions()) {
            action.execute(GameManager.getInstance().getGame().getPlayer());
        }
        boolean goalsFulfilled = GameManager.getInstance().getGame().getGoals().stream().allMatch(goal -> goal.isFulfilled(GameManager.getInstance().getGame().getPlayer()));
        if(goalsFulfilled){
            WinAnimation.animation();
        } else if(link1.getReference().equalsIgnoreCase("game over")){
            DeathAnimation.animation();
        } else if (link1.getReference().equalsIgnoreCase("end game")){
            EndGameAnimation.animation();
        } else { 
            Passage currentPassage = GameManager.getInstance().getGame().go(link1);
            PassageManager.getInstance().setPassage(currentPassage); 

            NextLevelAnimation.animation();
            SaveFileHandler.saveGameToFile(GameManager.getInstance().getGame(), GameManager.getInstance().getGame().getGameName(), PassageManager.getInstance().getPassage().getTitle());
        }
    }

    /**
     * Performs the action of the enemy attacking the player and the player attacking the enemy.
     *
     * @param damageAmount the amount of damage inflicted by the enemy
     * @param healAmount the amount of health healed by the enemy
     */
    public static void enemyAction(float damageAmount, float healAmount){
        FrontendUtils.delay(2000, () -> {
            playerHealth = playerHealth - damageAmount;
            enemyHealth = enemyHealth + healAmount;
            FightPane.updateHealthEnemy(enemyHealth);
            FightPane.updateHealthPlayer(playerHealth);
            if(enemyHealth < 0.00){
                FightPane.updateHealthEnemy(0.00F);
                FrontendUtils.delay(1000, () -> {
                    FightPane.addWinText(getCurrentFightPane());
                    FrontendUtils.delay(3000, () -> {
                        try {
                            winFight();
                        } catch (MalformedURLException | FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                }); 
                
            } else if(playerHealth < 0.00){
                FightPane.updateHealthPlayer(0.00F);
                FrontendUtils.delay(1000, () -> {
                    FightPane.addLooseText(getCurrentFightPane());
                    FrontendUtils.delay(3000, () -> {
                        try {
                            looseFight();
                        } catch (MalformedURLException | FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                });
            }   
        });
    }
 
    /**
     * Performs the action of the player attacking the enemy and healing the player.
     *
     * @param damageAmount the amount of damage inflicted by the player
     * @param healAmount the amount of health healed by the player
     */
    public static void playerAction(float damageAmount, float healAmount){
        enemyHealth = enemyHealth - damageAmount;
        playerHealth = playerHealth + healAmount;
        FightPane.updateHealthEnemy(enemyHealth);
        FightPane.updateHealthPlayer(playerHealth);
        if(enemyHealth < 0.00){
            FightPane.updateHealthEnemy(0.00F);
        } else if(playerHealth < 0.00){
            FightPane.updateHealthPlayer(0.00F);
        }  
    }

    public static String[] getPlayerInventoryItems(){
        List<String> inventoryItems = GameManager.getInstance().getGame().getPlayer().getInventory();
        String[] result = new String[3];
    
        for (int i = 0; i < 3; i++) {
            if (i < inventoryItems.size()) {
                result[i] = inventoryItems.get(i);
            } else {
                result[i] = "Missing item";
            }
        }
    
        return result;
    }
}
