package edu.ntnu.g60.views.GamePanes;

import java.io.FileNotFoundException;

import edu.ntnu.g60.TEMP_CURRENT_PASSAGE;
import edu.ntnu.g60.controllers.FightPaneController;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.views.ViewObjects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class FightPane extends StackPane{
    
    static ProgressBar playerBar;
    static ProgressBar enemyBar;
    public static FightPaneController controller;

    public FightPane() throws FileNotFoundException{
        FightPane.controller = new FightPaneController();
        getChildren().addAll(addFightPaneObjects());
    }

    public static void updateHealthPlayer(float playerHealth){
        playerBar.setProgress(playerHealth);
    }

    public static void updateHealthEnemy(float enemyHealth){
        enemyBar.setProgress(enemyHealth);
    }

    public static Group addFightPaneObjects() throws FileNotFoundException{
        ImageView enemyImage = ViewObjects.newImage("characters", TEMP_CURRENT_PASSAGE.getInstance().getPassage().getPlayer(), 150, 200, 150, 150);
        ImageView playerImage = ViewObjects.newImage("characters", TEMP_CURRENT_PASSAGE.getInstance().getPassage().getEnemy(), 700, 200, 150, 150);
        ImageView backgroundImage = ViewObjects.newImage("backgrounds", TEMP_CURRENT_PASSAGE.getInstance().getPassage().getBackground(), 0, 0, 1650, 1000);
        Button fightButton = ViewObjects.newButton("Fight", 309-193, 534-71, "fight_button", "fight_hover", controller::fightAction);
        Button healButton = ViewObjects.newButton("Heal", 704-193, 534-71, "heal_button", "heal_hover", controller::healAction);
        Button inventoryButton = ViewObjects.newButton("Inventory", 309-193, 625-71, "inventory_button", "inventory_hover", controller::inventoryAction);
        Button escapeButton = ViewObjects.newButton("Escape", 704-193, 625-71, "escape_button", "escape_hover", controller::escapeAction);
        ImageView coinIcon = ViewObjects.newImage("icons", "coin.png", 309-193, 136-71, 24, 24);
        ImageView scoreIcon = ViewObjects.newImage("icons", "star.png", 389-193, 136-71, 24, 24);
        Text scoreText = ViewObjects.newText("" + GameManager.getInstance().getGame().getPlayer().getScore(), 18, false, 342-193, 155-71);
        Text goldText = ViewObjects.newText("" + GameManager.getInstance().getGame().getPlayer().getGold(), 18, false, 422-193, 155-71);
        Rectangle infoBoard = ViewObjects.newRectangle(303-193, 129-71, 163, 38);
        MenuButton dropDown = ViewObjects.newMenuButton(controller::menuAction, controller::exitAction, "menu_button", "menu_hover", 283, 129-71, "Save and go to main menu", "Exit application");
        playerBar =  ViewObjects.newHealthBar(309-193, 504-71, 1.00F, "progress_bar"); 
        enemyBar = ViewObjects.newHealthBar(704-193, 504-71, 1.00F, "progress_bar");
        return new Group(backgroundImage, infoBoard, enemyImage, playerImage, fightButton, healButton,
        inventoryButton, escapeButton, coinIcon, scoreIcon, scoreText, goldText, playerBar, enemyBar, dropDown);
    }

    public static void addWinText(FightPane pane){
        Text winText = ViewObjects.newText("You won!", 50, false, 0, 0);
        pane.getChildren().addAll(winText);
    }

    public static void addLooseText(FightPane pane){
        Text looseText = ViewObjects.newText("You lost!", 50, false, 0, 0);
        pane.getChildren().addAll(looseText);
    }

    public static void addDefaultObjects(FightPane pane){
        Button fightButton = ViewObjects.newButton("Fight", 309-193, 534-71, "fight_button", "fight_hover", controller::fightAction);
        Button healButton = ViewObjects.newButton("Heal", 704-193, 534-71, "heal_button", "heal_hover",  controller::healAction);
        Button inventoryButton = ViewObjects.newButton("Inventory", 309-193, 625-71, "inventory_button", "inventory_hover", controller::inventoryAction);
        Button escapeButton = ViewObjects.newButton("Escape", 704-193, 625-71, "escape_button", "escape_hover", controller::escapeAction);
        StackPane.setAlignment(fightButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(fightButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(healButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(healButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(inventoryButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(inventoryButton, new Insets(50, 16, 18, 16)); 
        StackPane.setAlignment(escapeButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(escapeButton, new Insets(50, 16, 18, 16)); 
        pane.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
    }

    public static void addFightObjects(FightPane pane) throws FileNotFoundException{
        Button abilityOneButton = ViewObjects.newButton("Insult", 209-193, 484-71, "fight_button", "fight_hover", controller::abilityOneAction);
        Button abilityTwoButton = ViewObjects.newButton("Shame", 604-193, 484-71, "fight_button", "fight_hover", controller::abilityTwoAction);
        Button abilityThreeButton = ViewObjects.newButton("Hit", 209-193, 575-71, "fight_button", "fight_hover", controller::abilityThreeAction);
        Button backButton = ViewObjects.newButton("Back", 604-193, 575-71, "fight_button", "fight_hover", controller::backAction);
        StackPane.setAlignment(abilityOneButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(abilityOneButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(abilityTwoButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(abilityTwoButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(abilityThreeButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(abilityThreeButton, new Insets(50, 16, 18, 16)); 
        StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(backButton, new Insets(50, 16, 18, 16)); 
        pane.getChildren().addAll(abilityOneButton, abilityTwoButton, abilityThreeButton, backButton);
    }

    public static void addHealObjects(FightPane pane) throws FileNotFoundException{
        Button healOneButton = ViewObjects.newButton("Lick wound", 209-193, 484-71, "heal_button", "heal_hover", controller::healOneAction);
        Button healTwoButton = ViewObjects.newButton("Ignore pain", 604-193, 484-71, "heal_button", "heal_hover", controller::healTwoAction);
        Button backButton = ViewObjects.newButton("Back", 604-193, 575-71, "heal_button", "heal_hover", controller::backAction);
        Button emptyButton = ViewObjects.newButton("", 604-193, 575-71, "heal_button", "heal_hover", controller::emptyAction);
        StackPane.setAlignment(healOneButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(healOneButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(healTwoButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(healTwoButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(backButton, new Insets(50, 16, 18, 16)); 
        StackPane.setAlignment(emptyButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(emptyButton, new Insets(50, 16, 18, 16)); 
        pane.getChildren().addAll(healOneButton, healTwoButton, backButton, emptyButton);
    }

    public static void addInventoryObjects(FightPane pane) throws FileNotFoundException{
        //TODO: add actual inventory
        Button itemOneButton = ViewObjects.newButton("Item 1", 209-193, 484-71, "inventory_button", "inventory_hover", controller::inventoryOneAction);
        Button itemTwoButton = ViewObjects.newButton("Item 2", 604-193, 484-71, "inventory_button", "inventory_hover", controller::inventoryTwoAction);
        Button itemThreeButton = ViewObjects.newButton("Item 3", 209-193, 575-71, "inventory_button", "inventory_hover", controller::inventoryThreeAction);
        Button backButton = ViewObjects.newButton("Back", 604-193, 575-71, "inventory_button", "inventory_hover", controller::backAction);
        StackPane.setAlignment(itemOneButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(itemOneButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(itemTwoButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(itemTwoButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(itemThreeButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(itemThreeButton, new Insets(50, 16, 18, 16)); 
        StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(backButton, new Insets(50, 16, 18, 16)); 
        pane.getChildren().addAll(itemOneButton, itemTwoButton, itemThreeButton, backButton);
    }

    public static void addEscapeObjects(FightPane pane) throws FileNotFoundException{
        Button continueButton = ViewObjects.newButton("ESCAPE!", 209-193, 484-71, "escape_button", "escape_hover", controller::escapeTwoAction);
        Button backButton = ViewObjects.newButton("Go back", 604-193, 484-71, "escape_button", "escape_hover", controller::backAction);
        Button emptyOneButton = ViewObjects.newButton("", 209-193, 575-71, "escape_button", "escape_hover", controller::emptyAction);
        Button emptyTwoButton = ViewObjects.newButton("", 604-193, 575-71, "escape_button", "escape_hover", controller::emptyAction);
        StackPane.setAlignment(continueButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(continueButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(backButton, new Insets(50, 16, 109, 16)); 
        StackPane.setAlignment(emptyOneButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(emptyOneButton, new Insets(50, 16, 18, 16)); 
        StackPane.setAlignment(emptyTwoButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(emptyTwoButton, new Insets(50, 16, 18, 16)); 
        pane.getChildren().addAll(continueButton, backButton, emptyOneButton, emptyTwoButton);
    }

}
