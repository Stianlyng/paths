package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.Save;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.views.Animations.DeathAnimation;
import edu.ntnu.g60.views.Animations.LvlSwitchAnimation;
import edu.ntnu.g60.views.GamePanes.FightPane;
import javafx.event.ActionEvent;

public class FightPaneController {
    
    public static float enemyHealth;
    public static float playerHealth;
    public static FightPane currentFightPane;

    public static void setDefaultHealthValues(){
        enemyHealth = 1.00F;
        playerHealth = 1.00F;
    }

    public static String printHealthAmounts(){
        return "player: " + playerHealth + "\n" +
        "enemy: " + enemyHealth;
    }

    public static FightPane getCurrentFightPane(){
        return currentFightPane;
    }

    public static void setCurrentFightPane(FightPane pane){
        currentFightPane = pane;
    }
    
    public void fightAction(ActionEvent event){
        try {
            FightPane.addFightObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void healAction(ActionEvent event){
        try {
            FightPane.addHealObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void inventoryAction(ActionEvent event){
        try {
            FightPane.addInventoryObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void escapeAction(ActionEvent event){
        try {
            FightPane.addEscapeObjects(getCurrentFightPane());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void abilityOneAction(ActionEvent event){
        playerAction(0.15F, 0.0F);
        enemyAction(0.4F, 0.0F);
    }

    public void abilityTwoAction(ActionEvent event){
        playerAction(0.1F, 0.0F);
        enemyAction(0.2F, 0.0F);
    }

    public void abilityThreeAction(ActionEvent event){
        playerAction(0.65F, 0.0F);
        enemyAction(0.25F, 0.0F);
    }

    public void healOneAction(ActionEvent event){
        playerAction(0.0F, 0.01F);
        enemyAction(0.2F, 0.0F);
    }

    public void healTwoAction(ActionEvent event){
        playerAction(0.0F, (1.0F - playerHealth));
        enemyAction(0.1F, 0.0F);
    }

    public void inventoryOneAction(ActionEvent event){
        //TODO: ADD functionality
    }

    public void inventoryTwoAction(ActionEvent event){
        //TODO: ADD functionality
    }

    public void inventoryThreeAction(ActionEvent event){
        //TODO: ADD functionality
    }

    public void escapeTwoAction(ActionEvent event){
        try {
            looseFight();
        } catch (MalformedURLException | FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void emptyAction(ActionEvent event){}


    public void backAction(ActionEvent event){
        FightPane.addDefaultObjects(getCurrentFightPane());
    }

    public static void winFight() throws MalformedURLException, FileNotFoundException{
        Link link2 = GameController.getCurrentPassage().getLinks().get(1);
        if(link2.getReference().equals("game over")){
            DeathAnimation.animation();
        } else { 
            GameController.setCurrentGame(GameController.getCurrentGame());
            GameController.setCurrentPassage(GameController.getCurrentGame().go(link2));
            LvlSwitchAnimation.animation();
            try {
                SaveRegister.setSave(new Save(GameController.getCurrentGame().go(link2), Story.getCurrentSave().getSaveName(),
                Story.getCurrentSave().getSaveNumber()), Story.getCurrentSave().getSaveNumber());
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void looseFight() throws MalformedURLException, FileNotFoundException{
        Link link1 = GameController.getCurrentPassage().getLinks().get(0);
        if(link1.getReference().equals("game over")){
            DeathAnimation.animation();
        } else { 
            GameController.setCurrentGame(GameController.getCurrentGame());
            GameController.setCurrentPassage(GameController.getCurrentGame().go(link1));
            LvlSwitchAnimation.animation();
            try {
                SaveRegister.setSave(new Save(GameController.getCurrentGame().go(link1), Story.getCurrentSave().getSaveName(),
                Story.getCurrentSave().getSaveNumber()), Story.getCurrentSave().getSaveNumber());
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void enemyAction(float damageAmount, float healAmount){
        GameController.delay(2000, () -> {
            System.out.println(printHealthAmounts());
            playerHealth = playerHealth - damageAmount;
            enemyHealth = enemyHealth + healAmount;
            System.out.println(printHealthAmounts());
            FightPane.updateHealthEnemy(enemyHealth);
            FightPane.updateHealthPlayer(playerHealth);
            if(enemyHealth < 0.00){
                try {
                    winFight();
                    //add animation for win. 
                } catch (MalformedURLException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if(playerHealth < 0.00){
                try {
                    looseFight();
                    //add animation for loosing.
                } catch (MalformedURLException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }   
        });
    }

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
}
