package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import edu.ntnu.g60.exceptions.InvalidLinkException;
import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.utils.fileHandling.SaveFileHandler;
import edu.ntnu.g60.utils.frontend.FrontendUtils;
import edu.ntnu.g60.views.DialogBoxes;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.Animations.DeathAnimation;
import edu.ntnu.g60.views.Animations.EndGameAnimation;
import edu.ntnu.g60.views.Animations.NextLevelAnimation;
import edu.ntnu.g60.views.Animations.WinAnimation;
import edu.ntnu.g60.views.GamePanes.MiniGamePane;
import edu.ntnu.g60.views.StartMenu.MainMenuPane;
import javafx.event.ActionEvent;

/**
 * The FightPaneController class is responsible for handling the actions and logic related to the fight pane in the game.
 * It provides methods for managing the fight actions, updating health values, and controlling the flow of the fight.
 * @author olav sie
 */
public class MiniGameController {
    
    public static Game game = GameManager.getInstance().getGame();
    private static Passage passage;
    public static float enemyHealth;
    public static float playerHealth;
    public static MiniGamePane currentMiniGamePane;
    

    /**
     * Creates a MiniGameController object
     * 
     * @param passage current passage of the ongoing minigame
     */
    public MiniGameController(Passage passage){
        this.passage = passage;
    }

    /**
     * Sets the default health values of both player and enemies to be 1
     */
    public static void setDefaultHealthValues(){
        enemyHealth = 1.00F;
        playerHealth = 1.00F;
    }

    /**
     * Sets the current fightPane showing
     * 
     * @param current fightPane showing
     */
    public static void setCurrentMiniGamePane(MiniGamePane pane){
        currentMiniGamePane = pane;
    }
    
    /**
     * Handles the action when the exit application button is clicked.
     *
     * @param event the ActionEvent representing the button click event
     */
    public void exitApplicationAction(ActionEvent event){
        GameApp.closeApplication();
    }

