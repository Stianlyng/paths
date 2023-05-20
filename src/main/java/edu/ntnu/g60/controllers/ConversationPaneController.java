package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import edu.ntnu.g60.views.StartMenu.OpeningPane;
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

    public ConversationPaneController(Passage passage){
        this.passage = passage;
        clickable = true;
    }

    public ConversationPane getCurrentConversationPane(){
        return currentConversationPane;
    }

    public static void setCurrentConversationPane(ConversationPane pane){
        currentConversationPane = pane;
    }


    public static void setConversationPaneNumber(int conversationPaneUpdatedNumber){
        conversationPaneNumber = conversationPaneUpdatedNumber;
    }





    //TODO: change stile of morelines left?
    public void conversationPaneClickedAction(MouseEvent event){
        if(clickable){
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
                    MiniGameController.setCurrentFightPane(pane);
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

    public void choiceOneAction(ActionEvent event){
        choiceAction(passage.getLinks().get(0));
    }

    public void choiceTwoAction(ActionEvent event){
        choiceAction(passage.getLinks().get(1));
    }

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

    public void exitApplicationAction(ActionEvent event){
        GameApp.closeApplication();
    }

    public void goToMenuAndSaveAction(ActionEvent event){
        saveGame(DialogBoxes.dialogBoxWithTextInput("Save Game", "Enter a name for your save file", "Name:"));
        try {
            GameApp.changeRootPane(new OpeningPane());
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

    public static String[] getTextLines(){
        return FrontendUtils.splitTextIntoFourLines(passages(), ConversationPaneController.conversationPaneNumber);
    }

    //TODO: make less manual
    public static String getTypeInCurrentConversationPane(){
        String[] types = typesInCurrentPassage();
        String type = types[conversationPaneNumber];
        return type;
    }

    public static String[] passages(){
        String text = passage.getContent();
        int braceIndexx = text.indexOf('{');
        String output = text.substring(braceIndexx);
        String[] passages = output.split("\\n");
        return passages;
    }

    public static String[] typesInCurrentPassage(){
        String[] passages = passages();
        String[] types = new String[passages.length];
        for (int i = 0; i < passages.length; i++) {
            int braceIndex = passages[i].indexOf('{');
            if (braceIndex >= 0) {
                types[i] = passages[i].substring(braceIndex, braceIndex + 3);
            }
        }
        return types;
    }

    public static String[] getPlayerInventoryItems(){
        List<String> inventoryItems = GameManager.getInstance().getGame().getPlayer().getInventory();
        String[] result = new String[inventoryItems.size()];
    
        for (int i = 0; i < inventoryItems.size(); i++) {
            result[i] = inventoryItems.get(i);
        }
        return result;
    }
}
