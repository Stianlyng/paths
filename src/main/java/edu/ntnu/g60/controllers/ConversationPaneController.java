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
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.Animations.DeathAnimation;
import edu.ntnu.g60.views.Animations.EndGameAnimation;
import edu.ntnu.g60.views.Animations.NextLevelAnimation;
import edu.ntnu.g60.views.Animations.WinAnimation;
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
    
    public static Game game = GameManager.getInstance().getGame();
    

    boolean clickable;    

    public ConversationPaneController(){
        clickable = true;
    }

    public static int conversationPaneNumber;
    
    public static ConversationPane currentConversationPane;
    
    public ConversationPane getCurrentConversationPane(){
        return currentConversationPane;
    }

    public static void setCurrentConversationPane(ConversationPane pane){
        currentConversationPane = pane;
    }


    public static void setConversationPaneNumber(int conversationPaneUpdatedNumber){
        conversationPaneNumber = conversationPaneUpdatedNumber;
    }






    public void paneClickedAction(MouseEvent event){
    
    }

    public void exitApplicationAction(ActionEvent event){
        GameApp.closeApplication();
    }

    public void goToMenuAction(ActionEvent event){

    }

    public void choiceOneAction(ActionEvent event){

    }


    public void choiceTwoAction(ActionEvent event){
    }


    public static String getType(){
        String[] types = types();
        String type = types[conversationPaneNumber];
        return type;
    }




















































//UTILS    




    /**
     * Retrieves the text lines for the current conversation pane in a manageble form.
     *
     * @return an array of text lines for display in the conversation pane
     */
    public static String[] getTextLines(){
        String[] passages = passages();
        String[] contents = new String[passages.length];
        for (int i = 0; i < passages.length; i++) {
            int braceIndex = passages[i].indexOf('{');
            if (braceIndex >= 0) {
                contents[i] = passages[i].substring(braceIndex + 3);
            }
        }

        String[] passageContent = contents;
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
}