    /**
     * Handles the action when the go to menu and save button is clicked.
     *
     * @param event the ActionEvent representing the button click event
     */
    public void goToMenuAndSaveAction(ActionEvent event){
        saveGame(DialogBoxes.dialogBoxWithTextInput("Save Game", "Enter a name for your save file", "Name:"));
        try {
            GameManager.getInstance().endGame();
            GameApp.changeRootPane(new MainMenuPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Saves the game with the specified save name.
     *
     * @param saveName the name of the save file
     */
    private void saveGame(String saveName) {
        Game game = GameManager.getInstance().getGame();
        try {
            SaveFileHandler.saveGameToFile(game, saveName, passage.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs the fight action.
     *
     * @param event the action event
     */
    public void fightAction(ActionEvent event){
        try {
            MiniGamePane.addFightObjects(currentMiniGamePane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs the heal action.
     *
     * @param event the action event
     */
    public void healAction(ActionEvent event){
        try {
            MiniGamePane.addHealObjects(currentMiniGamePane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs the inventory action.
     *
     * @param event the action event
     */
    public void inventoryAction(ActionEvent event){
        try {
            MiniGamePane.addInventoryObjects(currentMiniGamePane);
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
            MiniGamePane.addEscapeObjects(currentMiniGamePane);
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

    /**
     * Performs the inventory action for the first item slot.
     * 
     * @param event The ActionEvent associated with the action.
    */
    public void inventoryOneAction(ActionEvent event) {
        inventoryAction(0);
    }

    /**
     * Performs the inventory action for the second item slot.
     * 
     * @param event The ActionEvent associated with the action.
    */
    public void inventoryTwoAction(ActionEvent event){
        inventoryAction(1);
    }

    /**
     * Performs the inventory action for the third item slot.
     * 
     * @param event The ActionEvent associated with the action.
    */
    public void inventoryThreeAction(ActionEvent event){
        inventoryAction(2);
    }

    /**
     * Performs the inventory action for the specified item slot.
     * 
     * @param itemNumber the number of the item slot.
     */
    public void inventoryAction(int itemNumber){
        List<String> playerInventory = game.getPlayer().getInventory();
        if (playerInventory.size() > itemNumber) {
            String[] playerInventoryItems = getPlayerInventoryItems();
            String itemToBeRemoved = playerInventoryItems[itemNumber];
            if (!itemToBeRemoved.equals("Missing item")) {
                game.getPlayer().removeFromInventory(itemToBeRemoved);
                try {
                    MiniGamePane.addInventoryObjects(currentMiniGamePane);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                playerAction(0.9F, 0.9F);
                enemyAction(0.0F, 0.0F);
            }
        }
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
        MiniGamePane.addDefaultObjects(currentMiniGamePane);
    }

    /**
     * Handles the win condition of a fight.
     *
     * @throws MalformedURLException if a malformed URL is encountered
     * @throws FileNotFoundException if the file is not found
     */
    public static void winFight() throws MalformedURLException, FileNotFoundException{
        fightOutcome(passage.getLinks().get(1));
    }
    
    /**
     * Handles the loose condition of a fight.
     *
     * @throws MalformedURLException if a malformed URL is encountered
     * @throws FileNotFoundException if the file is not found
     */
    public static void looseFight() throws MalformedURLException, FileNotFoundException{
        fightOutcome(passage.getLinks().get(0));
    }

    /**
     * Handels the outcome of a fight.
     * 
     * @param link The link to decide the outcome of the fight
     * @throws MalformedURLException if a malformed URL is encountered
     * @throws FileNotFoundException if the file is not found
     */
    public static void fightOutcome(Link link) throws FileNotFoundException, MalformedURLException{
        for (Action action : link.getActions()) {
            action.execute(GameManager.getInstance().getGame().getPlayer());
        }
        boolean goalsFulfilled = GameManager.getInstance().getGame().getGoals().stream().allMatch(goal -> goal.isFulfilled(GameManager.getInstance().getGame().getPlayer()));
        if(goalsFulfilled){
            WinAnimation.animation();
        } else if(link.getReference().equalsIgnoreCase("game over")){
            DeathAnimation.animation();
        } else if (link.getReference().equalsIgnoreCase("end game")){
            EndGameAnimation.animation();
        } else { 
            Passage currentPassage;
            try {
                currentPassage = GameManager.getInstance().getGame().go(link);
                NextLevelAnimation.animation(currentPassage);
            } catch (InvalidLinkException e) {
                e.getMessage();
            }
        }
    } 

    /**
     * Handles a player action in the minigame.
     * 
     * @param damageAmount The amount of damage a player should deal
     * @param healAmount The amound of healing a player should recieve
     */
    public static void playerAction(float damageAmount, float healAmount){
        applyDamageAndHealingPlayer(damageAmount, healAmount);
        MiniGamePane.updateHealthEnemy(enemyHealth);
        MiniGamePane.updateHealthPlayer(playerHealth);
        if(enemyHealth < 0.00){
            MiniGamePane.updateHealthEnemy(0.00F);
        } else if(playerHealth < 0.00){
            MiniGamePane.updateHealthPlayer(0.00F);
        }  
    }

    /**
     * Handles a enemy action in the minigame.
     * 
     * @param damageAmount The amount of damage a enemy should deal
     * @param healAmount The amound of healing a enemy should recieve
     */
    public static void enemyAction(float damageAmount, float healAmount) {
        FrontendUtils.delay(1500, () -> {
            applyDamageAndHealingEnemy(damageAmount, healAmount);
            MiniGamePane.updateHealthEnemy(enemyHealth);
            MiniGamePane.updateHealthPlayer(playerHealth);
    
            if (enemyHealth < 0.00) {
                MiniGamePane.updateHealthEnemy(0.00F);
                FrontendUtils.delay(1000, () -> {
                    MiniGamePane.addWinText(currentMiniGamePane);
                    handleWinFight();
                });
            } else if (playerHealth < 0.00) {
                MiniGamePane.updateHealthPlayer(0.00F);
                FrontendUtils.delay(1000, () -> {
                    MiniGamePane.addLooseText(currentMiniGamePane);
                    handleLooseFight();
                });
            }
        });
    }

    /**
     * Applies the amount of damage and healing specified in a enemyAction
     * 
     * @param damageAmount The amount of damage a enemy should deal
     * @param healAmount The amound of healing a enemy should recieve
     */
    private static void applyDamageAndHealingEnemy(float damageAmount, float healAmount) {
        playerHealth -= damageAmount;
        enemyHealth += healAmount;
    }

    /**
     * Applies the amount of damage and healing specified in a playerAction
     * 
     * @param damageAmount The amount of damage a enemy should deal
     * @param healAmount The amound of healing a enemy should recieve
     */
    private static void applyDamageAndHealingPlayer(float damageAmount, float healAmount) {
        enemyHealth -= damageAmount;
        playerHealth += healAmount;
    }

    /**
     * Handles what happens if a fight is won.
     */
    private static void handleWinFight() {
        FrontendUtils.delay(1500, () -> {
            try {
                winFight();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    /**
     * Handles what happens if a fight is lost
     */
    private static void handleLooseFight() {
        FrontendUtils.delay(1500, () -> {
            try {
                looseFight();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * Retrieves the player's three first inventory items in their inventory list.
     * This is to make them fit in the three buttons in the minigame
     *
     * @return an array of three inventory items.
     */
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
