package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import edu.ntnu.g60.models.Link;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.utils.Save;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.views.Animations.DeathAnimation;
import edu.ntnu.g60.views.Animations.LvlSwitchAnimation;
import edu.ntnu.g60.views.GamePanes.FightPane;
import javafx.event.ActionEvent;

public class FightPaneController {
    
    static float enemyHealth;
    static float playerHealth;
    static FightPane currentFightPane;

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
        playerAction(0.15F, 0);
        enemyAction(0.4F, 0);
    }

    public void abilityTwoAction(ActionEvent event){
        playerAction(0.1F, 0);
        enemyAction(0.2F, 0);
    }

    public void abilityThreeAction(ActionEvent event){
        playerAction(0.65F, 0);
        enemyAction(0.25F, 0);
    }

    public void healOneAction(ActionEvent event){
        playerAction(0.0F, 0.01F);
        enemyAction(0.2F, 0);
    }

    public void healTwoAction(ActionEvent event){
        playerAction(0.0F, (1.0F - playerHealth));
        enemyAction(0.1F, 0);
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
        try {
            FightPane.addFightPaneObjects();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    
    //TODO: move death to animations
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

    //todo: implenment actions
    public static void enemyAction(float damageAmount, float healAmount){
        GameController.delay(2000, () -> {
            playerHealth = playerHealth - damageAmount;
            enemyHealth = enemyHealth + healAmount;
            FightPane.updateHealthEnemy(enemyHealth);
            FightPane.updateHealthPlayer(playerHealth);
            if(enemyHealth < 0.00F){
                try {
                    winFight();
                } catch (MalformedURLException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if(playerHealth < 0.00F){
                try {
                    looseFight();
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
        if(enemyHealth < 0.00F){
            try {
                winFight();
            } catch (MalformedURLException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(playerHealth < 0.00F){
            try {
                looseFight();
            } catch (MalformedURLException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
