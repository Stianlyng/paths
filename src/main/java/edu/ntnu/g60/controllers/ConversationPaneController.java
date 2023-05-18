package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageManager;
import edu.ntnu.g60.utils.SaveFileHandler;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.Animations.DeathAnimation;
import edu.ntnu.g60.views.Animations.NextLevelAnimation;
import edu.ntnu.g60.views.GamePanes.ConversationPane;
import edu.ntnu.g60.views.GamePanes.FightPane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 * The ConversationPaneController class is responsible for controlling the conversation pane in the game.
 * It handles user interactions and updates the conversation pane accordingly.
 */
public class ConversationPaneController {
    boolean clickable;
    
    /**
    * Constructs a ConversationPaneController object.
    * Initializes the clickable flag as true.
    */    
    public ConversationPaneController(){
        clickable = true;
    }

    /**
    * The current conversation pane number.
    */
    public static int conversationPaneNumber;

    /**
    * The current conversation pane.
    */
    static ConversationPane currentConversationPane;

    /**
    * Returns the current conversation pane.
    * 
    * @return the current ConversationPane object
    */
    public ConversationPane getCurrentConversationPane(){
        return currentConversationPane;
    }

    /**
    * Sets the current conversation pane.
    * 
    * @param pane the ConversationPane object to set as current
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
    * Returns the type of the current conversation pane.
    * 
    * @return the type of the conversation pane as a String
    */
    public static String getType(){
        String[] types = types();
        String type = types[conversationPaneNumber];
        return type;
    }

    /**
    * Handles the action when the scene is clicked.
    * Updates the conversation pane or switches to fight pane based on the game state.
    * 
    * @param event the MouseEvent representing the click event
    */
    public void sceneClickedAction(MouseEvent event){
        if(clickable){
            boolean moreLinesLeft = (conversationPaneNumber + 1 == types().length) ? false : true;
            setConversationPaneNumber(conversationPaneNumber + 1);
    
            if(moreLinesLeft){
                try {
                    SoundController.stopSound();
                    ConversationPane pane = new ConversationPane();
                    ConversationPaneController.setCurrentConversationPane(pane);
                    GameApp.changeRootPane(pane);
                    SoundController.playSound("mumble");
                } catch (FileNotFoundException | MalformedURLException e1) {
                    e1.printStackTrace();
                } 
            } else if (PassageManager.getInstance().getPassage().hasFightScene()){
                try {
                    SoundController.stopSound();
                    FightPane pane = new FightPane();
                    FightPaneController.setDefaultHealthValues();
                    FightPaneController.setCurrentFightPane(pane);
                    GameApp.changeRootPane(pane);
                } catch (MalformedURLException | FileNotFoundException e1){
                    e1.printStackTrace();
                }  
            } else {
                try {
                    SoundController.stopSound();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } 
                try {
                    clickable = false;
                    ConversationPane.addChoiceObjects(getCurrentConversationPane());
                } catch (FileNotFoundException | MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Handles the action when the exit button is clicked.
     *
     * @param event the ActionEvent representing the button click event
     */
    public void exitAction(ActionEvent event){
        GameApp.closeApplication();
    }

    /**
     * Handles the action when the menu button is clicked.
     *
     * @param event the ActionEvent representing the button click event
     */
    public void menuAction(ActionEvent event){
        try {
            SaveFileHandler.saveGameToFile(GameManager.getInstance().getGame(), GameController.getSaveName(), PassageManager.getInstance().getPassage().getTitle());
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the first choice button is clicked.
     *
     * @param event the ActionEvent representing the button click event
     */
    public void choiceOneAction(ActionEvent event){
        try {
            Link link1 = PassageManager.getInstance().getPassage().getLinks().get(0);
            if(link1.getReference().equals("game over")){
                DeathAnimation.animation();
            } else{
            
                Passage currentPassage = GameManager.getInstance().getGame().go(link1);
                PassageManager.getInstance().setPassage(currentPassage); 
                
                NextLevelAnimation.animation();
                SaveFileHandler.saveGameToFile(GameManager.getInstance().getGame(), GameController.getSaveName(), PassageManager.getInstance().getPassage().getTitle() );
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Handles the action when the second choice button is clicked.
     *
     * @param event the ActionEvent representing the button click event
     */
    public void choiceTwoAction(ActionEvent event){
        try {
            Link link2 = PassageManager.getInstance().getPassage().getLinks().get(1);
            if(link2.getReference().equals("game over")){
                DeathAnimation.animation();
            } else{

                //todo; fix this
                Passage currentPassage = GameManager.getInstance().getGame().go(link2);
                PassageManager.getInstance().setPassage(currentPassage); 

                NextLevelAnimation.animation();
                SaveFileHandler.saveGameToFile(GameManager.getInstance().getGame(), GameController.getSaveName(), PassageManager.getInstance().getPassage().getTitle());
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Retrieves the text lines for the current conversation pane in a manageble form.
     *
     * @return an array of text lines for display in the conversation pane
     */
    public static String[] getTextLines(){
        String[] passageContent = contents();
        String line = passageContent[ConversationPaneController.conversationPaneNumber];
        
        String[] words = line.split("\\s+");
        int numGroups = (int) Math.ceil(words.length / 10.0);
        
        List<String[]> wordGroups = IntStream.range(0, numGroups)
            .mapToObj(i -> {
                int startIndex = i * 10;
                int endIndex = Math.min(startIndex + 10, words.length);
                String[] group = Arrays.copyOfRange(words, startIndex, endIndex);
                if (group.length < 10) {
                    String[] paddedGroup = new String[10];
                    Arrays.fill(paddedGroup, "");
                    System.arraycopy(group, 0, paddedGroup, 0, group.length);
                    group = paddedGroup;
                }
                return group;
            })
            .collect(Collectors.toList());
        
        String[] textLines = IntStream.range(0, Math.min(numGroups, 4))
            .mapToObj(i -> {
                StringBuilder sb = new StringBuilder();
                Arrays.stream(wordGroups.get(i)).forEach(word -> sb.append(word).append(" "));
                return sb.toString().trim();
            })
            .toArray(String[]::new);
        
        if (textLines.length < 4) {
            String[] paddedResult = new String[4];
            Arrays.fill(paddedResult, "");
            System.arraycopy(textLines, 0, paddedResult, 0, textLines.length);
            textLines = paddedResult;
        }
        return textLines;
    }

    /**
     * Retrieves the passages for the current conversation pane.
     *
     * @return an array of passages for the conversation pane
     */
    public static String[] passages(){
        String text = PassageManager.getInstance().getPassage().getContent();
        int braceIndexx = text.indexOf('{');
        String output = text.substring(braceIndexx);
        String[] passages = output.split("\\n");
        return passages;
    }

    /**
     * Retrieves the types of the passages in the conversation pane.
     *
     * @return an array of passage types for the conversation pane
     */
    public static String[] types(){
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

    /**
     * Retrieves the contents of the passages in the conversation pane.
     *
     * @return an array of passage contents for the conversation pane
     */
    public static String[] contents(){
        String[] passages = passages();
        String[] contents = new String[passages.length];
        for (int i = 0; i < passages.length; i++) {
            int braceIndex = passages[i].indexOf('{');
            if (braceIndex >= 0) {
                contents[i] = passages[i].substring(braceIndex + 3);
            }
        }
        return contents;
    }

}
