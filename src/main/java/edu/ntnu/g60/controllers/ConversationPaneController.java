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
import edu.ntnu.g60.views.Animations.*;
import edu.ntnu.g60.views.GamePanes.ConversationPane;
import edu.ntnu.g60.views.GamePanes.MiniGamePane;
import edu.ntnu.g60.views.StartMenu.MainMenuPane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 * The ConversationPaneController class is responsible for controlling the conversation pane in the game.
 * It handles user interactions and updates the conversation pane accordingly.
 */
public class ConversationPaneController {
    
    public static Game game = GameManager.getInstance().getGame();
    public static Passage passage;
    public static int conversationPaneNumber;
    public static ConversationPane currentConversationPane;
    boolean clickable;    

    /**
     * Constructs a new ConversationPaneController with the specified passage.
     *
     * @param passage the passage associated with the conversation pane
    */
    public ConversationPaneController(Passage passage){
        this.passage = passage;
        clickable = true;
    }

    /**
     * Retrieves the current conversation pane.
     *
     * @return the current conversation pane
     */
    public ConversationPane getCurrentConversationPane(){
        return currentConversationPane;
    }

    /**
     * Sets the current conversation pane.
     *
     * @param pane the conversation pane to set as current
     */
    public static void setCurrentConversationPane(ConversationPane pane){
        currentConversationPane = pane;
    }

    /**
     * Sets the conversation pane number.
     *
     * @param conversationPaneUpdatedNumber the updated conversation pane number
     */
    public static void setConversationPaneNumber(int conversationPaneUpdatedNumber){
        conversationPaneNumber = conversationPaneUpdatedNumber;
    }

    /**
     * Handles the action when the conversation pane is clicked.
     *
     * @param event the MouseEvent representing the click event
     */
    public void conversationPaneClickedAction(MouseEvent event){
        if(clickable){
            //TODO: change style of morelines left?
            boolean moreLinesLeft = (conversationPaneNumber + 1 == typesInCurrentPassage().length) ? false : true;
            setConversationPaneNumber(conversationPaneNumber + 1);
            try {
                SoundController.stopSound();
                if(moreLinesLeft){  
                    ConversationPane pane = new ConversationPane(passage);
                    ConversationPaneController.setCurrentConversationPane(pane);
                    GameApp.changeRootPane(pane);
                    SoundController.playSound("mumble");
                } else if (passage.hasFightScene()){
                    MiniGamePane pane = new MiniGamePane(passage);
                    MiniGameController.setDefaultHealthValues();
                    MiniGameController.setCurrentMiniGamePane(pane);
                    GameApp.changeRootPane(pane);
                } else {
                    clickable = false;
                    ConversationPane.addChoiceObjects(getCurrentConversationPane());
                }
            } catch (MalformedURLException | FileNotFoundException e1){
                e1.printStackTrace();
            }  
        }
    }

    /**
     * Handles the action when link one is selected.
     *
     * @param event the ActionEvent representing the choice selection
     */
    public void choiceOneAction(ActionEvent event){
        choiceAction(passage.getLinks().get(0));
    }

    /**
     * Handles the action when link two is selected.
     *
     * @param event the ActionEvent representing the choice selection
     */
    public void choiceTwoAction(ActionEvent event){
        choiceAction(passage.getLinks().get(1));
    }

    /**
     * Handles the action for a specific link choice.
     *
     * @param link the Link representing the chosen link
     */
    public void choiceAction(Link link){
        try {
            for (Action action : link.getActions()) {
                action.execute(game.getPlayer());
            }
            boolean goalsFulfilled = game.getGoals().stream().allMatch(goal -> goal.isFulfilled(game.getPlayer()));
            if(goalsFulfilled){
                WinAnimation.animation();
            } else if(link.getReference().equalsIgnoreCase("game over")){
                DeathAnimation.animation();
            } else if (link.getReference().equalsIgnoreCase("end game")){
                EndGameAnimation.animation();
            } else{
                Passage passage = game.go(link);
                NextLevelAnimation.animation(passage);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
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
        GameManager.getInstance().endGame();
        saveGame(DialogBoxes.dialogBoxWithTextInput("Save Game", "Enter a name for your save file", "Name:"));
        try {
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
     * Retrieves the text lines for the current conversation pane.
     *
     * @return an array of text lines
     */
    public static String[] getTextLines(){
        //TODO: make less manual
        return FrontendUtils.splitTextIntoFourLines(conversationParts(), ConversationPaneController.conversationPaneNumber);
    }

    /**
     * Retrieves the type of the current conversation pane(P, N or E).
     *
     * @return the type in the current conversation pane
     */
    public static String getTypeInCurrentConversationPane(){
        String[] types = typesInCurrentPassage();
        String type = types[conversationPaneNumber];
        return type;
    }

    /**
     * Retrieves the diffrent parts of conversation in the current passage.
     *
     * @return an array of types
     */
    public static String[] conversationParts(){
        String text = passage.getContent();
        int braceIndexx = text.indexOf('{');
        String output = text.substring(braceIndexx);
        String[] conversationParts = output.split("\\n");
        return conversationParts;
    }

    /**
     * Retrieves the types in the current passage.
     *
     * @return an array of types
     */
    public static String[] typesInCurrentPassage(){
        String[] conversationParts = conversationParts();
        String[] types = new String[conversationParts.length];
        for (int i = 0; i < conversationParts.length; i++) {
            int braceIndex = conversationParts[i].indexOf('{');
            if (braceIndex >= 0) {
                types[i] = conversationParts[i].substring(braceIndex, braceIndex + 3);
            }
        }
        return types;
    }

    /**
     * Retrieves the player's inventory items.
     *
     * @return an array of inventory items
     */
    public static String[] getPlayerInventoryItems(){
        List<String> inventoryItems = GameManager.getInstance().getGame().getPlayer().getInventory();
        String[] result = new String[inventoryItems.size()];
    
        for (int i = 0; i < inventoryItems.size(); i++) {
            result[i] = inventoryItems.get(i);
        }
        return result;
    }
}
