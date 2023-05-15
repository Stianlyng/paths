package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.Animations.DeathAnimation;
import edu.ntnu.g60.views.Animations.NextLevelAnimation;
import edu.ntnu.g60.views.GamePanes.ConversationPane;
import edu.ntnu.g60.views.GamePanes.FightPane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class ConversationPaneController {
    boolean clickable;

    public ConversationPaneController(){
        clickable = true;
    }

    public static int conversationPaneNumber;

    static ConversationPane currentConversationPane;

    public ConversationPane getCurrentConversationPane(){
        return currentConversationPane;
    }

    public static void setCurrentConversationPane(ConversationPane pane){
        currentConversationPane = pane;
    }

    public static void setConversationPaneNumber(int conversationPaneUpdatedNumber){
        conversationPaneNumber = conversationPaneUpdatedNumber;
    }

    public static String getType(){
        String[] types = types();
        String type = types[conversationPaneNumber];
        return type;
    }

    public void sceneClickedAction(MouseEvent event){
        if(clickable){
            boolean moreLinesLeft = (conversationPaneNumber + 1 == types().length) ? false : true;
            setConversationPaneNumber(conversationPaneNumber + 1);
    
            if(moreLinesLeft){
                try {
                    ConversationPane pane = new ConversationPane();
                    ConversationPaneController.setCurrentConversationPane(pane);
                    GameApp.changeRootPane(pane);
                    SoundController.stopSound();
                    SoundController.playSound("mumble");
                } catch (FileNotFoundException | MalformedURLException e1) {
                    e1.printStackTrace();
                } 
            } else if (GameController.getCurrentPassage().hasFightScene()){
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
    
    public void exitAction(ActionEvent event){
        GameApp.closeApplication();
    }

    public void menuAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void choiceOneAction(ActionEvent event){
        try {
            Link link1 = GameController.getCurrentPassage().getLinks().get(0);
            if(link1.getReference().equals("game over")){
                DeathAnimation.animation();
            } else{
                GameController.setCurrentGame(GameController.getCurrentGame());
                NextLevelAnimation.animation();
                GameController.getGameManager().saveGameToFile(ControllerValues.getGameFile());
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void choiceTwoAction(ActionEvent event){
        try {
            Link link2 = GameController.getCurrentPassage().getLinks().get(1);
            if(link2.getReference().equals("game over")){
                DeathAnimation.animation();
            } else{
                GameController.setCurrentGame(GameController.getCurrentGame());
                NextLevelAnimation.animation();
                GameController.getGameManager().saveGameToFile(ControllerValues.getGameFile());
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

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

    public static String[] passages(){
        String text = GameController.getCurrentPassage().getContent();
        int braceIndexx = text.indexOf('{');
        String output = text.substring(braceIndexx);
        String[] passages = output.split("\\n");
        return passages;
    }

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
