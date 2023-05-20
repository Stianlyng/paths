package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.utils.SaveFileHandler;
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
 */
public class MiniGameController {
    
    public static Game game = GameManager.getInstance().getGame();
    private static Passage passage;
    public static float enemyHealth;
    public static float playerHealth;
    public static MiniGamePane currentFightPane;
    
    public MiniGameController(Passage passage){
        this.passage = passage;
    }

    public static void setDefaultHealthValues(){
        enemyHealth = 1.00F;
        playerHealth = 1.00F;
    }


    public static MiniGamePane getCurrentFightPane(){
        return currentFightPane;
    }

    public static void setCurrentFightPane(MiniGamePane pane){
        currentFightPane = pane;
    }
    

    public void exitAction(ActionEvent event){
        GameApp.closeApplication();
    }


    public void goToMenuAndSaveAction(ActionEvent event){
        GameManager.getInstance().endGame();
        saveGame(DialogBoxes.dialogBoxWithTextInput("Save Game", "Enter a name for your save file", "Name:"));
        try {
            GameApp.changeRootPane(new MainMenuPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //TODO: kanskje gjøre om savefilehaandler til boolean for å indikere sukse
    private void saveGame(String saveName) {
        Game game = GameManager.getInstance().getGame();
        try {
            SaveFileHandler.saveGameToFile(game, saveName, passage.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fightAction(ActionEvent event){
        try {
            MiniGamePane.addFightObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void healAction(ActionEvent event){
        try {
            MiniGamePane.addHealObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void inventoryAction(ActionEvent event){
        try {
            MiniGamePane.addInventoryObjects(getCurrentFightPane());
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
            MiniGamePane.addEscapeObjects(getCurrentFightPane());
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
        //remove item from inventory
        //do something in fight
    }

    public void inventoryThreeAction(ActionEvent event){
        //TODO: ADD functionality
        //remove item from inventory
        //do something in fight
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
        MiniGamePane.addDefaultObjects(getCurrentFightPane());
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
            Passage currentPassage = GameManager.getInstance().getGame().go(link);
            NextLevelAnimation.animation(currentPassage);
        }
    } 

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

    public static void enemyAction(float damageAmount, float healAmount) {
        FrontendUtils.delay(1500, () -> {
            applyDamageAndHealingEnemy(damageAmount, healAmount);
            MiniGamePane.updateHealthEnemy(enemyHealth);
            MiniGamePane.updateHealthPlayer(playerHealth);
    
            if (enemyHealth < 0.00) {
                MiniGamePane.updateHealthEnemy(0.00F);
                FrontendUtils.delay(1000, () -> {
                    MiniGamePane.addWinText(getCurrentFightPane());
                    handleWinFight();
                });
            } else if (playerHealth < 0.00) {
                MiniGamePane.updateHealthPlayer(0.00F);
                FrontendUtils.delay(1000, () -> {
                    MiniGamePane.addLooseText(getCurrentFightPane());
                    handleLooseFight();
                });
            }
        });
    }

    private static void applyDamageAndHealingEnemy(float damageAmount, float healAmount) {
        playerHealth -= damageAmount;
        enemyHealth += healAmount;
    }

    private static void applyDamageAndHealingPlayer(float damageAmount, float healAmount) {
        enemyHealth -= damageAmount;
        playerHealth += healAmount;
    }

    private static void handleWinFight() {
        FrontendUtils.delay(1500, () -> {
            try {
                winFight();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    private static void handleLooseFight() {
        FrontendUtils.delay(1500, () -> {
            try {
                looseFight();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
