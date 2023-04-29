package edu.ntnu.g60.views.GamePanes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import edu.ntnu.g60.controllers.FightPaneController;
import edu.ntnu.g60.controllers.GameController;
import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Link;
import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.Player;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.utils.Save;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.ViewObjects;
import edu.ntnu.g60.views.Animations.LvlSwitchAnimation;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import edu.ntnu.g60.views.TitlePanes.DeathPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

//fix health amounts

public class FightPane extends StackPane{
    
    static ProgressBar playerBar;
    static ProgressBar enemyBar;
    public static FightPaneController controller;

    public FightPane() throws FileNotFoundException{
        FightPane.controller = new FightPaneController();
        addFightPaneObjects(FightPaneController.getCurrentFightPane());
        getChildren().addAll(addHealthBars());
    }

    public Group addHealthBars(){
        ProgressBar playerBar =  ViewObjects.newHealthBar(259-193, 306-71, (GameController.getCurrentGame().getPlayer().getHealth()/100), "progress_bar"); 
        ProgressBar enemyBar = ViewObjects.newHealthBar(789-193, 137-71, 1.00F, "progress_bar");
        return new Group(playerBar, enemyBar);
    }

    public static void updateHealthPlayer(float playerHealth){
        playerBar.setProgress(playerHealth);
    }

    public static void updateHealthEnemy(float enemyHealth){
        enemyBar.setProgress(enemyHealth);
    }

    public static void addFightPaneObjects(FightPane pane) throws FileNotFoundException{
        ImageView enemyImage = ViewObjects.newImage("characters", GameController.getCurrentPassage().getPlayer(), 50, 150, 150, 150);
        ImageView playerImage = ViewObjects.newImage("characters", GameController.getCurrentPassage().getEnemy(), 600, 150, 150, 150);
        ImageView backgroundImage = ViewObjects.newImage("backgrounds", GameController.getCurrentPassage().getBackground(), 0, 0, 1650, 1000);
        Button fightButton = ViewObjects.newButton("Fight", 209-193, 484-71, "fight_button", controller::fightAction);
        Button healButton = ViewObjects.newButton("Heal", 604-193, 484-71, "fight_button", controller::healAction);
        Button inventoryButton = ViewObjects.newButton("Inventory", 209-193, 575-71, "fight_button", controller::inventoryAction);
        Button escapeButton = ViewObjects.newButton("Escape", 604-193, 575-71, "fight_button", controller::escapeAction);
        ImageView coinIcon = ViewObjects.newImage("icons", "coin.png", 209-193, 86-71, 24, 24);
        ImageView scoreIcon = ViewObjects.newImage("icons", "star.png", 289-193, 86-71, 24, 24);
        Text scoreText = ViewObjects.newText("" + GameController.getCurrentGame().getPlayer().getScore(), 18, false, 242-193, 105-71);
        Text goldText = ViewObjects.newText("" + GameController.getCurrentGame().getPlayer().getGold(), 18, false, 322-193, 105-71);
        pane.getChildren().addAll( backgroundImage, enemyImage, playerImage, fightButton, healButton,
        inventoryButton, escapeButton, coinIcon, scoreIcon, scoreText, goldText);
    }

    public static void addFightObjects(FightPane pane) throws FileNotFoundException{
        Button abilityOneButton = ViewObjects.newButton("Insult", 209-193, 484-71, "fight_button", controller::abilityOneAction);
        Button abilityTwoButton = ViewObjects.newButton("Shame", 604-193, 484-71, "fight_button", controller::abilityTwoAction);
        Button abilityThreeButton = ViewObjects.newButton("Hit", 209-193, 575-71, "fight_button", controller::abilityThreeAction);
        Button backButton = ViewObjects.newButton("Back", 604-193, 575-71, "fight_button", controller::backAction);
        pane.getChildren().addAll(abilityOneButton, abilityTwoButton, abilityThreeButton, backButton);
    }

    public static void addHealObjects(FightPane pane) throws FileNotFoundException{
        Button healOneButton = ViewObjects.newButton("Lick wound", 209-193, 484-71, "fight_button", controller::healOneAction);
        Button healTwoButton = ViewObjects.newButton("Ignore pain", 604-193, 484-71, "fight_button", controller::healTwoAction);
        Button backButton = ViewObjects.newButton("Back", 604-193, 575-71, "fight_button", controller::backAction);
        Button emptyButton = ViewObjects.newButton("", 604-193, 575-71, "fight_button", controller::emptyAction);
        pane.getChildren().addAll(healOneButton, healTwoButton, backButton, emptyButton);
    }

    public static void addInventoryObjects(FightPane pane) throws FileNotFoundException{
        //TODO: add actuall inventory
        Button itemOneButton = ViewObjects.newButton("Item 1", 209-193, 484-71, "fight_button", controller::inventoryOneAction);
        Button itemTwoButton = ViewObjects.newButton("Item 2", 604-193, 484-71, "fight_button", controller::inventoryTwoAction);
        Button itemThreeButton = ViewObjects.newButton("Item 3", 209-193, 575-71, "fight_button", controller::inventoryThreeAction);
        Button backButton = ViewObjects.newButton("Back", 604-193, 575-71, "fight_button", controller::backAction);
        pane.getChildren().addAll(itemOneButton, itemTwoButton, itemThreeButton, backButton);
    }

    public static void addEscapeObjects(FightPane pane) throws FileNotFoundException{
        Button continueButton = ViewObjects.newButton("ESCAPE!", 209-193, 484-71, "fight_button", controller::escapeTwoAction);
        Button backButton = ViewObjects.newButton("Go back", 604-193, 484-71, "fight_button", controller::backAction);
        Button emptyOneButton = ViewObjects.newButton("", 209-193, 575-71, "fight_button", controller::emptyAction);
        Button emptyTwoButton = ViewObjects.newButton("", 604-193, 575-71, "fight_button", controller::emptyAction);
        pane.getChildren().addAll(continueButton, backButton, emptyOneButton, emptyTwoButton);
    }

}
